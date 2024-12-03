package com.lp.central.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String PET_QUEUE_NAME = "pet.queue";
    public static final String PET_EXCHANGE_NAME = "pet.exchange";
    public static final String PET_ROUTING_KEY = "pet.routing.key";

    @Bean
    public Queue petQueue() {
        return new Queue(PET_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange petExchange() {
        return new DirectExchange(PET_EXCHANGE_NAME);
    }

    @Bean
    public Binding petBinding(Queue petQueue, DirectExchange petExchange) {
        return BindingBuilder.bind(petQueue).to(petExchange).with(PET_ROUTING_KEY);
    }
    
    public static final String GUARDIAN_QUEUE_NAME = "guardian.queue";
    public static final String GUARDIAN_EXCHANGE_NAME = "guardian.exchange";
    public static final String GUARDIAN_ROUTING_KEY = "guardian.routing.key";

    @Bean
    public Queue guardianQueue() {
        return new Queue(GUARDIAN_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange guardianExchange() {
        return new DirectExchange(GUARDIAN_EXCHANGE_NAME);
    }

    @Bean
    public Binding guardianBinding(Queue guardianQueue, DirectExchange guardianExchange) {
        return BindingBuilder.bind(guardianQueue).to(guardianExchange).with(GUARDIAN_ROUTING_KEY);
    }
}

