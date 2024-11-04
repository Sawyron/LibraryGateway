package org.sawyron.librarygateway;

import org.sawyron.librarygateway.books.properties.BookRabbitMqRouteProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BookRabbitMqRouteProperties.class)
public class LibraryGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryGatewayApplication.class, args);
    }

}
