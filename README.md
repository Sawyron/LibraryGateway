# Library gateway

Custom application properties:

```yaml
book-rabbitmq:
  exchange: book-direct-exchange
  createRoute: book-create-route
  updateRoute: book-update-route
  deleteRoute: book-delete-rout
book-service:
  url: url
```

Related microservices:

- [Book service](https://github.com/Sawyron/LibraryBookService)

Docker compose example:

```yaml
services:
  library-gateway:
    build: https://github.com/Sawyron/LibraryGateway.git
    ports:
      - 8080:8080
    depends_on:
      - library-book-service
    environment:
      SPRING_RABBITMQ_HOST: rabbitmq
      SPRING_RABBITMQ_USERNAME: ${RABBITMQ_USER}
      SPRING_RABBITMQ_PASSWORD: ${RABBITMQ_PASSWORD}
      BOOK_SERVICE_URL: http://library-book-service:8080/api/v1/books
  library-book-service:
    build: https://github.com/Sawyron/LibraryBookService.git
    depends_on:
      - postgres
      - rabbitmq
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/${DB_NAME}
      SPRING_DATASOURCE_USERNAME: ${DB_USER}
      SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_RABBITMQ_HOST: rabbitmq
      SPRING_RABBITMQ_USERNAME: ${RABBITMQ_USER}
      SPRING_RABBITMQ_PASSWORD: ${RABBITMQ_PASSWORD}
  rabbitmq:
    image: rabbitmq:4.0-management
    ports:
      - 15672:15672
    environment:
      RABBITMQ_DEFAULT_USER: ${RABBITMQ_USER}
      RABBITMQ_DEFAULT_PASS: ${RABBITMQ_PASSWORD}
  postgres:
    image: postgres:17
    restart: always
    shm_size: 128mb
    environment:
      POSTGRES_PASSWORD: ${DB_PASSWORD}
      POSTGRES_USER: ${DB_USER}
      POSTGRES_DB: ${DB_NAME}
```