package com.lp.central.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lp.central.models.PetModel;
import com.lp.central.models.dto.PetCreateRequest;
import com.lp.central.services.PetService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/")
    public ResponseEntity<List<PetModel>> getAllPets() {
        try{
            return ResponseEntity.ok(petService.getAllPets());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetModel> getPetById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(petService.getPetById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPet(@RequestBody PetCreateRequest petCreateRequest) {
        try{
            PetModel pet = petService.createPet(petCreateRequest.getPet(), petCreateRequest.getGuardiansCpfs());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(pet.getId())
            .toUri();
            return ResponseEntity.created(location).body("Pet created with id: " + pet.getId());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PetModel> updatePet(@PathVariable Long id, @RequestBody PetModel updatedPet) {
        try{
            petService.updatePet(id, updatedPet);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addGuardians/{id}")
    public ResponseEntity<PetModel> addGuardians(@PathVariable Long id, @RequestBody List<String> guardianCpfs) {
        try{
            petService.addGuardians(id, guardianCpfs);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/removeGuardians/{id}")
    public ResponseEntity<PetModel> removeGuardians(@PathVariable Long id, @RequestBody List<String> guardianCpfs) {
        try{
            petService.removeGuardians(id, guardianCpfs);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        try{
            petService.deletePet(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
