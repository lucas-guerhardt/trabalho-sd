package com.lp.clinic.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lp.clinic.config.CentralConfig;
import com.lp.clinic.models.ConsultationModel;
import com.lp.clinic.models.dto.ConsultationCreate;
import com.lp.clinic.models.dto.ConsultationGet;
import com.lp.clinic.models.dto.ConsultationUpdate;
import com.lp.clinic.models.dto.GuardianDto;
import com.lp.clinic.models.dto.PetDto;
import com.lp.clinic.repositories.ConsultationRepository;
import com.lp.clinic.services.ConsultationService;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConsultationRepository consultationRepository;

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
    public ConsultationModel createConsultation(ConsultationCreate consultation) {
        PetDto pet = getPet(consultation.getPatientId());
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        consultation.getGuardiansIds().forEach(guardianId -> {
            GuardianDto guardian = restTemplate.getForObject(
                    CentralConfig.GET_GUARDIAN + guardianId,
                    GuardianDto.class);

            if (guardian == null) {
                throw new RuntimeException("Guardian with ID " + guardianId + " not found");
            }
        });

        ConsultationModel consultationModel = new ConsultationModel(consultation.getPatientId(),
                consultation.getGuardiansIds(), consultation.getSymptoms(), consultation.getDescription());

        return consultationRepository.save(consultationModel);
    }

    @Override
    public ConsultationModel updateConsultation(Long id, ConsultationUpdate updatedConsultation) {
        ConsultationModel consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
        consultation.setSymptoms(updatedConsultation.getSymptoms());
        consultation.setDescription(updatedConsultation.getDescription());
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }
}