package com.lp.central.services;

import java.util.List;

import com.lp.central.models.PetModel;

public interface PetService {
    PetModel createPet(PetModel pet, List
    <String> guardianCpfs);
    List<PetModel> getAllPets();
    PetModel getPetById(Long id);
    PetModel updatePet(Long id, PetModel updatedPet);
    PetModel addGuardians(Long id, List<String> guardianCpfs);
    PetModel removeGuardians(Long id, List<String> guardianCpfs);
    void deletePet(Long id);
}