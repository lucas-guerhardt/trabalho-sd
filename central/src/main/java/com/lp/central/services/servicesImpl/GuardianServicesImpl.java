package com.lp.central.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;
import com.lp.central.models.dto.guardian.GuardianCreate;
import com.lp.central.models.dto.guardian.GuardianGet;
import com.lp.central.models.dto.guardian.GuardianUpdate;
import com.lp.central.models.dto.pet.PetGet;
import com.lp.central.repositories.GuardianRepository;
import com.lp.central.services.GuardianService;
import com.lp.central.services.PetService;

@Service
public class GuardianServicesImpl implements GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PetService petService;

    @Override
    public List<Long> getPetsIdsOf(String cpf) {
        GuardianModel guardian = guardianRepository.findByCpf(cpf);
        Set<PetModel> pets = guardian.getPets();
        List<Long> petsIds = new ArrayList<>();
        for (PetModel pet : pets) {
            petsIds.add(pet.getId());
        }
        return petsIds;
    }

    @Override
    public List<GuardianGet> get() {
        List<GuardianModel> guardians = guardianRepository.findAll();
        List<GuardianGet> guardianGet = new ArrayList<>();

        for (GuardianModel guardian : guardians) {
            List<Long> petsIds = getPetsIdsOf(guardian.getCpf());
            guardianGet
                    .add(new GuardianGet(guardian.getId(), guardian.getName(), guardian.getEmail(), guardian.getCpf(),
                            petsIds));
        }
        return guardianGet;
    }

    @Override
    public GuardianGet get(Long id) {
        GuardianModel guardian = guardianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guardian not found"));

        return new GuardianGet(guardian.getId(), guardian.getName(), guardian.getEmail(), guardian.getCpf(),
                getPetsIdsOf(guardian.getCpf()));
    }

    @Override
    public GuardianGet getByEmail(String email) {
        if (!guardianRepository.existsByEmail(email)) {
            throw new RuntimeException("Guardian not found");
        }
        GuardianModel guardian = guardianRepository.findByEmail(email);

        return new GuardianGet(guardian.getId(), guardian.getName(), guardian.getEmail(), guardian.getCpf(),
                getPetsIdsOf(email));
    }

    @Override
    public GuardianGet getByCpf(String cpf) {
        if (!guardianRepository.existsByCpf(cpf)) {
            throw new RuntimeException("Guardian not found");
        }
        GuardianModel guardian = guardianRepository.findByCpf(cpf);

        return new GuardianGet(guardian.getId(), guardian.getName(), guardian.getEmail(), guardian.getCpf(),
                getPetsIdsOf(cpf));
    }

    @Override
    public List<GuardianGet> getByCpf(List<String> guardianCpfs) {
        List<GuardianGet> guardians = new ArrayList<>();

        for (String cpf : guardianCpfs) {
            if (!guardianRepository.existsByCpf(cpf)) {
                throw new RuntimeException("Any guardian not found");
            }
            GuardianModel guardian = guardianRepository.findByCpf(cpf);
            guardians
                    .add(new GuardianGet(guardian.getId(), guardian.getName(), guardian.getEmail(), guardian.getCpf(),
                            getPetsIdsOf(cpf)));
        }
        return guardians;
    }

    @Override
    public List<PetGet> getPetsByGuardian(Long id) {
        GuardianModel guardian = guardianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guardian not found"));
        List<PetModel> pets = new ArrayList<>();
        pets.addAll(guardian.getPets());

        List<PetGet> petGet = new ArrayList<>();
        for (PetModel pet : pets) {
            petGet.add(new PetGet(pet.getId(), pet.getName(), pet.getAge(), pet.getColor(), pet.getBreed(),
                    pet.getPetType(), petService.getGuardiansCpfsOf(id)));
        }

        return petGet;
    }

    @Override
    public String createGuardian(GuardianCreate guardian) {
        if (guardianRepository.existsByEmail(guardian.getEmail())
                || guardianRepository.existsByCpf(guardian.getCpf())) {
            throw new RuntimeException("Error creating guardian");
        }
        GuardianModel newGuardian = new GuardianModel(guardian.getName(), guardian.getEmail(), guardian.getCpf());
        guardianRepository.save(newGuardian);

        return "Guardian created with id: " + newGuardian.getId();
    }

    @Override
    public String addPet(Long guardianId, PetModel pet) {
        GuardianModel guardian = guardianRepository.findById(guardianId)
                .orElseThrow(() -> new RuntimeException("Guardian not found"));
        guardian.getPets().add(pet);
        guardianRepository.save(guardian);

        return "Pet with id: " + pet.getId() + " added to guardian whit id: " + guardian.getId();
    }

    @Override
    public String removePet(Long guardianId, PetModel pet) {
        GuardianModel guardian = guardianRepository.findById(guardianId)
                .orElseThrow(() -> new RuntimeException("Guardian not found"));
        guardian.getPets().remove(pet);
        guardianRepository.save(guardian);

        return "Pet with id: " + pet.getId() + " removed";
    }

    @Override
    public String updateGuardian(Long id, GuardianUpdate updatedGuardian) {
        GuardianModel guardian = guardianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guardian not found"));

        if (!Objects.equals(updatedGuardian.getEmail(), null)
                && guardianRepository.existsByEmail(updatedGuardian.getEmail())
                && !guardianRepository.findByEmail(updatedGuardian.getEmail()).getId().equals(id)) {
            throw new RuntimeException("Error updating guardian");
        }

        if (!Objects.equals(updatedGuardian.getName(), null)) {
            guardian.setName(updatedGuardian.getName());
        }

        if (!Objects.equals(updatedGuardian.getEmail(), null)) {
            guardian.setEmail(updatedGuardian.getEmail());
        }

        guardianRepository.save(guardian);

        return "Guardian with id: " + guardian.getId() + " updated";
    }

    @Override
    public void deleteGuardian(Long id) {
        if (!guardianRepository.existsById(id)) {
            throw new RuntimeException("Guardian not found");
        }
        guardianRepository.deleteById(id);
    }

}