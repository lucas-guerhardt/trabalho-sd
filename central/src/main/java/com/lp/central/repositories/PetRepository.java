package com.lp.central.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lp.central.models.PetModel;
import com.lp.central.models.enums.Breed;
import com.lp.central.models.enums.PetType;

public interface PetRepository extends JpaRepository<PetModel, Long> {
    List<PetModel> findByName(String name);
    List<PetModel> findByPetType(PetType type);
    List<PetModel> findByBreed(Breed breed);
    List<PetModel> findByColor(String color);
    List<PetModel> findByAge(int age);
}