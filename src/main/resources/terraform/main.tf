terraform {
  required_providers {
    docker =  {
      source = "kreuzwerker/docker"
      version = "2.11.0"
    }
  }
}

provider "docker" {
}

resource "docker_container" "web" {
  image = "nginx:latest"
  name = "web"
  ports {
    internal = 80
    external =  80
  }
}