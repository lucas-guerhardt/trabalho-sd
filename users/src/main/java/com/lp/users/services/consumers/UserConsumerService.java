package com.lp.users.services.consumers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lp.users.config.RabbitConfig;
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
    public void receiveEmail(Map<String, String> message) {
        System.out.println("#################################################################### Received message: " + message);

        if(message.get("error") != null) {
            throw new RuntimeException("Error fetching password hash");
        }

        String passwordHash = userService.get(message.get("email")).getPassword();
        Map<String, String> payload = new HashMap<>();

        payload.put("id", message.get("email"));
        payload.put("password", message.get("password"));
        payload.put("passwordHash", passwordHash);

        if(passwordHash == null) {
            payload.put("error", "User not found");
        }

        userProducerService.sendPasswordHash(payload);
    }    
}
