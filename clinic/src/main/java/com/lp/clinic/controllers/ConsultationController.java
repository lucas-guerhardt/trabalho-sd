package com.lp.clinic.controllers;

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

import com.lp.clinic.models.ConsultationModel;
import com.lp.clinic.services.ConsultationService;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @GetMapping("/")
    public ResponseEntity<List<ConsultationModel>> getAllConsultations() {
        try{
            return ResponseEntity.ok(consultationService.getAllConsultations());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationModel> getConsultationById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(consultationService.getConsultationById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConsultationModel>> getConsultationsByPatientId(@PathVariable Long patientId) {
        try{
            return ResponseEntity.ok(consultationService.getConsultationByPatientId(patientId));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/guardian/{guardianId}")
    public ResponseEntity<List<ConsultationModel>> getConsultationsByGuardianId(@PathVariable Long guardianId) {
        try{
            return ResponseEntity.ok(consultationService.getConsultationByGuardianId(guardianId));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createConsultation(@RequestBody ConsultationModel consultation) {
        try{
            ConsultationModel createdConsultation = consultationService.createConsultation(consultation);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdConsultation.getId())
            .toUri();
            return ResponseEntity.created(location).body("Consultation created successfully with id: " + createdConsultation.getId());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConsultationModel> updateConsultation(@PathVariable Long id, ConsultationModel updatedConsultation) {
        try{
            consultationService.updateConsultation(id, updatedConsultation);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        try{
            consultationService.deleteConsultation(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}