package com.lp.clinic.services.servicesImpl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lp.clinic.config.RabbitConfig;
import com.lp.clinic.models.dto.EmailService.ResponseDto;
import com.lp.clinic.services.ResponseConsumerService;

@Component
public class ResponseConsumerServiceImpl implements ResponseConsumerService {

    @Override
    @RabbitListener(queues = RabbitConfig.RESPONSE_QUEUE)
    public void consumeResponse(ResponseDto response) {
        if (response.getError() == null) {
            System.out.println("Email sent to " + response.getEmail() + " successfully");
        } else {
            System.out.println("Error sending email to " + response.getEmail() + ": " + response.getError());
        }
    }
}