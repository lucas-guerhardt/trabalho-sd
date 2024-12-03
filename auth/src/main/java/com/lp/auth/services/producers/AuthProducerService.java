package com.lp.auth.services.producers;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.lp.auth.config.RabbitConfig;

@Component
public class AuthProducerService {

    private final RabbitTemplate rabbitTemplate;

    public AuthProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmail(Map<String,String> email) {
        // Send message to auth exchance
        rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_EXCHANGE, RabbitConfig.EMAIL_ROUTING_KEY, email);
        System.out.println("Message sent to email queue: " + email.get("id"));
    }

    public void sendToken(Map<String,String> token) {
        // Send message to token exchange
        rabbitTemplate.convertAndSend(RabbitConfig.TOKEN_EXCHANGE, RabbitConfig.TOKEN_ROUTING_KEY, token);
        System.out.println("Message sent to token queue: " + token.get("id"));
    }
}
