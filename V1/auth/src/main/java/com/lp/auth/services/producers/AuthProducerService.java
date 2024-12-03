package com.lp.auth.services.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.lp.auth.config.RabbitConfig;
import com.lp.auth.models.dto.MessageDto;

@Component
public class AuthProducerService {

    private final RabbitTemplate rabbitTemplate;

    public AuthProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmail(MessageDto email) {
        // Send message to auth exchance
        rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_EXCHANGE, RabbitConfig.EMAIL_ROUTING_KEY, email);
        System.out.println("Message sent to email queue: " + email.getEmail());
    }

    public void sendToken(MessageDto token) {
        // Send message to token exchange
        rabbitTemplate.convertAndSend(RabbitConfig.TOKEN_EXCHANGE, RabbitConfig.TOKEN_ROUTING_KEY, token);
        System.out.println("Message sent to token queue: " + token.getToken());
    }
}
