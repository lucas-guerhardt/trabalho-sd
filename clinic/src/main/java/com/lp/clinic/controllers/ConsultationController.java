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
import com.lp.clinic.models.dto.ConsultationCreate;
import com.lp.clinic.models.dto.ConsultationGet;
import com.lp.clinic.models.dto.ConsultationUpdate;
import com.lp.clinic.services.ConsultationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/consultation")
@Tag(name = "Consultation Controller", description = "Consultation API")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @GetMapping("/")
    public ResponseEntity<List<ConsultationGet>> getAllConsultations() {
        try {
            return ResponseEntity.ok(consultationService.get());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationGet> getConsultationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(consultationService.get(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConsultationGet>> getConsultationsByPatientId(@PathVariable Long patientId) {
        try {
            return ResponseEntity.ok(consultationService.getConsultationByPatientId(patientId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/guardian/{guardianId}")
    public ResponseEntity<List<ConsultationGet>> getConsultationsByGuardianId(@PathVariable Long guardianId) {
        try {
            return ResponseEntity.ok(consultationService.getConsultationByGuardianId(guardianId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/guardian/cpf{cpf}")
    public ResponseEntity<List<ConsultationGet>> getConsultationsByGuardianCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(consultationService.getConsultationByGuardianCpf(cpf));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createConsultation(@RequestBody ConsultationCreate consultation) {
        try {
            ConsultationModel createdConsultation = consultationService.createConsultation(consultation);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdConsultation.getId())
                    .toUri();
            return ResponseEntity.created(location)
                    .body("Consultation created successfully with id: " + createdConsultation.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConsultationModel> updateConsultation(@PathVariable Long id,
            ConsultationUpdate updatedConsultation) {
        try {
            consultationService.updateConsultation(id, updatedConsultation);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        try {
            consultationService.deleteConsultation(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}