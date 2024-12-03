package com.lp.central.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.lp.central.config.RabbitConfig;

@Service
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToPetQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.PET_EXCHANGE_NAME, RabbitConfig.PET_ROUTING_KEY, message);
    }

    public void sendToGuardianQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.GUARDIAN_EXCHANGE_NAME, RabbitConfig.GUARDIAN_ROUTING_KEY, message);
    }
    
}
