# installing kubernetes

https://www.cherryservers.com/blog/install-kubernetes-on-ubuntu

https://pabpereza.notion.site/Install-kubernetes-1-28-on-Ubuntu-Server-20-04-LTS-6f8d28eadb6242f0a78c40eaa0211167

## update system

```bash
sudo apt update
sudo apt upgrade
```

## disable swap memory (if this step fails, the installation will fail)

```bash
sudo free -h
sudo swappoff -a 
sudo free -h
sudo vim /etc/fstab 
## comment the line starts with swap using a #

# or do this 
sudo sudo sed -i '/ swap / s/^/#/' /etc/fstab
sudo free -h

sudo reboot
```

## setup host name

```bash
# change host name
sudo hostnamectl set-hostname "master-node"
# reload the changes
sudo exec bash 
```

## setup /etc/hosts

```bash
# show the ip
ip --brief addr show
# setup the current ip and the localhost for the master-node
sudo vim /etc/hosts 
# check the file
ping master-node
```

## Set up the IPV4 bridge on all nodes

```bash
## modify file /etc/modules-load.d/k8s.conf
cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
overlay
br_netfilter
EOF

## enable mods
sudo modprobe overlay
sudo modprobe br_netfilter

## sysctl params required by setup, params persist across reboots
cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables  = 1
net.bridge.bridge-nf-call-ip6tables = 1
net.ipv4.ip_forward                 = 1
EOF

# Apply sysctl params without reboot
sudo sysctl --system
```

## Install kubelet, kubeadm, and kubectl on each node
```bash 
sudo apt-get update

sudo apt-get install -y apt-transport-https ca-certificates curl

sudo mkdir /etc/apt/keyrings

curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-archive-keyring.gpg

echo "deb [signed-by=/etc/apt/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list

sudo apt-get update

sudo apt install -y kubelet kubeadm kubectl
```

## Install docker
```bash 
sudo apt install docker.io
```

## setup containerd
```bash 
sudo mkdir /etc/containerd

sudo sh -c "containerd config default > /etc/containerd/config.toml"

sudo sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml
```
## restart services
```bash 
sudo systemctl restart containerd.service
sudo systemctl restart kubelet.service
sudo systemctl enable kubelet.service

sudo systemctl status docker.service
sudo systemctl status containerd.service
sudo systemctl status kubelet.service
```

## Initialize the Kubernetes cluster on the master node
```bash
sudo kubeadm config images pull

sudo kubeadm init --pod-network-cidr=172.24.0.0/16 --cri-socket=unix:///run/containerd/containerd.sock --upload-certs --control-plane-endpoint=master-node

mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

export KUBECONFIG=/etc/kubernetes/admin.conf

kubectl cluster-info
```

## Installing the network plugin on the master node
```bash
curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/tigera-operator.yaml
kubectl create -f tigera-operator.yaml

curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/custom-resources.yaml
sed -ie 's/192.168.0.0/172.24.0.0/g' custom-resources.yaml
kubectl create -f custom-resources.yaml

kubectl get pods --all-namespaces
```

## check everything is working well
```bash
export KUBECONFIG=/etc/kubernetes/admin.conf

kubectl get nodes -o wide
kubectl get pods --all-namespaces
kubectl cluster-info

## check if the kubelet is not working. If it's not, check the if the swap is disabled.
sudo systemctl status kubelet.service
sudo systemctl status docker.service
sudo systemctl status containerd.service
```

## Add worker nodes to the cluster
You can now join any number of the control-plane node running the following command on each as root:

```bash
  kubeadm join master-node:6443 --token 70fma1.dty00ypu3ubitg2n \
	--discovery-token-ca-cert-hash sha256:459ff63c64df4bc95cc04a14e91f8b8788fab03cfc6bc7eda41754cfb8a1e316 \
	--control-plane --certificate-key cd98f8ec2a38193db9ca7736e66da9bde0884fa42e7652a0fdcda9687ab73a7e
```

Please note that the certificate-key gives access to cluster sensitive data, keep it secret!
As a safeguard, uploaded-certs will be deleted in two hours; If necessary, you can use
"kubeadm init phase upload-certs --upload-certs" to reload certs afterward.

Then you can join any number of worker nodes by running the following on each as root:

```bash
kubeadm join master-node:6443 --token 70fma1.dty00ypu3ubitg2n \
	--discovery-token-ca-cert-hash sha256:459ff63c64df4bc95cc04a14e91f8b8788fab03cfc6bc7eda41754cfb8a1e316 
```

## Create tokens
Token, launch this command on master server:
```bash
kubeadm token create
```
Token, launch this command on master server:
```bash
openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //'
```
If you want to see the active tokens: 
```bash
kubeadm token list
```
use the command to join the worker nodes
```bash
kubeadm join
sudo kubeadm join master-node:6443 --token $TOKEN --discovery-token-ca-cert-hash $CERTIFICATE_HASH
```

## optional is kubelet is not working: /etc/docker/daemon.json

```bash
cat /etc/docker/daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"]
}
```

## optional if kubelet is not working: /var/lib/kubelet/config.yaml
Take into account the IPs
```bash
sudo cat /var/lib/kubelet/config.yaml
apiVersion: kubelet.config.k8s.io/v1beta1
authentication:
  anonymous:
    enabled: false
  webhook:
    cacheTTL: 0s
    enabled: true
  x509:
    clientCAFile: /etc/kubernetes/pki/ca.crt
authorization:
  mode: Webhook
  webhook:
    cacheAuthorizedTTL: 0s
    cacheUnauthorizedTTL: 0s
cgroupDriver: systemd
clusterDNS:
- 172.16.0.10
clusterDomain: cluster.local
cpuManagerReconcilePeriod: 0s
evictionPressureTransitionPeriod: 0s
fileCheckFrequency: 0s
healthzBindAddress: 127.0.0.1
healthzPort: 10248
httpCheckFrequency: 0s
imageMinimumGCAge: 0s
kind: KubeletConfiguration
logging:
  flushFrequency: 0
  options:
    json:
      infoBufferSize: "0"
  verbosity: 0
memorySwap: {}
nodeStatusReportFrequency: 0s
nodeStatusUpdateFrequency: 0s
resolvConf: /run/systemd/resolve/resolv.conf
rotateCertificates: true
runtimeRequestTimeout: 0s
shutdownGracePeriod: 0s
shutdownGracePeriodCriticalPods: 0s
staticPodPath: /etc/kubernetes/manifests
streamingConnectionIdleTimeout: 0s
syncFrequency: 0s
volumeStatsAggPeriod: 0s
```

## Conflicting values on sources.list.d
```bash
E: Conflicting values set for option Signed-By regarding source http://apt.kubernetes.io/ kubernetes-xenial: /etc/apt/keyrings/kubernetes.gpg != /etc/apt/keyrings/kubernetes-archive-keyring.gpg
```
```bash
sudo rm /etc/apt/sources.list.d/kubernetes.list
```
## Validate instalation on a docker container

```bash
podman run -it ubuntu:22.10 sh -c '\
    apt-get update && \
    apt-get install -y apt-transport-https ca-certificates curl && \
    curl -fsSLo /etc/apt/keyrings/kubernetes-archive-keyring.gpg https://packages.cloud.google.com/apt/doc/apt-key.gpg && \
    echo "deb [signed-by=/etc/apt/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | tee /etc/apt/sources.list.d/kubernetes.list && \
    apt-get update && \
    apt-get install -y kubelet'
```
