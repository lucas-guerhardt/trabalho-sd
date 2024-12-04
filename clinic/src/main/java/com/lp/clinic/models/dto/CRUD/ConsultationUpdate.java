package com.lp.clinic.models.dto.CRUD;

import java.util.Set;

import com.lp.clinic.models.enums.Symptoms;

public class ConsultationUpdate {

    private Set<Symptoms> symptoms;

    private String description;

    public ConsultationUpdate() {
    }

    public ConsultationUpdate(Set<Symptoms> symptoms,
            String description) {
        this.symptoms = symptoms;
        this.description = description;
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
