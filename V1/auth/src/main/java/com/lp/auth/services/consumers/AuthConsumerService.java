package com.lp.auth.services.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lp.auth.config.RabbitConfig;
import com.lp.auth.models.dto.MessageDto;
import com.lp.auth.services.producers.AuthProducerService;
import com.lp.auth.utils.JwtUtil;

@Component
public class AuthConsumerService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthProducerService authProducerService;
    
    public AuthConsumerService(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthProducerService authProducerService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authProducerService = authProducerService;
    }

    @RabbitListener(queues = RabbitConfig.PASSWORD_QUEUE)
    public void receivePasswordHash(MessageDto message) {
        System.out.println("Received message from password queue: " + message);
        MessageDto payload = new MessageDto();
        payload.setId(message.getId());
        
        if(message.getError() != null) {
            payload.setError(message.getError());
        }
        
        if (!passwordEncoder.matches(message.getPassword(), message.getPasswordHash())) {
            payload.setError("Invalid email or password");
        }
        
        String token = jwtUtil.generateToken(message.getId());
        payload.setToken(token);

        System.out.println("#############################");
        System.out.println(payload.getId());
        System.out.println(payload.getToken());
        System.out.println("#############################");
        authProducerService.sendToken(payload);
    }
}