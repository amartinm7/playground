#!/bin/bash

# installing kubernetes

# https://www.cherryservers.com/blog/install-kubernetes-on-ubuntu

# https://pabpereza.notion.site/Install-kubernetes-1-28-on-Ubuntu-Server-20-04-LTS-6f8d28eadb6242f0a78c40eaa0211167

## setup a fixed ip for every host
# To do that, you can go to your router/switch, and from the console, you can setup a fixed ip to a host inside your network.

##
# check the `sudo sed -i` commands works well after execute them.

## update system

# ```bash
sudo apt update
sudo apt upgrade
# ```

## disable swap memory (if this step fails, the installation will fail)
sudo swapoff -a
sudo sed -i 's|/swap.img|#/swap.img|g' /etc/fstab

## TODO setup host name

#```bash
# change host name
MY_HOST_NAME="master-node"
sudo hostnamectl set-hostname $MY_HOST_NAME
# reload the changes
sudo exec bash
#```

## setup /etc/hosts

#```bash
# show the ip
# ip --brief addr show
# setup the current ip and the localhost for the master-node
# sudo vim /etc/hosts
# check the file
# ping master-node
#```
# find current IP address
MY_ETH_IP=$(ip a s eth0 | grep -E -o 'inet [0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' | cut -d' ' -f2)
# setup "master-node" and current IP
sudo echo $MY_ETH_IP $MY_HOST_NAME >> /etc/hosts

## Set up the IPV4 bridge on all nodes

#```bash
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
# ```

## Install kubelet, kubeadm, and kubectl on each node
#```bash
sudo apt-get update

sudo apt-get install -y apt-transport-https ca-certificates curl

sudo mkdir /etc/apt/keyrings

curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-archive-keyring.gpg

echo "deb [signed-by=/etc/apt/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list

sudo apt-get update

sudo apt install -y kubelet kubeadm kubectl

sudo apt-mark hold kubelet kubeadm kubectl
#```

## Install docker
#```bash
sudo apt install -y docker.io
#```

## setup containerd
#```bash
sudo mkdir /etc/containerd

sudo sh -c "containerd config default > /etc/containerd/config.toml"

sudo sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml
#```
## restart services
#```bash
sudo systemctl restart docker.service
sudo systemctl enable docker.service
sudo systemctl restart containerd.service
sudo systemctl enable containerd.service
sudo systemctl restart kubelet.service
sudo systemctl enable kubelet.service

# sudo systemctl status docker.service
# sudo systemctl status containerd.service
# sudo systemctl status kubelet.service
#```

## Initialize the Kubernetes cluster on the master node (master node only)

#```bash
sudo kubeadm config images pull

sudo kubeadm init --pod-network-cidr=172.24.0.0/16 --cri-socket=unix:///run/containerd/containerd.sock --upload-certs --control-plane-endpoint=$MY_HOST_NAME

sudo mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

export KUBECONFIG=/etc/kubernetes/admin.conf

kubectl cluster-info
#```

## Installing the network plugin on the master node
#```bash
curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/tigera-operator.yaml
kubectl create -f tigera-operator.yaml

curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/custom-resources.yaml
sed -ie 's/192.168.0.0/172.24.0.0/g' custom-resources.yaml
kubectl create -f custom-resources.yaml

kubectl get pods --all-namespaces
#```

## check everything is working well
#```bash
export KUBECONFIG=/etc/kubernetes/admin.conf

# kubectl get nodes -o wide
# kubectl get pods --all-namespaces
# kubectl cluster-info
# kubectl config view
# kubectl config get-contexts

## check if the kubelet is not working. If it's not, check the if the swap is disabled.
# sudo systemctl status kubelet.service
# sudo systemctl status docker.service
# sudo systemctl status containerd.service
#```
