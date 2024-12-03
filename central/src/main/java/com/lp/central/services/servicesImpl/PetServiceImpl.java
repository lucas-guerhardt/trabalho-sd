package com.lp.central.services.servicesImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;
import com.lp.central.repositories.PetRepository;
import com.lp.central.services.GuardianService;
import com.lp.central.services.PetService;

@Service
public class PetServiceImpl implements PetService{
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private GuardianService guardianService;

    @Override
    public List<PetModel> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public PetModel getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public PetModel createPet(PetModel pet, List <String> guardianCpfs) {
        petRepository.save(pet);

        List <GuardianModel> guardians = guardianService.getGuardianByCpf(guardianCpfs);
        pet.setGuardians(new HashSet<>(guardians));

        for(GuardianModel guardian : guardians){
            guardian.getPets().add(pet);
            guardianService.updateGuardian(guardian.getId(), guardian);
        }
        return pet;
    }

    @Override
    public PetModel updatePet(Long id, PetModel updatedPet) {
        PetModel pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }
        return petRepository.save(updatedPet);
    }

    @Override
    public PetModel addGuardians(Long id, List<String> guardianCpfs) {
        PetModel pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        List <GuardianModel> guardians = new ArrayList<>();
        guardians.addAll(pet.getGuardians());
        guardians.addAll(guardianService.getGuardianByCpf(guardianCpfs));
        
        pet.setGuardians(new HashSet<>(guardians));
        guardians.forEach(guardian -> guardianService.addPet(guardian.getId(), pet));
        return petRepository.save(pet);
    }

    @Override
    public PetModel removeGuardians(Long id, List<String> guardianCpfs) {
        PetModel pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new RuntimeException("Pet not found");
        }

        List <GuardianModel> guardians = guardianService.getGuardianByCpf(guardianCpfs);
        pet.getGuardians().removeAll(guardians);
        guardians.forEach(guardian -> guardianService.removePet(guardian.getId(), pet));
        return petRepository.save(pet);
    }

    @Override
    public void deletePet(Long id) {
        if(!petRepository.existsById(id)) {
            throw new RuntimeException("Pet not found");
        }
        petRepository.deleteById(id);
    }
}
