# Kafka

kafka-rest-proxy, kafka-topics-ui

```yaml
  kafka-rest-proxy:
    image: confluentinc/cp-kafka-rest:7.4.0
    hostname: kafka-rest-proxy
    ports:
      - "8082:8082"
    environment:
      KAFKA_REST_LISTENERS: http://0.0.0.0:8082/
      KAFKA_REST_SCHEMA_REGISTRY_URL: http://kafka-schema-registry:8081/
      KAFKA_REST_HOST_NAME: kafka-rest-proxy
      KAFKA_REST_BOOTSTRAP_SERVERS: kafka:29092
    depends_on:
      - zookeeper
      - kafka
      - kafka-schema-registry

  kafka-topics-ui:
    image: landoop/kafka-topics-ui:0.9.4
    hostname: kafka-topics-ui
    ports:
      - "8083:8000"
    environment:
      KAFKA_REST_PROXY_URL: "http://kafka-rest-proxy:8082/"
      PROXY: "true"
    depends_on:
      - zookeeper
      - kafka
      - kafka-schema-registry
      - kafka-rest-proxy
```