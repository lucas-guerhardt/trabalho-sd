package com.lp.users.services.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.lp.users.config.RabbitConfig;
import com.lp.users.models.dto.MessageDto;

@Component
public class UserProducerService {

    private final RabbitTemplate rabbitTemplate;

    public UserProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPasswordHash(MessageDto message) {
        rabbitTemplate.convertAndSend(RabbitConfig.PASSWORD_EXCHANGE, RabbitConfig.PASSWORD_ROUTING_KEY, message);
    }
    
}
