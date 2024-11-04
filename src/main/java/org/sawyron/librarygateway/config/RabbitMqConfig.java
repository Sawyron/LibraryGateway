package org.sawyron.librarygateway.config;

import org.sawyron.librarygateway.books.properties.BookRabbitMqRouteProperties;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public DirectExchange bookExchange(BookRabbitMqRouteProperties properties) {
        return new DirectExchange(properties.exchange());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
