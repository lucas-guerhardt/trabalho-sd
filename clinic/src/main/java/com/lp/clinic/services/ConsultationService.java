package com.lp.clinic.services;

import java.util.List;

import com.lp.clinic.models.ConsultationModel;

public interface ConsultationService {
    List<ConsultationModel> getAllConsultations();
    ConsultationModel getConsultationById(Long id);
    List<ConsultationModel> getConsultationByPatientId(Long patientId);
    List<ConsultationModel> getConsultationByGuardianId(Long guardianId);
    ConsultationModel createConsultation(ConsultationModel consultation);
    ConsultationModel updateConsultation(Long id, ConsultationModel updatedConsultation);
    void deleteConsultation(Long id);
}