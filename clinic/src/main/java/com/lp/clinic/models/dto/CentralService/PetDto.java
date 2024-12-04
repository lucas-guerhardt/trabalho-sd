package com.lp.clinic.models.dto.CentralService;

import java.util.List;

public class PetDto {
    private Long id;

    private String name;

    private int age;

    private String color;

    private String breed;

    private String petType;

    private List<String> guardiansCpfs;

    public PetDto() {
    }

    public PetDto(Long id, String name, int age, String color, String breed, String type, List<String> guardiansCpfs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.breed = breed;
        this.petType = type;
        this.guardiansCpfs = guardiansCpfs;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String type) {
        this.petType = type;
    }

    public List<String> getGuardiansCpfs() {
        return guardiansCpfs;
    }

    public void setGuardiansCpfs(List<String> guardiansCpfs) {
        this.guardiansCpfs = guardiansCpfs;
    }
}
