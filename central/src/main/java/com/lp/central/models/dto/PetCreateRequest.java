package com.lp.central.models.dto;

import java.util.List;

import com.lp.central.models.PetModel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PetCreateRequest{
    @NotNull
    private PetModel pet;
    @NotNull
    @Size(min = 11 , max = 11)
    private List<String> guardiansCpfs;

    public PetCreateRequest() {
    }

    public PetModel getPet() {
        return pet;
    }

    public void setPet(PetModel pet) {
        this.pet = pet;
    }

    public List<String> getGuardiansCpfs() {
        return guardiansCpfs;
    }

    public void setGuardiansCpfs(List<String> guardianCpfs) {
        this.guardiansCpfs = guardianCpfs;
    }
}