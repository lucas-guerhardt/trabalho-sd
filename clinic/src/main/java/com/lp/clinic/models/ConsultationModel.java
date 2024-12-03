package com.lp.clinic.models;

import java.util.Set;

import com.lp.clinic.models.enums.Symptoms;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;

@Entity
public class ConsultationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long patientId;

    @ElementCollection
    @CollectionTable(
        name = "consultation_guardians", 
        joinColumns = @JoinColumn(name = "consultation_id")
    )
    @Column(name = "guardian_id")
    private Set<Long> guardiansIds;

    @ElementCollection
    @CollectionTable(
        name = "consultation_symptoms", 
        joinColumns = @JoinColumn(name = "consultation_id") 
    )
    @Enumerated(EnumType.ORDINAL) 
    @Column(name = "symptom")
    private Set<Symptoms> symptoms;

    public ConsultationModel() {
    }

    public ConsultationModel(Long patientId, Set<Long> guardiansIds, Set<Symptoms> symptomsIds) {
        this.patientId = patientId;
        this.guardiansIds = guardiansIds;
        this.symptoms = symptomsIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Set<Long> getGuardiansIds() {
        return guardiansIds;
    }

    public void setGuardiansIds(Set<Long> guardiansIds) {
        this.guardiansIds = guardiansIds;
    }

    public Set<Symptoms> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptoms> symptoms) {
        this.symptoms = symptoms;
    }
}