package com.lp.central.models.dto.consultationService;

import java.util.List;
import java.util.Set;

public class ConsultationDto {
    private Long id;

    private Long patientId;

    private Set<Long> guardiansIds;

    private Set<String> symptoms;

    private String patientName;

    private List<String> guardiansCpfs;

    private String patientBreed;

    private String patientType;

    public ConsultationDto() {
    }

    public ConsultationDto(Long id, Long patientId, Set<Long> guardiansIds, Set<String> symptoms, String description,
            String patientName,
            List<String> guardiansCpfs,
            String patientBreed, String patientType) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.guardiansIds = guardiansIds;
        this.guardiansCpfs = guardiansCpfs;
        this.patientBreed = patientBreed;
        this.patientType = patientType;
        this.symptoms = symptoms;
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

    public Set<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<String> symptoms) {
        this.symptoms = symptoms;
    }
}
