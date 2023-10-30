# Docker-compose

## execute containers

Run the containers

```bash
docker-compose -f docker-compose.yml up -d
```

## Logs

Over the same folder, logs the services inside:

```bash
docker-compose -f docker-compose.yml logs -f database

docker-compose -f docker-compose.yml logs -f wiremock

docker-compose -f docker-compose.yml logs -f yourmicroservice
```

To know the names, open the docker-compose.yml and review the names inside.
