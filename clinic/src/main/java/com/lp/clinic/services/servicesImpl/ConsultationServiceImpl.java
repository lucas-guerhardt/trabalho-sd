package com.lp.clinic.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.clinic.models.ConsultationModel;
import com.lp.clinic.repositories.ConsultationRepository;
import com.lp.clinic.services.ConsultationService;

@Service
public class ConsultationServiceImpl implements ConsultationService{
    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public List<ConsultationModel> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public ConsultationModel getConsultationById(Long id) {
        return consultationRepository.findById(id).orElseThrow(() -> new RuntimeException("Consultation not found"));
    }

    @Override
    public List<ConsultationModel> getConsultationByPatientId(Long patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    @Override
    public List<ConsultationModel> getConsultationByGuardianId(Long guardianId) {
        // return consultationRepository.findByGuardianId(guardianId);
        return null;
    }

    @Override
    public ConsultationModel createConsultation(ConsultationModel consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public ConsultationModel updateConsultation(Long id, ConsultationModel updatedConsultation) {
        ConsultationModel consultation = consultationRepository.findById(id).orElseThrow(() -> new RuntimeException("Consultation not found"));
        consultation.setGuardiansIds(updatedConsultation.getGuardiansIds());
        consultation.setSymptoms(updatedConsultation.getSymptoms());
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }
}