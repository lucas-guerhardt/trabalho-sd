package com.lp.central.services.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lp.central.config.RabbitConfig;

@Component
public class PetConsumer {
    @RabbitListener(queues = RabbitConfig.PET_QUEUE_NAME)
    public void receiveFromPetQueue(String message) {
        System.out.println("Pet Consumer: " + message);
        //implementar a logica para enviar as informações dos pets
    }
}
