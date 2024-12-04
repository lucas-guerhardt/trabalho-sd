package com.lp.clinic.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.lp.clinic.config.CentralConfig;
import com.lp.clinic.models.ConsultationModel;
import com.lp.clinic.models.dto.CRUD.ConsultationCreate;
import com.lp.clinic.models.dto.CRUD.ConsultationGet;
import com.lp.clinic.models.dto.CRUD.ConsultationUpdate;
import com.lp.clinic.models.dto.CentralService.GuardianDto;
import com.lp.clinic.models.dto.CentralService.PetDto;
import com.lp.clinic.repositories.ConsultationRepository;
import com.lp.clinic.services.ConsultationService;
import com.lp.clinic.services.EmailProducerService;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private EmailProducerService emailProducerService;

    private PetDto getPet(Long petId) {
        PetDto pet = restTemplate.getForObject(
                CentralConfig.GET_PET_URL + petId,
                PetDto.class);
        return pet;
    }

    private String getGuardianCpf(Long guardianId) {
        GuardianDto guardian = restTemplate.getForObject(
                CentralConfig.GET_GUARDIAN + guardianId,
                GuardianDto.class);

        if (guardian == null) {
            return null;
        }

        return guardian.getCpf();
    }

    private ConsultationGet mapConsultation(ConsultationModel consultation) {
        PetDto pet = getPet(consultation.getPatientId());
        List<String> guardiansCpfs = consultation.getGuardiansIds().stream()
                .map(this::getGuardianCpf)
                .toList();
        return new ConsultationGet(consultation.getId(), consultation.getPatientId(),
                consultation.getGuardiansIds(), consultation.getSymptoms(), consultation.getDescription(),
                pet.getName(), guardiansCpfs, pet.getBreed(), pet.getPetType());
    }

    @Override
    public List<ConsultationGet> get() {
        List<ConsultationModel> consultations = consultationRepository.findAll();

        return consultations.stream().map(consultation -> {
            return mapConsultation(consultation);
        }).toList();
    }

    @Override
    public ConsultationGet get(Long id) {
        ConsultationModel consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));

        return mapConsultation(consultation);
    }

    @Override
    public List<ConsultationGet> getConsultationByPatientId(Long patientId) {
        List<ConsultationModel> consultation = consultationRepository.findByPatientId(patientId);
        return consultation.stream().map(this::mapConsultation).toList();
    }

    @Override
    public List<ConsultationGet> getConsultationByGuardianCpf(String guardianCpf) {
        ResponseEntity<List<GuardianDto>> response = restTemplate.exchange(
                CentralConfig.GET_ALL_GUARDIAN,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuardianDto>>() {
                });

        List<GuardianDto> guardians = response.getBody();

        List<ConsultationModel> consultations = consultationRepository.findAll();

        if (guardians == null) {
            return List.of();
        }

        return consultations.stream().filter(consultation -> {
            return consultation.getGuardiansIds().stream().anyMatch(_ -> {
                return guardians.stream().anyMatch(guardian -> {
                    return guardian.getCpf().equals(guardianCpf);
                });
            });
        }).map(this::mapConsultation).toList();
    }

    @Override
    public List<ConsultationGet> getConsultationByGuardianId(Long guardianId) {
        ResponseEntity<List<GuardianDto>> response = restTemplate.exchange(
                CentralConfig.GET_ALL_GUARDIAN,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GuardianDto>>() {
                });

        List<GuardianDto> guardians = response.getBody();

        List<ConsultationModel> consultations = consultationRepository.findAll();

        if (guardians == null) {
            return List.of();
        }

        return consultations.stream().filter(consultation -> {
            return consultation.getGuardiansIds().stream().anyMatch(_ -> {
                return guardians.stream().anyMatch(guardian -> {
                    return guardian.getId().equals(guardianId);
                });
            });
        }).map(this::mapConsultation).toList();
    }

    @Override
    @Transactional
    public ConsultationModel createConsultation(ConsultationCreate consultation) {
        PetDto pet = getPet(consultation.getPatientId());
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        List<String> guardiansEmail = new ArrayList<>();

        consultation.getGuardiansIds().forEach(guardianId -> {
            GuardianDto guardian = restTemplate.getForObject(
                    CentralConfig.GET_GUARDIAN + guardianId,
                    GuardianDto.class);

            if (guardian == null) {
                throw new RuntimeException("Guardian with ID " + guardianId + " not found");
            }

            guardiansEmail.add(guardian.getEmail());
        });

        ConsultationModel consultationModel = new ConsultationModel(consultation.getPatientId(),
                consultation.getGuardiansIds(), consultation.getSymptoms(), consultation.getDescription());

        ConsultationModel newConsultation = consultationRepository.save(consultationModel);

        guardiansEmail.forEach(email -> {
            emailProducerService.sendEmail(email, "New Consultation for " + pet.getName(),
                    "Hello, a new consultation for" + pet.getName()
                            + "was created. Please check the details in the system.");
        });

        return newConsultation;
    }

    @Override
    @Transactional
    public ConsultationModel updateConsultation(Long id, ConsultationUpdate updatedConsultation) {
        ConsultationModel consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
        consultation.setSymptoms(updatedConsultation.getSymptoms());
        consultation.setDescription(updatedConsultation.getDescription());
        return consultationRepository.save(consultation);
    }

    @Override
    @Transactional
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }
}