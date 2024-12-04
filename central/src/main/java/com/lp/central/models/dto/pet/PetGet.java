package com.lp.central.models.dto.pet;

import java.util.List;

import com.lp.central.models.PetModel;
import com.lp.central.models.enums.Breed;
import com.lp.central.models.enums.Color;
import com.lp.central.models.enums.PetType;

public class PetGet extends PetModel {
    private Long id;

    private List<String> guardiansCpfs;

    public PetGet() {
    }

    public PetGet(Long id, String name, int age, Color color, Breed breed, PetType type, List<String> guardiansCpfs) {
        super(name, age, color, breed, type);
        this.id = id;
        this.guardiansCpfs = guardiansCpfs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getGuardiansCpfs() {
        return guardiansCpfs;
    }

    public void setGuardiansCpfs(List<String> guardiansCpfs) {
        this.guardiansCpfs = guardiansCpfs;
    }

    public PetModel toPetModel() {
        PetModel petModel = new PetModel(this.getName(), this.getAge(), this.getColor(), this.getBreed(),
                this.getPetType());
        petModel.setId(this.id);
        return petModel;
    }
}
