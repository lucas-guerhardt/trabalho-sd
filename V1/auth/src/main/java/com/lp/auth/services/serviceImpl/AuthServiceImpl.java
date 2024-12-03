package com.lp.auth.services.serviceImpl;

import io.jsonwebtoken.Claims;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.lp.auth.config.RabbitConfig;
import com.lp.auth.models.dto.LoginDto;
import com.lp.auth.models.dto.MessageDto;
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
        MessageDto payload = new MessageDto();
        payload.setId(loginDto.getEmail());
        payload.setEmail(loginDto.getEmail());
        payload.setPassword(loginDto.getPassword());
        payload.setError(null);

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

    private String awaitTokenFromQueue(String email) {
        MessageDto response = (MessageDto) rabbitTemplate.receiveAndConvert(RabbitConfig.TOKEN_QUEUE);

        while(response.getEmail()!=email) {
            authProducerService.sendToken(response);
            response = (MessageDto) rabbitTemplate.receiveAndConvert(RabbitConfig.TOKEN_QUEUE);
        }

        if(response.getError()!=null) {
            throw new RuntimeException(response.getError());
        }

        return response.getToken();
    }
    
}
