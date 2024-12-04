package com.lp.clinic.models.dto;

import java.util.List;
import java.util.Set;

import com.lp.clinic.models.ConsultationModel;
import com.lp.clinic.models.enums.Symptoms;

public class ConsultationGet extends ConsultationModel {
    private Long id;

    private String patientName;

    private List<String> guardiansCpfs;

    private String patientBreed;

    private String patientType;

    public ConsultationGet() {
    }

    public ConsultationGet(Long id, Long patientId, Set<Long> guardiansIds, Set<Symptoms> symptoms, String description,
            String patientName,
            List<String> guardiansCpfs,
            String patientBreed, String patientType) {
        super(patientId, guardiansIds, symptoms, description);
        this.id = id;
        this.patientName = patientName;
        this.guardiansCpfs = guardiansCpfs;
        this.patientBreed = patientBreed;
        this.patientType = patientType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<String> getGuardiansCpfs() {
        return guardiansCpfs;
    }

    public void setGuardiansCpfs(List<String> guardiansCpfs) {
        this.guardiansCpfs = guardiansCpfs;
    }

    public String getPatientBreed() {
        return patientBreed;
    }

    public void setPatientBreed(String patientBreed) {
        this.patientBreed = patientBreed;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public ConsultationModel toConsultationModel() {
        ConsultationModel model = new ConsultationModel(this.getPatientId(), this.getGuardiansIds(), this.getSymptoms(),
                this.getDescription());

        model.setId(this.id);

        return model;
    }

}
