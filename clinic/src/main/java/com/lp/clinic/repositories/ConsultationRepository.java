package com.lp.clinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lp.clinic.models.ConsultationModel;

public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {
    Boolean existsByPatientId(Long patientId);
    List<ConsultationModel> findByPatientId(Long patientId);
    @Query("SELECT c FROM ConsultationModel c WHERE :guardianId IN elements(c.guardiansIds)")
    List<ConsultationModel> findByGuardianId(Long guardianId);
}
