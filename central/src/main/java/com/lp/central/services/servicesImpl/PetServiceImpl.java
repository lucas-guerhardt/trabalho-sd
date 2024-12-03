package com.lp.central.services.servicesImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;
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
    public String createPet(PetCreate pet, List<String> guardianCpfs) {
        PetModel newPet = new PetModel(pet.getName(), pet.getAge(), pet.getColor(), pet.getBreed(), pet.getPetType());
        petRepository.save(newPet);

        List<GuardianGet> guardians = guardianService.getByCpf(guardianCpfs);
        newPet.setGuardians(new HashSet<>(guardians));

        for (GuardianGet guardian : guardians) {
            guardian.getPets().add(newPet);
            guardianService.addPet(guardian.getId(), newPet);
        }
        return "Pet created successfully with id: " + newPet.getId();
    }

    @Override
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
    public String addGuardians(Long id, List<String> guardianCpfs) {
        PetModel pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        List<GuardianModel> guardians = new ArrayList<>();
        guardians.addAll(pet.getGuardians());
        guardians.addAll(guardianService.getByCpf(guardianCpfs));

        pet.setGuardians(new HashSet<>(guardians));
        guardians.forEach(guardian -> guardianService.addPet(guardian.getId(), pet));
        petRepository.save(pet);

        return "Guardians added successfully to pet with id: " + pet.getId();
    }

    @Override
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
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet not found");
        }
        petRepository.deleteById(id);
    }
}
