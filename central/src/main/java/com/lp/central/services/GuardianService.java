package com.lp.central.services;

import java.util.List;

import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;

public interface GuardianService {
    List<GuardianModel> getAllGuardians();
    GuardianModel getGuardianById(Long id);
    GuardianModel getGuardianByEmail(String email);
    GuardianModel getGuardianByCpf(String cpf);
    List<GuardianModel> getGuardianByCpf(List<String> cpf);
    List<PetModel> getPetsByGuardian(Long id);
    GuardianModel createGuardian(GuardianModel guardian);
    PetModel addPet(Long guardianId, PetModel pet);
    PetModel removePet(Long guardianId, PetModel pet);
    GuardianModel updateGuardian(Long id, GuardianModel updatedGuardian);
    void deleteGuardian(Long id);
}
