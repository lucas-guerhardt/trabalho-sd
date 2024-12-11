package com.lp.central.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EMAIL_QUEUE = "email.queue";
    public static final String EMAIL_EXCHANGE = "email.exchange";
    public static final String EMAIL_ROUTING_KEY = "email.routing.key";

    public static final String RESPONSE_QUEUE = "response.queue";
    public static final String RESPONSE_EXCHANGE = "response.exchange";
    public static final String RESPONSE_ROUTING_KEY = "response.routing.key";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationRunner initializeRabbit(RabbitAdmin rabbitAdmin) {
        return _ -> {
            rabbitAdmin.declareQueue(new Queue(EMAIL_QUEUE, true));
            rabbitAdmin.declareQueue(new Queue(RESPONSE_QUEUE, true));

            rabbitAdmin.declareExchange(new DirectExchange(EMAIL_EXCHANGE));
            rabbitAdmin.declareExchange(new DirectExchange(RESPONSE_EXCHANGE));

            rabbitAdmin.declareBinding(
                    BindingBuilder.bind(new Queue(EMAIL_QUEUE, true))
                            .to(new DirectExchange(EMAIL_EXCHANGE))
                            .with(EMAIL_ROUTING_KEY));

            rabbitAdmin.declareBinding(
                    BindingBuilder.bind(new Queue(RESPONSE_QUEUE, true))
                            .to(new DirectExchange(RESPONSE_EXCHANGE))
                            .with(RESPONSE_ROUTING_KEY));
        };
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
