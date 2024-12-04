package com.lp.email.services.servicesImpl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.lp.email.config.RabbitConfig;
import com.lp.email.models.dto.ResponseDto;
import com.lp.email.services.ResponseProducerService;

@Component
public class ResponseProducerServiceImpl implements ResponseProducerService {

    private final RabbitTemplate rabbitTemplate;

    public ResponseProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendResponse(ResponseDto response) {
        rabbitTemplate.convertAndSend(RabbitConfig.RESONSE_EXCHANGE, RabbitConfig.RESPONSE_ROUTING_KEY, response);
        System.out.println("Sending response: " + response);
    }
}