terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "3.0.2"
    }
  }
}

provider "docker" {
  host = "unix:///var/run/docker.sock"
}

resource "docker_container" "web" {
  image = "nginx:latest"
  name = "web"
  ports {
    internal = 80
    external =  80
  }
}
