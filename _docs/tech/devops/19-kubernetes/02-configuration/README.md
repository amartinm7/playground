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