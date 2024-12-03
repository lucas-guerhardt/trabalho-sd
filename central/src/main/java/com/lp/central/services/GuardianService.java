package com.lp.central.services;

import java.util.List;

import com.lp.central.models.PetModel;
import com.lp.central.models.dto.guardian.GuardianCreate;
import com.lp.central.models.dto.guardian.GuardianGet;
import com.lp.central.models.dto.guardian.GuardianUpdate;
import com.lp.central.models.dto.pet.PetGet;

public interface GuardianService {
    List<GuardianGet> get();

    GuardianGet get(Long id);

    List<Long> getPetsIdsOf(String cpf);

    GuardianGet getByEmail(String email);

    GuardianGet getByCpf(String cpf);

    List<GuardianGet> getByCpf(List<String> cpf);

    List<PetGet> getPetsByGuardian(Long id);

    String createGuardian(GuardianCreate guardian);

    String addPet(Long guardianId, PetModel pet);

    String removePet(Long guardianId, PetModel pet);

    String updateGuardian(Long id, GuardianUpdate updatedGuardian);

    void deleteGuardian(Long id);
}
