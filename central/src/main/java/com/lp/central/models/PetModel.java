package com.lp.central.models;

import jakarta.persistence.GenerationType;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lp.central.models.enums.Breed;
import com.lp.central.models.enums.Color;
import com.lp.central.models.enums.PetType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pet")
public class PetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private int age;

    private Color color;

    private Breed breed;

    @NotNull
    private PetType petType;

    @ManyToMany(mappedBy = "pets", cascade = (CascadeType.PERSIST))
    @JsonIgnore
    private Set<GuardianModel> guardians;

    public PetModel() {
    }

    public PetModel(String name, int age, Color color, Breed breed, PetType type) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.breed = breed;
        this.petType = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPetType(PetType type) {
        this.petType = type;
    }

    public Set<GuardianModel> getGuardians() {
        return guardians;
    }

    public void setGuardians(Set<GuardianModel> guardians) {
        this.guardians = guardians;
    }
}