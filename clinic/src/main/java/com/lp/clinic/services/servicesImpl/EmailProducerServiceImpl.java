package com.lp.clinic.services.servicesImpl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.lp.clinic.config.RabbitConfig;
import com.lp.clinic.models.dto.EmailService.EmailDto;
import com.lp.clinic.services.EmailProducerService;

@Component
public class EmailProducerServiceImpl implements EmailProducerService {

    private final RabbitTemplate rabbitTemplate;

    public EmailProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmail(String to, String subject, String text) {
        EmailDto email = new EmailDto(to, subject, text);
        rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_EXCHANGE, RabbitConfig.EMAIL_ROUTING_KEY, email);
        System.out.println("Email sent to email queue successfully");
    }
}