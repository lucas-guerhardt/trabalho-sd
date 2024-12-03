package com.lp.auth.services.consumers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lp.auth.config.RabbitConfig;
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
    public void receivePasswordHash(Map<String,String> message) {
        System.out.println("Received message from password queue: " + message);
        Map<String,String> payload = new HashMap<>();
        payload.put("id", message.get("id"));
        
        if(message.get("error") != null) {
            payload.put("error", message.get("error"));
        }
        
        if (!passwordEncoder.matches(message.get("password"), message.get("passwordHash"))) {
            payload.put("error", "Invalid email or password");
        }
        
        String token = jwtUtil.generateToken(message.get("id"));
        payload.put("token", token);
        payload.put("error", null);
        authProducerService.sendToken(payload);
    }
}