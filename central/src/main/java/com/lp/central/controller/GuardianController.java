package com.lp.central.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lp.central.models.GuardianModel;
import com.lp.central.models.PetModel;
import com.lp.central.services.GuardianService;

@RestController
@RequestMapping("/guardian")
public class GuardianController {
    @Autowired
    private GuardianService guardianService;

    @GetMapping("/")
    public ResponseEntity<List<GuardianModel>> getAllGuardians() {
        try{
            return ResponseEntity.ok(guardianService.getAllGuardians());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuardianModel> getGuardianById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(guardianService.getGuardianById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetModel>> getPetsByGuardian(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(guardianService.getPetsByGuardian(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGuardian(@RequestBody GuardianModel guardian) {
        try{
            GuardianModel createdGuardian = guardianService.createGuardian(guardian);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdGuardian.getId())
            .toUri();
            return ResponseEntity.created(location).body("Guardian created with id: " + createdGuardian.getId());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GuardianModel> updateGuardian(@PathVariable Long id, @RequestBody GuardianModel updatedGuardian) {
        try{
            guardianService.updateGuardian(id, updatedGuardian);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGuardian(@PathVariable Long id) {
        try{
            guardianService.deleteGuardian(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
