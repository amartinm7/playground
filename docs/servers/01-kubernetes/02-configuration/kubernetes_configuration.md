# Kubernetes configuration

Kubernetes exposes an entry point to handle the cluster, handling by the `api-controller`

We send a `yaml` or `json` with the deployment descriptor to the `api-controller` to say to kubernetes how many pods we want to deploy

![kubernetes-deployment-00.jpg](_img%2Fkubernetes-deployment-00.jpg)

![kubernetes-deployment-01.jpg](_img%2Fkubernetes-deployment-01.jpg)

![kubernetes-deployment-03.jpg](_img%2Fkubernetes-deployment-03.jpg)

The `control-loop` looks for current status is the same as the required status to achieve it

![kubernetes-control-loop-00.jpg](_img%2Fkubernetes-control-loop-00.jpg)


## kubernetes configuration process: api-controller & etcd

The kubernetes current status is saved/handled with the `etcd`

So when a new `deployement` comes, the `control-loop` try to achieve the new requirements, but it has to see the current status of the deployment to compare both. To do that, the `control-loop` asks the `etcd` service for the current `deployment`.

![kubernetes-configuration-file-00.jpg](_img%2Fkubernetes-configuration-file-00.jpg)

![kubernetes-configuration-file-01.jpg](_img%2Fkubernetes-configuration-file-01.jpg)

![kubernetes-configuration-file-03.jpg](_img%2Fkubernetes-configuration-file-03.jpg)

![kubernetes-configuration-file-04.jpg](_img%2Fkubernetes-configuration-file-04.jpg)

![kubernetes-configuration-file-05.jpg](_img%2Fkubernetes-configuration-file-05.jpg)

![kubernetes-configuration-file-06.jpg](_img%2Fkubernetes-configuration-file-06.jpg)

## k8 Production Cluster vs Minikube (k8 local installation)

A typical `Production Cluster` setup: 
- Multiple Master and Worker nodes
- Separate virtual or physical machines

But in a local machine maybe is not possible, because we need a lot of resources.

![cluster-vs-minikube.jpg](_img%2Fcluster-vs-minikube.jpg)

Minikube is an open source that runs Master and Node processes on ONE machine. So we can test in local the `deployments` and so on.

## minikube installations

![minikube-installation-000.jpg](_img%2Fminikube-installation-000.jpg)

follow the instructions here:
- https://minikube.sigs.k8s.io/docs/start/

minikube is an image that runs over docker. 
Inside minikube, the pods can be executed in docker too.
So is docker in docker

![minikube-installations-00.jpg](_img%2Fminikube-installations-00.jpg)

![minikube-installations-01.jpg](_img%2Fminikube-installations-01.jpg)

![minikube-installations-03.jpg](_img%2Fminikube-installations-03.jpg)

![minikube-installations-04.jpg](_img%2Fminikube-installations-04.jpg)

![kubectl-cmd-00.jpg](_img%2Fkubectl-cmd-00.jpg)

![kubectl-cmd-01.jpg](_img%2Fkubectl-cmd-01.jpg)

![kubectl-cmd-02.jpg](_img%2Fkubectl-cmd-02.jpg)

![kubectl-cmd-03.jpg](_img%2Fkubectl-cmd-03.jpg)

![kubectl-cmd-04.jpg](_img%2Fkubectl-cmd-04.jpg)

## Kubectl

`Kubectl`is a command line tool for handling K8s clusters calling to the `api-controller` (api-server, enables interaction with cluster)

![kubectl-00.jpg](_img%2Fkubectl-00.jpg)

![kubectl-01.jpg](_img%2Fkubectl-01.jpg)