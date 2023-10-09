# Kubernetes

## Orchestration 

![kubernetes-orchestation.jpg](_img%2Fkubernetes-orchestation.jpg)

![kubernetes-orchestation-00.jpg](_img%2Fkubernetes-orchestation-00.jpg)

![kubernetes-orchestation-01.jpg](_img%2Fkubernetes-orchestation-01.jpg)

## Basic elements

`kubelet` is the piece of code in change to handle the communication between nodes. If this process goes down, then the node can be communicated with the others in the cluster.

### Cluster Nodes

All the nodes together are shaping the kubernetes cluster:

- `master nodes`: at least two per cluster. One of them is a backup, to support the availability of the cluster if one master node goes down. The important infrastructure pieces are:
  - `api server`
  - `controller manager`
  - scheduler
  - 
- `worker nodes`: every node handles several `pods`. Every node has their own `cpu and memory` assigment. The important infrastructure pieces are `kubelet` and `pods`

![kuberneter-cluster.jpg](_img%2Fkuberneter-cluster.jpg)


### Main kubernetes components

![main-kubernetes-components.jpg](_img%2Fmain-kubernetes-components.jpg)

![main-kubernetes-components-node.jpg](_img%2Fmain-kubernetes-components-node.jpg)

![main-kubernetes-components-pod.jpg](_img%2Fmain-kubernetes-components-pod.jpg)

![main-kubernetes-components-pod-how-to.jpg](_img%2Fmain-kubernetes-components-pod-how-to.jpg)

every `pod` has their `ip`. But the pods are ephemeris, so the pod can die, and another pod is replacing the previous. So the system setup a new ip for the new pod. So the communication between pods can rely into the `ip` straightforward. To do that the pods are using the `kubernetes services`. Pods communicates each of other using `services` 

### Main kubernetes components: services

The `services` are not attached to the instance of the pod itself, are attached to the pod as something generic (as a class into the program world)

![main-kubernetes-components-services-vs-ip.jpg](_img%2Fmain-kubernetes-components-services-vs-ip.jpg)

when the pods are running, we want to access to them outside the cluster, I mean, using a browser, cli or whatever. We can expose some services and others not.

![main-kubernetes-components-service-types.jpg](_img%2Fmain-kubernetes-components-service-types.jpg)

### Main kubernetes components: ingress

and we want to access using logical names instead ip's for instance. We need a `domain name service DNS` to do achieve that.

![main-kubernetes-components-services-external-access.jpg](_img%2Fmain-kubernetes-components-services-external-access.jpg)

this is what `ingress` do for us:

![main-kubernetes-components-ingress.jpg](_img%2Fmain-kubernetes-components-ingress.jpg)

### Main kubernetes components: config map

The config is the kubernetes feature in charge to store env variables as url's. Something that can change over the time or similar. 

Sometimes the database or service name changes. So we have to rebuild everything, push it to repo and deploy. 

![main-kubernetes-components-config-map-00.jpg](_img%2Fmain-kubernetes-components-config-map-00.jpg)

It's a problem. To avoid that we can use the `config-map`. Use a var into your code, and this var is resolved into the cluster when the pod is deployed

![main-kubernetes-components-config-map-01.jpg](_img%2Fmain-kubernetes-components-config-map-01.jpg)

### Main kubernetes components: secrets

`Secrets` are the same than `config map but encoded into base64. This is not secure, you have to use another solution to encrypt to your passwords o whatever 

![main-kubernetes-components-config-secrets-00.jpg](_img%2Fmain-kubernetes-components-config-secrets-00.jpg)

![main-kubernetes-components-config-secrets-01.jpg](_img%2Fmain-kubernetes-components-config-secrets-01.jpg)

![main-kubernetes-components-config-secrets-02.jpg](_img%2Fmain-kubernetes-components-config-secrets-02.jpg)

