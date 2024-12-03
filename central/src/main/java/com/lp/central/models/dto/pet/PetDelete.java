package com.lp.central.models.dto.pet;

public class PetDelete {
    private Long id;

    public PetDelete() {
    }

    public PetDelete(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
