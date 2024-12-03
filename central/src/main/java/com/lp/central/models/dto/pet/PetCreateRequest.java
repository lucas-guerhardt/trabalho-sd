package com.lp.central.models.dto.pet;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PetCreateRequest{
    @NotNull
    private PetCreate pet;
    @NotNull
    @Size(min = 11 , max = 11)
    private List<String> guardiansCpfs;

    public PetCreateRequest() {
    }

    public PetCreate getPet(PetCreate pet) {
        return pet;
    }

    public void setPet(PetCreate pet) {
        this.pet = pet;
    }

    public List<String> getGuardiansCpfs() {
        return guardiansCpfs;
    }

    public void setGuardiansCpfs(List<String> guardianCpfs) {
        this.guardiansCpfs = guardianCpfs;
    }
}