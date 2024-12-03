package com.lp.central.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;
import com.lp.central.repositories.GuardianRepository;
import com.lp.central.services.GuardianService;

@Service
public class GuardianServicesImpl implements GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;

    @Override
    public List<GuardianModel> getAllGuardians() {
        return guardianRepository.findAll();
    }

    @Override
    public GuardianModel getGuardianById(Long id) {
        return guardianRepository.findById(id).orElseThrow(() -> new RuntimeException("Guardian not found"));
    }
    
    @Override
    public GuardianModel getGuardianByEmail(String email) {
        if(!guardianRepository.existsByEmail(email)) {
            throw new RuntimeException("Guardian not found");
        }
        return guardianRepository.findByEmail(email);
    }

    @Override
    public GuardianModel getGuardianByCpf(String cpf) {
        if(!guardianRepository.existsByCpf(cpf)) {
            throw new RuntimeException("Guardian not found");
        }
        return guardianRepository.findByCpf(cpf);
    }
    
    @Override
    public List<GuardianModel> getGuardianByCpf(List<String> guardianCpfs) {
        List<GuardianModel> guardians = new ArrayList<>();
        for (String cpf : guardianCpfs) {
            if (!guardianRepository.existsByCpf(cpf)) {
                throw new RuntimeException("Any guardian not found");
            }
            guardians.add(guardianRepository.findByCpf(cpf));
        }
        return guardians;
    }

    @Override
    public List<PetModel> getPetsByGuardian(Long id) {
        GuardianModel guardian = guardianRepository.findById(id).orElseThrow(() -> new RuntimeException("Guardian not found"));
        List <PetModel> pets = new ArrayList<>();
        pets.addAll(guardian.getPets());
        return pets;
    }
    
    @Override
    public GuardianModel createGuardian(GuardianModel guardian) {
        if(guardianRepository.existsByEmail(guardian.getEmail()) || guardianRepository.existsByCpf(guardian.getCpf())) {
            throw new RuntimeException("Error creating guardian");
        }
        return guardianRepository.save(guardian);
    }
    
    @Override
    public PetModel addPet(Long guardianId, PetModel pet) {
        GuardianModel guardian = guardianRepository.findById(guardianId).orElseThrow(() -> new RuntimeException("Guardian not found"));
        guardian.getPets().add(pet);
        guardianRepository.save(guardian);

        return pet;
    }

    @Override
    public PetModel removePet(Long guardianId, PetModel pet) {
        GuardianModel guardian = guardianRepository.findById(guardianId).orElseThrow(() -> new RuntimeException("Guardian not found"));
        guardian.getPets().remove(pet);
        guardianRepository.save(guardian);

        return pet;
    }

    @Override
    public GuardianModel updateGuardian(Long id, GuardianModel updatedGuardian) {
        GuardianModel guardian = guardianRepository.findById(id).orElseThrow(() -> new RuntimeException("Guardian not found"));
        
        if(guardianRepository.existsByEmail(updatedGuardian.getEmail()) && !guardianRepository.findByEmail(updatedGuardian.getEmail()).getId().equals(id)) {
            throw new RuntimeException("Error updating guardian");
        }

        if(guardianRepository.existsByCpf(updatedGuardian.getCpf()) && !guardianRepository.findByCpf(updatedGuardian.getCpf()).getId().equals(id)) {
            throw new RuntimeException("Error updating guardian");
        }
        
        guardian.setName(updatedGuardian.getName());
        guardian.setCpf(updatedGuardian.getCpf());
        guardian.setEmail(updatedGuardian.getEmail());
        return guardianRepository.save(guardian);
    }

    @Override
    public void deleteGuardian(Long id) {
        if(!guardianRepository.existsById(id)) {
            throw new RuntimeException("Guardian not found");
        }
        guardianRepository.deleteById(id);
    }

}