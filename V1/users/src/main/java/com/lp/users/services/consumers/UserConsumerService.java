package com.lp.users.services.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lp.users.config.RabbitConfig;
import com.lp.users.models.dto.MessageDto;
import com.lp.users.services.UserService;
import com.lp.users.services.producers.UserProducerService;

@Component
public class UserConsumerService {
    private final UserService userService;
    private final UserProducerService userProducerService;

    public UserConsumerService(UserService userService, UserProducerService userProducerService) {
        this.userService = userService;
        this.userProducerService = userProducerService;
    }

    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
    public void receiveEmail(MessageDto message) {
        System.out.println("Received message: " + message);

        if(message.getError() != null) {
            throw new RuntimeException("Error fetching password hash");
        }

        String passwordHash = userService.get(message.getEmail()).getPassword();
        MessageDto payload = new MessageDto();

        payload.setId(message.getId());
        payload.setPassword(message.getPassword());
        payload.setPasswordHash(passwordHash);

        if(passwordHash == null) {
            payload.setError("User not found");
        }

        userProducerService.sendPasswordHash(payload);
    }    
}
