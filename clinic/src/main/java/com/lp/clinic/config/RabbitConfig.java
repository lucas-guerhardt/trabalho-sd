package com.lp.clinic.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EMAIL_QUEUE = "email.queue";
    public static final String EMAIL_EXCHANGE = "email.exchange";
    public static final String EMAIL_ROUTING_KEY = "email.routing.key";

    public static final String RESPONSE_QUEUE = "response.queue";
    public static final String RESPONSE_EXCHANGE = "response.exchange";
    public static final String RESPONSE_ROUTING_KEY = "response.routing.key";
}