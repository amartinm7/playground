# Deploy app on minikube

## minikube starting

https://minikube.sigs.k8s.io/docs/start/

![minikube-start.jpg](_img%2Fminikube-start.jpg)

## Example

![minikube-deployment-overview-00.jpg](_img%2Fminikube-deployment-overview-00.jpg)

![minikube-deployment-overview.jpg](_img%2Fminikube-deployment-overview.jpg)

## kubectl cli commands

kubectl get pod | configmap | secret | .

kubectl get configmap

kubectl get secret

kubectl get all

kubectl apply -f mongo-config. yaml

### get pods by service name

![minikube-kubectl-get-service.jpg](_img%2Fminikube-kubectl-get-service.jpg)

![minikube-kubectl-get-pod-02.jpg](_img%2Fminikube-kubectl-get-pod-02.jpg)

### get pods logs by service name

![minikube-kubectl-get-pod-01.jpg](_img%2Fminikube-kubectl-get-pod-01.jpg)

### kubectl describe pod service name

show the deployment descriptor of the pod

## Creating deployment files

### Deployment yaml

![minikube-deployment-file-01.jpg](_img%2Fminikube-deployment-file-01.jpg)

![minikube-deployment-file.jpg](_img%2Fminikube-deployment-file.jpg)

Every deployment file can have the `deployment` (https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) and the `service` (https://kubernetes.io/docs/concepts/services-networking/service/). Every configuration can go in an isolate yaml, or you can add all splitting them by --- (You can have multiple YAML configurations within 1 file using ---)

![minikube-deployment-file.jpg](_img%2Fminikube-deployment-file.jpg)

- `replicas` how many Pods you want to create?
- `label` how to identify to the pods (Which Pods belongs to
  Deployment? the label is in charge of that)
- `containerPort` the port exposed by the docker container

![minikube-deployment-label.jpg](_img%2Fminikube-deployment-label.jpg)

![minikube-deployment-label-02.jpg](_img%2Fminikube-deployment-label-02.jpg)

![minikube-deployment-label-03.jpg](_img%2Fminikube-deployment-label-03.jpg)

### Service yaml

![kubernetes-service-documentation.jpg](_img%2Fkubernetes-service-documentation.jpg)

![minikube-deployment-service.jpg](_img%2Fminikube-deployment-service.jpg)

in the service part:

- `targetPort` is the same of the `containerPort` on the `deployment` file.
- `nodeport` is the external exposed port

![minikube-kubectl-how-to-expose-external-ip-04.jpg](_img%2Fminikube-kubectl-how-to-expose-external-ip-04.jpg)

![minikube-kubectl-how-to-expose-external-ip-02.jpg](_img%2Fminikube-kubectl-how-to-expose-external-ip-02.jpg)



### kubectl deployment: using config-map and secrets

![minikube-kubectl-how-to-expose-external-ip-05.jpg](_img%2Fminikube-kubectl-how-to-expose-external-ip-05.jpg)

- `selector`: select pods to forward the requests to. 

![minikube-deployment-setup-secrets-02.jpg](_img%2Fminikube-deployment-setup-secrets-02.jpg)

![minikube-deployment-setup-config-map.jpg](_img%2Fminikube-deployment-setup-config-map.jpg)

![minikube-deployment-setup-secrets.jpg](_img%2Fminikube-deployment-setup-secrets.jpg)

![minikube-deployment-setup-secrets-01.jpg](_img%2Fminikube-deployment-setup-secrets-01.jpg)

![minikube-deployment-setup-config-map-file-02.jpg](_img%2Fminikube-deployment-setup-config-map-file-02.jpg)

![minikube-deployment-setup-secret-file.jpg](_img%2Fminikube-deployment-setup-secret-file.jpg)

![minikube-deployment-setup-secret-base64.jpg](_img%2Fminikube-deployment-setup-secret-base64.jpg)

![minikube-deployment-setup-config-map-file.jpg](_img%2Fminikube-deployment-setup-config-map-file.jpg)

### kubectl apply deployment

![minikube-deployment-example.jpg](_img%2Fminikube-deployment-example.jpg)

![minikube-kubectl-apply-deployment.jpg](_img%2Fminikube-kubectl-apply-deployment.jpg)

### exposing external ip

![minikube-kubectl-how-to-expose-external-ip-00.jpg](_img%2Fminikube-kubectl-how-to-expose-external-ip-00.jpg)

![minikube-kubectl-how-to-expose-external-ip-01.jpg](_img%2Fminikube-kubectl-how-to-expose-external-ip-01.jpg)

The ip is the cluster ip, is the ip of minikube in this case.

![minikube-kubectl-get-node-expose-external-ip.jpg](_img%2Fminikube-kubectl-get-node-expose-external-ip.jpg)

![minikube-kubectl-get-node-expose-external-ip-01.jpg](_img%2Fminikube-kubectl-get-node-expose-external-ip-01.jpg)

![minikube-kubectl-get-pod-00.jpg](_img%2Fminikube-kubectl-get-pod-00.jpg)

![minikube-kubectl-how-to-expose-external-ip-03.jpg](_img%2Fminikube-kubectl-how-to-expose-external-ip-03.jpg)