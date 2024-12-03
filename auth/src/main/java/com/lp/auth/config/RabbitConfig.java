package com.lp.auth.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitConfig {

    public static final String EMAIL_QUEUE = "email.queue";
    public static final String EMAIL_EXCHANGE = "email.exchange";
    public static final String EMAIL_ROUTING_KEY = "email.routing.key";

    public static final String TOKEN_QUEUE = "token.queue";
    public static final String TOKEN_EXCHANGE = "token.exchange";
    public static final String TOKEN_ROUTING_KEY = "token.routing.key";

    public static final String PASSWORD_QUEUE = "password.queue";
    public static final String PASSWORD_EXCHANGE = "password.exchange";
    public static final String PASSWORD_ROUTING_KEY = "password.routing.key";

    public static final String VALIDATION_QUEUE = "validation.queue";
    public static final String VALIDATION_EXCHANGE = "validation.exchange";
    public static final String VALIDATION_ROUTING_KEY = "validation.routing.key";

    @Bean
    @Qualifier(EMAIL_QUEUE)
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    @Qualifier(TOKEN_QUEUE)
    public Queue tokenQueue() {
        return new Queue(TOKEN_QUEUE, true);
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(EMAIL_EXCHANGE);
    }

    @Bean
    public DirectExchange tokenExchange() {
        return new DirectExchange(TOKEN_EXCHANGE);
    }

    @Bean
    public Binding emailBinding(@Qualifier(EMAIL_QUEUE)Queue emailQueue, DirectExchange emailExchange) {
        return BindingBuilder.bind(emailQueue).to(emailExchange).with(EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding tokenBinding(@Qualifier(TOKEN_QUEUE)Queue tokenQueue, DirectExchange tokenExchange) {
        return BindingBuilder.bind(tokenQueue).to(tokenExchange).with(TOKEN_ROUTING_KEY);
    }
}