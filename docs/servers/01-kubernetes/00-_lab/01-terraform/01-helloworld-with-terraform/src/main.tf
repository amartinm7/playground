terraform {
  required_version = "> 1.5"
}

provider "kubernetes" {
  config_path = "~/.kube/config"
  config_context = "kubernetes-admin@kubernetes"
}

resource "kubernetes_namespace" "my-example-namespace" {
  metadata {
    name = "my-example-namespace"
  }
}

resource "kubernetes_manifest" "helloworld-deployment" {
  manifest = yamldecode(file("${path.module}/helloworld_deployment.yaml"))
}

resource "kubernetes_manifest" "helloworld-service" {
  manifest = yamldecode(file("${path.module}/helloworld_service.yaml"))
}