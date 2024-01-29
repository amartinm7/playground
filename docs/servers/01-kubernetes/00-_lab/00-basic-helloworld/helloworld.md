# helloworld on kubernetes

## get a helloworld docker image

for instance
https://hub.docker.com/r/karthequian/helloworld
This image runs over the 80 port, I mean exposes the 80 port

Test it
```bash
docker pull karthequian/helloworld
docker run -p 8080:80 karthequian/helloworld
```
open a browser and `http://localhost:8080` a validate is running
or a curl

```bash
curl http://localhost:8080
```

## Create the deployment and the service
```yaml
cat <<EOF | sudo tee helloworld_k8s.yaml
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
---
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
EOF
```

## apply the deployment and the service
Everytime you change the previous yaml file, you have to apply the changes.
```bash
kubectl apply -f helloworld.yaml
# output
deployment.apps/hello-world-dep configured
service/hello-world-svc configured
```
## inspect the deployment and the service
```bash
# get the context
kubectl config get-contexts
# get the service configuration deployed
kubectl describe service hello-world-svc
# get the deployment configuration deployed
kubectl describe deployments.apps
# get the deployment configuration deployed
kubectl get deployment hello-world-dep
# get the pods and the worker node
kubectl get pods -o wide
# get the logs on a docker container inside a pod
kubectl logs <pod-name> -c <container-name>
# get the logs of pod with only a docker container
kubectl logs <pod-name>
```
## do a portforward to access to the pod
```bash
# get the service configuration deployed
kubectl port-forward hello-world-dep-66fbd4fb95-bbnm5 8080:80
## do a curl
curl http://localhost:8080
```
Open the browser, write down `http://localhost:8080`

## scalling replicas
Over the yaml file, setup the number of pod replicas and apply the changes `kubectl apply -f helloworld.yaml
`
```bash
spec:
  replicas: 3
```
using kubectl
```bash
# with deployment name
kubectl scale --replicas=3 deployment/hello-world-dep
# with the yaml file
kubectl scale --replicas=3 -f foo.yaml
```
## Remove deployment, service and pod
```bash
kubectl delete services hello-world
kubectl delete deployment hello-world
kubectl delete pod [pod_name] -n [namespace] --grace-period 0 --force
kubectl delete pod [pod_name]
```
