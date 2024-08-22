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
or with command line
```bash
kubectl create namespace my-example-namespace
kubectl get namespaces
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
kubectl get namespaces
kubeclt -n my-example-namespace describe desployments
kubeclt -n my-example-namespace describe services
kubectl -n my-example-namespace get pods
kubectl get services --all-namespaces -o wide
kubectl get deployments --all-namespaces -o wide
# port forward
kubectl -n my-example-namespace port-forward hello-world-dep-66fbd4fb95-2gr77 8080:80
#
sudo lsof -i:8080
sudo kill -9 pid
```

## destroy the terraform plan
```bash
# destroy manually the pod
kubectl -n my-example-namespace scale --replicas=0 deployment/hello-world-dep
kubectl -n my-example-namespace delete pod hello-world-dep-66fbd4fb95-9bs8f --grace-period 0 --force
# 
01-terraform destroy
# say yes
kubectl get deployments --all-namespaces -o wide
kubectl get namespaces
kubectl get services --all-namespaces -o wide
kubeclt -n my-example-namespace describe desployments
kubeclt -n my-example-namespace describe services
kubectl -n my-example-namespace get pods
```
output
```bash
ubernetes_namespace.my-example-namespace: Destroying... [id=my-example-namespace]
kubernetes_manifest.helloworld-service: Destroying...
kubernetes_manifest.helloworld-service: Destruction complete after 0s
kubernetes_manifest.helloworld-deployment: Destroying...
kubernetes_manifest.helloworld-deployment: Destruction complete after 0s
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 10s elapsed]
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 20s elapsed]
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 30s elapsed]
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 40s elapsed]
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 50s elapsed]
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 1m0s elapsed]
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 1m10s elapsed]
kubernetes_namespace.my-example-namespace: Still destroying... [id=my-example-namespace, 1m20s elapsed]
```

## destroy the terraform plan
```bash
kubectl delete services hello-world-svc
kubectl delete deployment hello-world-dep
kubectl delete pod [pod_name] -n [namespace] --grace-period 0 --force
kubectl delete pod [pod_name]
#
kubectl -n my-example-namespace delete pod hello-world-dep-66fbd4fb95-9bs8f --grace-period 0 --force
kubectl get deployments --all-namespaces -o wide
```

## other terraform commands
```bash
# check the status of the Terraform state
terraform state list
# remove of the Terraform resources
terraform state rm <resource-type>.<resource-name>
```
