package com.lp.clinic.services;

import java.util.List;

import com.lp.clinic.models.ConsultationModel;
import com.lp.clinic.models.dto.CRUD.ConsultationCreate;
import com.lp.clinic.models.dto.CRUD.ConsultationGet;
import com.lp.clinic.models.dto.CRUD.ConsultationUpdate;

public interface ConsultationService {
    List<ConsultationGet> get();

    ConsultationGet get(Long id);

    List<ConsultationGet> getConsultationByPatientId(Long patientId);

    List<ConsultationGet> getConsultationByGuardianId(Long guardianId);

    List<ConsultationGet> getConsultationByGuardianCpf(String guardianCpf);

    ConsultationModel createConsultation(ConsultationCreate consultation);

    ConsultationModel updateConsultation(Long id, ConsultationUpdate updatedConsultation);

    void deleteConsultation(Long id);
}