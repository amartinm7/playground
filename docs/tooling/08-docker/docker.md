# Docker

![docker_components.jpeg](_img%2Fdocker_components.jpeg)

## using environment vars

docker reads your system environment by default. Use `printenv` to see your env vars.

But if you want to fix the env vars for a docker file, you can create an `.env` file to setup it.

create an `.env` file with the vars, like this

```bash
DB_CONNECTION=mysql
DB_HOST=127.0.0.1
DB_PORT=3306
DB_DATABASE=your_database_name
DB_USERNAME=root
DB_PASSWORD=
```

use the vars into the docker-compose.yml, and when the docker-compose starts, automatically reads the file

```bash
    mysql:
        image: 'mysql/mysql-server:8.0'
        ports:
            - '${FORWARD_DB_PORT:-3306}:3306'
        environment:
            MYSQL_ROOT_PASSWORD: '${DB_PASSWORD}'
            MYSQL_ROOT_HOST: "%"
            MYSQL_DATABASE: '${DB_DATABASE}'
            MYSQL_USER: '${DB_USERNAME}'
            MYSQL_PASSWORD: '${DB_PASSWORD}'
            MYSQL_ALLOW_EMPTY_PASSWORD: 1
        volumes:
            - 'sail-mysql:/var/lib/mysql'
            - './vendor/laravel/sail/database/mysql/create-testing-database.sh:/docker-entrypoint-initdb.d/10-create-testing-database.sh'
        networks:
            - sail
        healthcheck:
            test: ["CMD", "mysqladmin", "ping", "-p${DB_PASSWORD}"]
            retries: 3
            timeout: 5s
```
## Docker ps

docker ps -a -q
docker rm pid

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

## docker-compose

docker-compose up -d
docker-compose -f docker-compose-xxx.yml up -d
docker-compose down

## docker volumen

docker volume prune
docker system prune 

docker volume rm $(docker volume ls -q --filter dangling=true)

docker volume rm $(docker volume ls -qf dangling=true)
docker volume rm pid

## docker image

- Remove an image
docker rmi image_name:version/image-id

- Remove all images
docker rmi $(docker images -qf "dangling=true")

- Remove all images except "my-image"
docker rmi $(docker images | grep -v 'ubuntu\|my-image' | awk {'print $3'})

- Remove all images except "my-image"
docker rmi $(docker images --quiet | grep -v $(docker images --quiet ubuntu:my-image))

## docker containers

- Kill containers and remove them
  docker rm $(docker kill $(docker ps -aq))

- stop containers and remove them
  docker rm $(docker stop $(docker ps -aq))

- Remove all containers
  docker rm $(docker ps -a -q)

## docker run

- run a container
docker container run --rm -it alpine ping 8.8.8.8

## docker network

- disconnect a network from a container
docker network disconnect bridge <container-name>

- Remove one or more networks
docker network rm pid 

- list networks
  docker network ls
