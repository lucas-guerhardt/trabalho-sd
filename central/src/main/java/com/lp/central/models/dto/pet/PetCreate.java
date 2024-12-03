package com.lp.central.models.dto.pet;

import com.lp.central.models.enums.Breed;
import com.lp.central.models.enums.Color;
import com.lp.central.models.enums.PetType;

public class PetCreate {
    private String name;
    private int age;
    private Color color;
    private Breed breed;
    private PetType petType;

    public PetCreate() {
    }

    public PetCreate(String name, int age, Color color, Breed breed, PetType petType) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.breed = breed;
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }
}
