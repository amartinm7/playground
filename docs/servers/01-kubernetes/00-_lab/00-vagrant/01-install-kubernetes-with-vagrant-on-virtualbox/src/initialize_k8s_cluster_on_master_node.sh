#!/bin/bash -e

MY_HOST_NAME=$1

echo "Init cluster with $MY_HOST_NAME" | sudo tee initialize_k8s_cluster_on_master_node.output.txt

sudo apt-get update -y

sudo kubeadm config images pull

sudo kubeadm init --pod-network-cidr=172.24.0.0/16 --cri-socket=unix:///run/containerd/containerd.sock --upload-certs --control-plane-endpoint="$MY_HOST_NAME"

# Create kube config
mkdir -p "$HOME"/.kube
sudo cp -i /etc/kubernetes/admin.conf "$HOME"/.kube/config
sudo chown "$(id -u)":"$(id -g)" "$HOME"/.kube/config

export KUBECONFIG=/etc/kubernetes/admin.conf

# kubectl cluster-info

# kubectl get nodes -o wide
# kubectl get pods --all-namespaces
# kubectl cluster-info
# kubectl config view
# kubectl config get-contexts
# 
# ## check if the kubelet is not working. If it's not, check the if the swap is disabled.
# sudo systemctl status kubelet.service
# sudo systemctl status docker.service
# sudo systemctl status containerd.service# 