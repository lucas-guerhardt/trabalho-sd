package com.lp.central.services.servicesImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.lp.central.config.ClinicConfig;
import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;
import com.lp.central.models.dto.consultationService.ConsultationDto;
import com.lp.central.models.dto.guardian.GuardianGet;
import com.lp.central.models.dto.pet.PetCreate;
import com.lp.central.models.dto.pet.PetGet;
import com.lp.central.models.dto.pet.PetUpdate;
import com.lp.central.repositories.PetRepository;
import com.lp.central.services.GuardianService;
import com.lp.central.services.PetService;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private GuardianService guardianService;

    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity<List<ConsultationDto>> getConsultationIdByPetId(Long petId) {
        ResponseEntity<List<ConsultationDto>> response = restTemplate.exchange(
                ClinicConfig.GET_CONSULTATION_BY_PETID_URL + petId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ConsultationDto>>() {
                });

        List<Long> consultationsIds = new ArrayList<>();
        List<ConsultationDto> responseList = response.getBody();

        if (responseList == null) {
            return null;
        }

        responseList.forEach(consultation -> consultationsIds.add(consultation.getId()));

        return response;
    }

    @Override
    public List<String> getGuardiansCpfsOf(Long petId) {
        PetModel pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        List<String> guardiansCpfs = new ArrayList<>();
        for (GuardianModel guardian : pet.getGuardians()) {
            guardiansCpfs.add(guardian.getCpf());
        }
        return guardiansCpfs;
    }

    @Override
    public List<PetGet> get() {
        List<PetModel> pets = petRepository.findAll();
        List<PetGet> petGet = new ArrayList<>();
        for (PetModel pet : pets) {
            petGet.add(new PetGet(pet.getId(), pet.getName(), pet.getAge(), pet.getColor(), pet.getBreed(),
                    pet.getPetType(), getGuardiansCpfsOf(pet.getId())));
        }
        return petGet;
    }

    @Override
    public PetGet get(Long id) {
        PetModel pet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
        return new PetGet(pet.getId(), pet.getName(), pet.getAge(), pet.getColor(), pet.getBreed(), pet.getPetType(),
                getGuardiansCpfsOf(pet.getId()));
    }

    @Override
    public PetGet getByGuardianCpf(String cpf) {
        GuardianGet guardian = guardianService.getByCpf(cpf);
        PetModel pet = guardian.getPets().iterator().next();
        return new PetGet(pet.getId(), pet.getName(), pet.getAge(), pet.getColor(), pet.getBreed(), pet.getPetType(),
                getGuardiansCpfsOf(pet.getId()));
    }

    @Override
    public List<PetGet> getPetsByGuardianId(Long id) {
        GuardianModel guardian = guardianService.get(id);
        if (Objects.equals(guardian, null)) {
            throw new RuntimeException("Guardian not found");

        }
        List<PetModel> pets = new ArrayList<>();
        pets.addAll(guardian.getPets());

        List<PetGet> petGet = new ArrayList<>();
        for (PetModel pet : pets) {
            petGet.add(new PetGet(pet.getId(), pet.getName(), pet.getAge(), pet.getColor(), pet.getBreed(),
                    pet.getPetType(), getGuardiansCpfsOf(id)));
        }

        return petGet;
    }

    @Override
    @Transactional
    public PetModel createPet(PetCreate pet, List<String> guardianCpfs) {
        PetModel newPet = new PetModel(pet.getName(), pet.getAge(), pet.getColor(), pet.getBreed(), pet.getPetType());
        petRepository.save(newPet);

        List<GuardianGet> guardians = guardianService.getByCpf(guardianCpfs);
        List<GuardianModel> guardiansModels = new ArrayList<>();
        for (GuardianGet guardian : guardians) {
            guardiansModels.add(guardian.toGuardianModel());
        }
        newPet.setGuardians(new HashSet<>(guardiansModels));

        for (GuardianGet guardian : guardians) {
            guardianService.addPet(guardian.getId(), newPet);
        }

        return newPet;
    }

    @Override
    @Transactional
    public String updatePet(Long id, PetUpdate updatedPet) {
        PetModel pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        if (!Objects.equals(updatedPet.getName(), null)) {
            pet.setName(updatedPet.getName());
        }

        if (!Objects.equals(updatedPet.getAge(), null)) {
            pet.setAge(updatedPet.getAge());
        }

        if (!Objects.equals(updatedPet.getBreed(), null)) {
            pet.setColor(updatedPet.getColor());
        }

        petRepository.save(pet);

        return "Pet updated successfully with id: " + pet.getId();
    }

    @Override
    @Transactional
    public String addGuardians(Long id, List<String> guardianCpfs) {
        PetModel pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        List<GuardianModel> guardians = new ArrayList<>();
        guardians.addAll(pet.getGuardians());
        List<GuardianGet> guardiansGet = guardianService.getByCpf(guardianCpfs);
        guardiansGet.forEach(guardian -> guardians.add(guardian.toGuardianModel()));
        guardians.addAll(guardians);
        pet.setGuardians(new HashSet<>(guardians));
        guardians.forEach(guardian -> guardianService.addPet(guardian.getId(), pet));
        petRepository.save(pet);

        return "Guardians added successfully to pet with id: " + pet.getId();
    }

    @Override
    @Transactional
    public String removeGuardians(Long id, List<String> guardianCpfs) {
        PetModel pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        List<GuardianGet> guardians = guardianService.getByCpf(guardianCpfs);
        pet.getGuardians().removeAll(guardians);
        guardians.forEach(guardian -> guardianService.removePet(guardian.getId(), pet));
        petRepository.save(pet);

        return "Guardians removed successfully from pet with id: " + pet.getId();
    }

    @Override
    @Transactional
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet not found");
        }

        List<ConsultationDto> consultations = getConsultationIdByPetId(id).getBody();
        if (consultations != null) {
            consultations.forEach(consultation -> {
                restTemplate.delete(ClinicConfig.DELETE_CONSULTATION_URL + consultation.getId());
            });
        }

        petRepository.deleteById(id);
    }
}
