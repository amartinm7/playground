#!/bin/bash

MY_MASTER_NODE_NAME=$1 #"master"
NODENAME=$(hostname -s) #"master"
CONTROL_IP="10.0.0.10"
POD_CIDR="172.16.1.0/16"
SERVICE_CIDR="172.17.1.0/18"

echo "Init cluster with $MY_MASTER_NODE_NAME" | sudo tee initialize_k8s_cluster_on_master_node.output.txt

sudo apt-get update -y

sudo kubeadm config images pull

# sudo kubeadm init --pod-network-cidr=172.24.0.0/16 --cri-socket=unix:///run/containerd/containerd.sock --upload-certs --control-plane-endpoint="$MY_MASTER_NODE_NAME"

sudo kubeadm init --apiserver-advertise-address=$CONTROL_IP --apiserver-cert-extra-sans=$CONTROL_IP --pod-network-cidr=$POD_CIDR --service-cidr=$SERVICE_CIDR --node-name "$NODENAME" --ignore-preflight-errors Swap

# Create kube config
sudo mkdir -p /home/vagrant/.kube
sudo cp -i /etc/kubernetes/admin.conf /home/vagrant/.kube/config
sudo chown "$(id -u)":"$(id -g)" /home/vagrant/.kube/config
sudo chmod 444 /home/vagrant/.kube/config
sudo chmod 444 /etc/kubernetes/admin.conf

export KUBECONFIG=/etc/kubernetes/admin.conf

# sudo kubectl cluster-info

# sudo kubectl get nodes -o wide
# sudo kubectl get pods --all-namespaces
# kubectl cluster-info
# kubectl config view
# kubectl config get-contexts
# kubectl -n <namespace> get deployment
# kubectl -n <namespace name> delete deployment <deployment name> 
# kubectl create -f deployment.yaml
# kubectl delete -f deployment.yaml
# kubectl delete -f custom-resources.yaml
# kubectl apply -f deployment.yaml
# kubectl get deployments
# kubectl -n namespace logs pod_name
# ## check if the kubelet is not working. If it's not, check the if the swap is disabled.
# sudo systemctl status kubelet.service
# sudo systemctl status docker.service
# sudo systemctl status containerd.service# 