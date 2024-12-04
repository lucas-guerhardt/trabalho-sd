package com.lp.central.services;

import java.util.List;

import com.lp.central.models.PetModel;
import com.lp.central.models.dto.pet.PetCreate;
import com.lp.central.models.dto.pet.PetGet;
import com.lp.central.models.dto.pet.PetUpdate;

public interface PetService {
    List<PetGet> get();

    PetGet get(Long id);

    List<String> getGuardiansCpfsOf(Long petId);

    PetGet getByGuardianCpf(String cpf);

    List<PetGet> getPetsByGuardianId(Long id);

    PetModel createPet(PetCreate pet, List<String> guardianCpfs);

    String updatePet(Long id, PetUpdate updatedPet);

    String addGuardians(Long id, List<String> guardianCpfs);

    String removeGuardians(Long id, List<String> guardianCpfs);

    void deletePet(Long id);
}