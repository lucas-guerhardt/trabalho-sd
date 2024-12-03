package com.lp.clinic.services.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lp.clinic.config.RabbitConfig;

@Component
public class ConsultationConsumer {
    @RabbitListener(queues = RabbitConfig.CONSULTATION_QUEUE_NAME)
    public void receiveFromConsultationQueue(String message) {
        System.out.println("Consultation Consumer: " + message);
        //implementar a logica para enviar as informações das consultas
    }
    
}
