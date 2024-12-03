package com.lp.central.services.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lp.central.config.RabbitConfig;

@Component
public class GuardianConsumer {
    @RabbitListener(queues = RabbitConfig.GUARDIAN_QUEUE_NAME)
    public void receiveFromGuardianQueue(String message) {
        System.out.println("Guardian Consumer: " + message);
        //implementar a logica para enviar as informações dos responsáveis
    }
    
}
