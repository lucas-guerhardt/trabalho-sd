package com.lp.clinic.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String CONSULTATION_QUEUE_NAME = "consultation.queue";
    public static final String CONSULTATION_EXCHANGE_NAME = "consultation.exchange";
    public static final String CONSULTATION_ROUTING_KEY = "consultation.routing.key";

    @Bean
    public Queue consultationQueue() {
        return new Queue(CONSULTATION_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange consultationExchange() {
        return new DirectExchange(CONSULTATION_EXCHANGE_NAME);
    }

    @Bean
    public Binding consultationBinding(Queue consultationQueue, DirectExchange consultationExchange) {
        return BindingBuilder.bind(consultationQueue).to(consultationExchange).with(CONSULTATION_ROUTING_KEY);
    }
    
}
