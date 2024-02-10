#!/bin/bash -e

MY_HOST_NAME=$1

echo "Init cluster with $MY_HOST_NAME" | sudo tee initialize_k8s_cluster_on_master_node.output.txt

# sudo kubeadm config images pull

sudo kubeadm init --pod-network-cidr=172.24.0.0/16 --cri-socket=unix:///run/containerd/containerd.sock --upload-certs --control-plane-endpoint="$MY_HOST_NAME"

# Create kube config
sudo mkdir -p ~/.kube
sudo cp -i /etc/kubernetes/admin.conf ~/.kube/config
sudo chown $(id -u):$(id -g) ~/.kube/config

export KUBECONFIG=/etc/kubernetes/admin.conf

kubectl cluster-info