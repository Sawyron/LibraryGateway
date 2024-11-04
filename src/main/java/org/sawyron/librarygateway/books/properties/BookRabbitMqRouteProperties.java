package org.sawyron.librarygateway.books.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "book-rabbitmq")
public record BookRabbitMqRouteProperties(
        String exchange,
        String createRoute,
        String updateRoute,
        String deleteRoute) {
}
