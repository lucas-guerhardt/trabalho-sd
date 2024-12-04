package com.lp.clinic.services.servicesImpl;

import java.util.Objects;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.lp.clinic.config.RabbitConfig;
import com.lp.clinic.models.dto.EmailService.ResponseDto;
import com.lp.clinic.services.ResponseConsumerService;

public class ResponseConsumerServiceImpl implements ResponseConsumerService {
    @RabbitListener(queues = RabbitConfig.RESPONSE_QUEUE)
    public void consumeResponse(ResponseDto response) {
        if (Objects.isNull(response.getError())) {
            System.out.println("Email sent to " + response.getEmail() + " successfully");
        } else {
            System.out.println("Error sending email to " + response.getEmail() + ": " + response.getError());
        }
    }
}