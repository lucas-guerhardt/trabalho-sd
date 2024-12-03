package com.lp.auth.services.serviceImpl;

import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.lp.auth.config.RabbitConfig;
import com.lp.auth.models.dto.LoginDto;
import com.lp.auth.services.AuthService;
import com.lp.auth.services.producers.AuthProducerService;
import com.lp.auth.utils.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final JwtUtil jwtUtil;
    private final AuthProducerService authProducerService;
    private final RabbitTemplate rabbitTemplate;
    
    public AuthServiceImpl(JwtUtil jwtUtil, AuthProducerService authProducerService, RabbitTemplate rabbitTemplate) {
        this.jwtUtil = jwtUtil;
        this.authProducerService = authProducerService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String login(LoginDto loginDto) {
        Map<String, String> payload = new HashMap<>();
        payload.put("id", loginDto.getEmail());
        payload.put("email", loginDto.getEmail());
        payload.put("password", loginDto.getPassword());
        payload.put("error", null);

        authProducerService.sendEmail(payload);
        
        return awaitTokenFromQueue(loginDto.getEmail());
    }

    @Override
    public String logout(String token) {
        return null;
    }

    @Override
    public String validateToken(String token) {
        try {
            Claims claims = jwtUtil.extractClaims(token);
            return claims.getSubject();
        } catch (Exception e) {
            return "Invalid or expired token";
        }
    }

    @SuppressWarnings("unchecked")
    private String awaitTokenFromQueue(String email) {
        Map<String, String> response = (Map<String, String>) rabbitTemplate.receiveAndConvert(RabbitConfig.TOKEN_QUEUE);

        while(response.get("id")!=email) {
            authProducerService.sendToken(response);
            response = (Map<String, String>) rabbitTemplate.receiveAndConvert(RabbitConfig.TOKEN_QUEUE);
        }

        if(response.get("error")!=null) {
            throw new RuntimeException(response.get("error"));
        }

        return response.get("token");
    }
    
}
