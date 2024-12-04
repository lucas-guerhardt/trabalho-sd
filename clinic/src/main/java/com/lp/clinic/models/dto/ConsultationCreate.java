package com.lp.clinic.models.dto;

import java.util.Set;

import com.lp.clinic.models.enums.Symptoms;

public class ConsultationCreate {
    private Long patientId;
    private Set<Long> guardiansIds;
    private Set<Symptoms> symptoms;
    private String description;

    public ConsultationCreate() {
    }

    public ConsultationCreate(Long patientId, Set<Long> guardiansIds, Set<Symptoms> symptoms, String description) {
        this.patientId = patientId;
        this.guardiansIds = guardiansIds;
        this.symptoms = symptoms;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
