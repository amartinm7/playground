# Deploy app on minikube

## minikube starting

https://minikube.sigs.k8s.io/docs/start/

![minikube-start.jpg](_img%2Fminikube-start.jpg)

```bash
brew install minikube
which minikube
```

If `which minikube` fails after installation via brew, you may have to remove the old minikube links and link the newly installed binary:

```bash
brew unlink minikube
brew link minikube
```
Now let's get starting:

```bash
minikube start
# check the cluster
kubectl get po -A 
# or 
minikube kubectl -- get po -A
#
minikube dashboard
#
minikube stop
```


## Example

![minikube-deployment-overview-00.jpg](_img%2Fminikube-deployment-overview-00.jpg)

![minikube-deployment-overview.jpg](_img%2Fminikube-deployment-overview.jpg)

## cluster

It's a set of nodes. In the case of k8s are two master-nodes, several worker-nodes. 

If you have two clusters you can use a tool as load-balancer to route the traffic between the two clusters.

The tools to handle two or more clusters are: 

- kubefed: allows to create a virtual a whole with the clusters. So it's easy to do some changes between them.
- istio: allows to create a matrix network and route the traffic between the clusters.
- rancher tool: allows to admin and apply configurations between the clusters.

## context and namespace 

you can setup the `~/.kube/config` file to handle to k8 configuration. For instance:

```yaml
contexts:
- context:
    cluster: my-cluster
    namespace: my-namespace-pro
    user: my-user
  name: my-context
```

- `context`: is a param group of parameters to access to the cluster. The parameters are cluster name, namespace and user. You can have several context. For instance 
- `namespace`: is a logic partition inside de cluster to manage the resources and grants. For instance, to distinguish between the pre or pro pods visible to the connected user.
- `user`: the user to connect to the cluster.


## kubectl cli commands

`kubectl get pod | configmap | secret | ... -o wide` 

`kubectl get configmap -o wide`

`kubectl get secret -o wide`

`kubectl get all`

`kubectl apply -f mongo-config. yaml`

`kubectl config view` to watch the `~/.kube/config` file

`kubectl config use-context <context-name>`

`kubectl logs webapp-deployment-f8d7df85d-zf2h8 -f`

`kubectl get services -o wide`

`minikube ip`

`kubectl get node -o wide`

more examples:

```bash
kubectl create deployment mynginx --image=nginx -n=pre
kubectl create deployment myapache --image=httpd -n=pro
kubectl create deployment mycaddy --image=caddy
kubectl create deployment mytomcat --image=tomcat

kubectl get deployment

kubectl create namespace pre
kubectl create namespace pro
```

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

Every deployment file can have the `deployment` (https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)
and the `service` (https://kubernetes.io/docs/concepts/services-networking/service/). Every configuration can go in an
isolate yaml, or you can add all splitting them by --- (You can have multiple YAML configurations within 1 file
using ---)

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

## kaster k8 provider

https://www.kasten.io/product/

![kasten.jpg](_img%2Fkasten.jpg)

## Example files

minikube dashboard

![minikube-dashboard.jpg](_img%2Fminikube-dashboard.jpg)

Execute the next commands to start the dashboard, and to deploy the example files:

```bash
minikube delete --all

minikube dashboard

kubectl apply -f mongo-config.yaml
kubectl apply -f mongo-secret.yaml
kubectl apply -f mongo.yaml
kubectl apply -f webapp.yaml

kubectl rollout status deployment/mongo-deployment

kubectl get rs

kubectl scale --current-replicas=3 --replicas=1 deployment/nginx-deployment

kubectl get configmaps
kubectl describe configmaps any-config-map

kubectl get secret
kubectl describe secret any-secret

kubectl get svc
kubectl describe svc any-services

kubectl get all
minikube kubectl -- get configmap
minikube kubectl -- get secret
kubectl get pods -o wide
kubectl logs webapp-deployment-f8d7df85d-zf2h8
kubectl logs webapp-deployment-f8d7df85d-zf2h8 -f
kubectl get services -o wide
minikube ip 
kubectl get node -o wide

curl -X GET 'http://192.168.49.2:30100'

```

mongo-secret.yaml
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: mongo-secret
type: Opaque
data:
  mongo-user: bW9uZ291c2Vy
  mongo-password: bW9uZ29wYXNzd29yZA==
```

mongo-config.yaml
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: mongo-config
data:
  mongo-url: mongo-service
```

mongo.yaml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongo-deployment
  labels:
    app: mongo
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mongo
  template:
    metadata:
      labels:
        app: mongo
    spec:
      containers:
      - name: mongodb
        image: mongo:5.0
        ports:
        - containerPort: 27017
        env:
        - name: MONGO_INITDB_ROOT_USERNAME
          valueFrom:
            secretKeyRef:
              name: mongo-secret
              key: mongo-user
        - name: MONGO_INITDB_ROOT_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mongo-secret
              key: mongo-password  
---
apiVersion: v1
kind: Service
metadata:
  name: mongo-service
spec:
  selector:
    app: mongo
  ports:
    - protocol: TCP
      port: 27017
      targetPort: 27017
```

webapp.yaml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: webapp-deployment
  labels:
    app: webapp
spec:
  replicas: 1
  selector:
    matchLabels:
      app: webapp
  template:
    metadata:
      labels:
        app: webapp
    spec:
      containers:
      - name: webapp
        image: nanajanashia/k8s-demo-app:v1.0
        ports:
        - containerPort: 3000
        env:
        - name: USER_NAME
          valueFrom:
            secretKeyRef:
              name: mongo-secret
              key: mongo-user
        - name: USER_PWD
          valueFrom:
            secretKeyRef:
              name: mongo-secret
              key: mongo-password 
        - name: DB_URL
          valueFrom:
            configMapKeyRef:
              name: mongo-config
              key: mongo-url
---
apiVersion: v1
kind: Service
metadata:
  name: webapp-service
spec:
  type: NodePort
  selector:
    app: webapp
  ports:
    - protocol: TCP
      port: 3000
      targetPort: 3000
      nodePort: 30100
```

### another example

```bash
minikube delete --all

minikube start

minikube dashboard

kubectl apply -f https://k8s.io/examples/controllers/nginx-deployment.yaml

kubectl apply -f ./deploy/nginx-deployment.yaml


# two steps command: first connect to the pod
kubectl exec -it nginx-deployment-57c4449555-5frhd -c ping -- /bin/sh 

# second command: inside the pod execute
wget -O- http://localhost:80
## or 
curl  http://localhost:80


kubectl get svc -o wide

kubectl rollout status deployment/nginx-deployment

kubectl get all

###  update image and deploy
kubectl set image deployment/nginx-deployment nginx=nginx:1.16.1

kubectl rollout status deployment/nginx-deployment

kubectl get rs

kubectl get pods

kubectl logs <POD-NAME>

kubectl cluster-info

kubectl get events

kubectl config view

## delete pods. You can delete a pod, but another can be created if replica is more than 1
kubectl delete pod/nginx-deployment-cbdccf466-pgjnq 

## delete deployment and pods
kubectl delete deployment nginx-deployment --grace-period=-10

kubectl delete deployment --all --namespace=default --grace-period=-10

### scale: replicas of the pods
kubectl scale --replicas=1 deployment/nginx-deployment

kubectl scale --replicas=2 deployment/nginx-deployment

### autoscale: min, max replicas of the pods
kubectl autoscale deployment/nginx-deployment --min=10 --max=15 --cpu-percent=80

### setup memory and server
kubectl set resources deployment/nginx-deployment -c=nginx --limits=cpu=200m,memory=512Mi


### check the history
kubectl rollout history deployment/nginx-deployment

## check the history the revision number
kubectl rollout history deployment/nginx-deployment --revision=1

## Rolling Back to a Previous Revision
kubectl rollout undo deployment/nginx-deployment

## Rolling Back to a specific Revision
kubectl rollout undo deployment/nginx-deployment --to-revision=2

kubectl describe deployment nginx-deployment

## Pause deploy
kubectl rollout pause deployment/nginx-deployment

## Resume deploy
kubectl rollout resume deployment/nginx-deployment

## Watch the status of the rollout until it's done.
kubectl get rs -w
```

## deploy a nginx

for creating the ingress-controller to expose an external ip. just do it only one time, when the minikube is installed into the machine

````bash
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm install my-ingress-server ingress-nginx/ingress-nginx
````
(Not working for minikube)

```bash
minikube delete

minikube start

minikube dashboard

kubectl apply -f ./deploy/nginx-deployment.yaml

kubectl get all

kubectl get svc nginx-service

minikube ip

# here is the key: Allow external traffic starting tunnel for service nginx-service.  
minikube service nginx-service
# or
minikube tunnel

```
open a web page on the url http://127.0.0.1:61385/

![minikube-open-tunnel.jpg](_img%2Fminikube-open-tunnel.jpg)

## In real k8s: Allow external traffic

By default, the pod is only accessible by its internal IP within the Kubernetes cluster. In order to make the hello-node container accessible from outside the kubernetes virtual network, you have to expose the pod as a kubernetes service.

From our development machine we can expose the pod to the public internet using the `kubectl expose` command combined with the `--type="LoadBalancer"` flag. The flag is needed for the creation of an externally accessible ip:

```bash
kubectl expose deployment nginx-deployment --type="LoadBalancer"
```
