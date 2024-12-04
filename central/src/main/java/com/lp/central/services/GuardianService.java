package com.lp.central.services;

import java.util.List;

import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;
import com.lp.central.models.dto.guardian.GuardianCreate;
import com.lp.central.models.dto.guardian.GuardianGet;
import com.lp.central.models.dto.guardian.GuardianUpdate;

public interface GuardianService {
    List<GuardianGet> get();

    GuardianGet get(Long id);

    List<Long> getPetsIdsOf(String cpf);

    GuardianGet getByEmail(String email);

    GuardianGet getByCpf(String cpf);

    List<GuardianGet> getByCpf(List<String> cpf);

    GuardianModel createGuardian(GuardianCreate guardian);

    String addPet(Long guardianId, PetModel pet);

    String removePet(Long guardianId, PetModel pet);

    String updateGuardian(Long id, GuardianUpdate updatedGuardian);

    void deleteGuardian(Long id);
}
