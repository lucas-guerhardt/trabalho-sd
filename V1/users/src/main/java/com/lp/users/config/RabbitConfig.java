package com.lp.users.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String PASSWORD_QUEUE = "password.queue";
    public static final String PASSWORD_EXCHANGE = "password.exchange";
    public static final String PASSWORD_ROUTING_KEY = "password.routing.key";

    public static final String VALIDATION_QUEUE = "validation.queue";
    public static final String VALIDATION_EXCHANGE = "validation.exchange";
    public static final String VALIDATION_ROUTING_KEY = "validation.routing.key";

    public static final String EMAIL_QUEUE = "email.queue";
    public static final String EMAIL_EXCHANGE = "email.exchange";
    public static final String EMAIL_ROUTING_KEY = "email.routing.key";

    public static final String TOKEN_QUEUE = "token.queue";
    public static final String TOKEN_EXCHANGE = "token.exchange";
    public static final String TOKEN_ROUTING_KEY = "token.routing.key";

    @Bean
    @Qualifier(PASSWORD_QUEUE)
    public Queue passwordQueue() {
        return new Queue(PASSWORD_QUEUE, true);
    }

    @Bean
    public DirectExchange passwordExchange() {
        return new DirectExchange(PASSWORD_EXCHANGE);
    }

    @Bean
    public Binding passwordBinding(Queue passwordQueue, DirectExchange passwordExchange) {
        return BindingBuilder.bind(passwordQueue).to(passwordExchange).with(PASSWORD_ROUTING_KEY);
    }
    
    @Bean
    @Qualifier(VALIDATION_QUEUE)
    public Queue validationQueue() {
        return new Queue(VALIDATION_EXCHANGE, true);
    }

    @Bean
    public DirectExchange validationExchange() {
        return new DirectExchange(VALIDATION_EXCHANGE);
    }

    @Bean
    public Binding validationBinding(Queue validationQueue, DirectExchange validationExchange) {
        return BindingBuilder.bind(validationQueue).to(validationExchange).with(VALIDATION_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

