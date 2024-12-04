package com.lp.clinic.services;

public interface EmailProducerService {
    void sendEmail(String to, String subject, String text);
}