package com.lp.clinic.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.lp.clinic.config.RabbitConfig;

@Service
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToConsultationQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.CONSULTATION_EXCHANGE_NAME, RabbitConfig.CONSULTATION_ROUTING_KEY, message);
    }    
}