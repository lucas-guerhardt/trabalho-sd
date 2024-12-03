package com.lp.central.models.dto.pet;

import com.lp.central.models.enums.Breed;
import com.lp.central.models.enums.Color;
import com.lp.central.models.enums.PetType;

public class PetUpdate extends PetCreate {
    public PetUpdate() {
    }

    public PetUpdate(String name, int age, Color color, Breed breed, PetType petType) {
        super(name, age, color, breed, petType);
    }
}
