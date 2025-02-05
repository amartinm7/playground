# Kubernetes Objects

## intro

- `Pods`, or groups of containers, can group together container images developed by different teams into a single deployable unit.

- Kubernetes `services` provide load balancing, naming, and discovery to isolate one microservice from another.

- `Namespaces` provide isolation and access control, so that each microservice can control the degree to which other services interact with it.

- `Ingress objects provide an easy-to-use frontend that can combine multiple microservices into a single externalized API surface area.

## Cluster

Cluster: A set of worker machines, called nodes, that run containerized applications. Every cluster has at least one worker node. A cluster also has a control plane that runs services that manage the cluster.

![kubernetes-objects-cluster.jpg](_img%2Fkubernetes-objects-cluster.jpg)

## Node

Node: Kubernetes runs your workload by grouping containers into pods and assigning those pods to run on nodes. A node can be a virtual or physical machine, depending on the cluster. Each node is managed by the control plane and contains the services necessary to run pods.

![kubernetes-objects-node.jpg](_img%2Fkubernetes-objects-node.jpg)

## Pod

Pod: A group of one or more containers. Pods are defined by a PodSpec file, a specification for how to run the containers. Pods are the basic building block within Kubernetes for deployment, scaling, and replication.

```bash
kubectl get pods -o wide
```

![kubernetes-objects-pod.jpg](_img%2Fkubernetes-objects-pod.jpg)



## Volume

Ephemeral volume: Applications in a pod have access to shared volumes to facilitate data sharing in the pod and persistence of data across container restarts. When a pod ceases to exist, Kubernetes destroys ephemeral volumes.

Persistent volume: A persistent volume functions similarly to an ephemeral volume but has a lifecycle independent of any individual pod that uses them. Persistent volumes are backed by storage subsystems independent of cluster nodes.

![kubernetes-objects-volume.jpg](_img%2Fkubernetes-objects-volume.jpg)

## Service

Service: In Kubernetes, a service is a logical collection of pods and a means to access them. The service is continually updated with the set of pods available, eliminating the need for pods to track other pods.

![kubernetes-objects-service.jpg](_img%2Fkubernetes-objects-service.jpg)

## Namespace

Namespace: A virtual cluster that is backed by the same physical cluster. Physical clusters can have resources with the same name as long as they are in different namespaces. Namespaces are especially useful when you have multiple teams or projects using the same cluster.

```bash
kubectl get namespaces -o wide
```

![kubernetes-objects-namespace.jpg](_img%2Fkubernetes-objects-namespace.jpg)

## Replicaset

ReplicaSet: Ensures that a specific number of pod replicas are running at any given time.

![kubernetes-objects-replicaset.jpg](_img%2Fkubernetes-objects-replicaset.jpg)

## Deployment

Deployment: Owns and manages ReplicaSets or individual pods. You describe a desired state in the deployment. The deployment then changes the actual state of the cluster to the desired state at a controlled rate.

```bash
kubectl apply -f nginx-deploy.yaml
```

![kubernetes-objects-deployment.jpg](_img%2Fkubernetes-objects-deployment.jpg)

## Secrets and configmap

ConfigMap: A ConfigMap is an API object that stores nonconfidential data as key-value pairs used by other Kubernetes objects, such as pods. Use ConfigMaps to follow the best practice of portability by separating your configuration data from your application code.

Secrets: All confidential data, such as AWS credentials, should be stored as Kubernetes secrets. Secrets restrict access to sensitive information. Optionally, encryption can be turned on to improve security.

![kubernetes-objects-secrets-and-configmap.jpg](_img%2Fkubernetes-objects-secrets-and-configmap.jpg)

## Pod scheduling

Pod scheduling

You can schedule pods with the Kubernetes scheduler. The scheduler checks the resources required by your pods and uses that information to influence the scheduling decision. The scheduler runs a series of filters to exclude ineligible nodes for pod placement

![kubernetes-objects-pod-scheduling.jpg](_img%2Fkubernetes-objects-pod-scheduling.jpg)

## Control plane and data plane

`Control plane`: Control plane nodes manage the worker nodes and the pods in the cluster.

`Data plane`: Worker nodes host the pods that are the components of the application workload.

![kubernetes-objects-planing.jpg](_img%2Fkubernetes-objects-planing.jpg)

Control/data plane communication: 
Communication between the `control plane` and `worker nodes` is done through the `API server` to `kubelet`.

### Control plane

The `control plane` determines when tasks are scheduled and where they are routed to.

- The `control plane` includes:
  - Control plane nodes
  - Controller manager
  - Cloud controller o Scheduler
  - API server
  - etcd

The `controller manager` runs background threads called controllers that detect and
respond to cluster events.

The `cloud controller` is a specific controller that interacts with the underlying cloud provider.

The `scheduler` selects nodes for newly created containers to run on.

The `Kubernetes API server` exposes the Kubernetes API and is the frontend for the Kubernetes control plane. &nbsp;It handles all communication from the cluster to the control plane; none of the other control plane components are designed to expose remote services. The Kubernetes API server is designed to scale horizontally, deploying more instances as necessary

`Etcd` is the core persistence layer for Kubernetes. It is a highly available distributed key value store. This is where the critical cluster data and state are stored.

### Data plane

In Kubernetes, the data plane is where the tasks are run. This is all done on your worker nodes.

The data plane includes

- worker nodes: 
  - kube-proxy
  - container runtime
  - kubelet
  - pods

`kube-proxy` helps with networking. It maintains network rules on the host and performs any connection forwarding that may be necessary.

`container runtime` Kubernetes supports several runtimes, with Docker being the most common.

`kubelet` is the primary agent that runs on the worker nodes. Kubelet makes sure that the right containers are running in a pod and that they are healthy.

A `Pod` is a group of one or more containers. The containers in a pod are always colocated, scheduled together, and managed together; you cannot split containers in a pod across nodes. Applications in a pod can easily communicate with each other. Like individual application containers, pods are considered to be relatively ephemeral (rather than durable) entities. This means that pods can disappear if they become unhealthy, and new ones can take their place.

## kubectl

You can communicate with your control plane nodes using `kubectl`. `kubectl` is a command line interface (CLI) for communicating with the Kubernetes API server. It provides commands to create resources, view detailed information about the cluster and resources, and access troubleshooting tools. kubectl commands are used to roll out, scale, and automatically scale resources. 

Syntax:

```bash
kubectl [command] [TYPE] [NAME] [flags]
```

- Command: Specifies the operation you are performing.
- Type: Specifies the resource type.
- Name: Specifies the name of the resource.
- Flag: Specifies optional flags.

## EKS API

The Amazon EKS control plane consists of at least two API server nodes and three etcd nodes across three Availability Zones. Amazon EKS automatically detects and replaces unhealthy control plane nodes, which removes a significant operational burden for running Kubernetes. With this capability, you can focus on building your applications instead of managing AWS infrastructure.

To get started with Amazon EKS, you provision your cluster of worker nodes. Amazon EKS handles the provisioning, scaling, and management of the Kubernetes control plane in a highly available and secure configuration. You then connect to the Amazon EKS cluster using the graphical or command line interface. After you’ve connected to the Amazon EKS cluster, you’re ready to deploy your Kubernetes applications to your Amazon EKS cluster. You can do this the same way that you would with any other Kubernetes environment.

You use the Amazon EKS API for anything that Amazon EKS manages. As you have learned, this includes the entire control plane (creating and managing the cluster). In later modules, you learn how Amazon EKS can also manage parts of the data plane using features such as managed node groups and AWS Fargate.

![kubernetes-objects-eks-api.jpg](_img%2Fkubernetes-objects-eks-api.jpg)

![kubernetes-objects-eks-api-samples.jpg](_img%2Fkubernetes-objects-eks-api-samples.jpg)