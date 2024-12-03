package com.lp.users.services.producers;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.lp.users.config.RabbitConfig;

@Component
public class UserProducerService {

    private final RabbitTemplate rabbitTemplate;

    public UserProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPasswordHash(Map<String,String> message) {
        rabbitTemplate.convertAndSend(RabbitConfig.PASSWORD_EXCHANGE, RabbitConfig.PASSWORD_ROUTING_KEY, message);
    }
    
}
