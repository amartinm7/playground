# helloworld with terraform

## create the deployment kubernetes file
```bash
cat <<EOT >> helloworld-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: hello-world-dep
  namespace: my-example-namespace
spec:
  replicas: 1
  selector:
    matchLabels:
      app: hello-world
  template:
    metadata:
      labels:
        app: hello-world
    spec:
      containers:
      - name: hello-world
        image: karthequian/helloworld:latest
        ports:
        - containerPort: 80
EOT
```

## create the service kubernetes file
```bash
cat <<EOT >> helloworld-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: hello-world-svc
  namespace: my-example-namespace
spec:
  selector:
    app: hello-world
  ports:
  - name: http
    port: 8080
    targetPort: 80
  type: ClusterIP
EOT
```

## create the kubernetes namespace file
```bash
cat <<EOT >> my-example-namespace.yaml
apiVersion: v1
kind: Namespace
metadata:
  name: my-example-namespace
EOT
```

## create the terraform main file
Setup the kubernetes config with the `~/.kube/config` and the `kubectl config get-contexts`
```bash
cat <<EOT >> main.tf
terraform {
  required_version = "> 1.5"
}

provider "kubernetes" {
  config_path = "~/.kube/config"
  config_context = "kubernetes-admin@kubernetes"
}

# create namespace for the pods
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
EOT
```

## execute terraform
```bash
terraform init
terraform apply
# say yes
kubectl config get-contexts
kubeclt -n my-example-namespace describe desployments
kubeclt -n my-example-namespace describe services
kubectl -n my-example-namespace get pods
terraform destroy
```


