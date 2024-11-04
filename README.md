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