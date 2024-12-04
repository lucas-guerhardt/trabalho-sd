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
import com.lp.central.models.dto.guardian.GuardianCreate;
import com.lp.central.models.dto.guardian.GuardianGet;
import com.lp.central.models.dto.guardian.GuardianUpdate;
import com.lp.central.models.dto.pet.PetGet;
import com.lp.central.services.GuardianService;
import com.lp.central.services.PetService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/guardian")
@Tag(name = "Guardian Controller", description = "Guardian API")
public class GuardianController {
    @Autowired
    private GuardianService guardianService;

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public ResponseEntity<List<GuardianGet>> get() {
        try {
            return ResponseEntity.ok(guardianService.get());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuardianGet> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(guardianService.get(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetGet>> getPetsByGuardian(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(petService.getPetsByGuardianId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGuardian(@RequestBody GuardianCreate guardian) {
        try {
            GuardianModel createdGuardian = guardianService.createGuardian(guardian);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdGuardian.getId())
                    .toUri();
            return ResponseEntity.created(location).body("Guardian created with id: " + createdGuardian.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateGuardian(@PathVariable Long id,
            @RequestBody GuardianUpdate updatedGuardian) {
        try {
            guardianService.updateGuardian(id, updatedGuardian);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGuardian(@PathVariable Long id) {
        try {
            guardianService.deleteGuardian(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
