#!/bin/bash

## update system
./update_system.sh

## disable swap memory (if this step fails, the installation will fail)
./disable_swap_memory.sh

## setup host name
MY_HOST_NAME="master-node"
./setup_host_name $MY_HOST_NAME

## setup /etc/hosts
./setup_etc_hosts_with_eth1_ip.sh $MY_HOST_NAME

## add the woker-node into the master-node /etc/hosts to get visibility between them
MY_HOST_NAME_REF="worker"
MY_HOST_IP_REF="192.168.56.2"
./setup_etc_hosts_with_fixed_ips.sh $MY_HOST_IP_REF $MY_HOST_NAME_REF

## Set up the IPV4 bridge on all nodes
./setup_IPV4_bridge_in_modules.sh

## Install kubelet, kubeadm, and kubectl on each node
./install_kubelet_kubeadm_kubectl.sh

## Install docker and containerd
./install_docker_containerd.sh

## Initialize the Kubernetes cluster on the master node (master node only)
./initialize_k8s_cluster_on_master_node.sh $MY_HOST_NAME

## Installing the network plugin on the master node
./install_network_plugin_on_master_node.sh

## Create a token and sha256 certificate
./create_token_and_sha256_certificate.sh

# Install the kubernetes dashboard
./install_dashboard.sh

## check the cluster info
#
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
