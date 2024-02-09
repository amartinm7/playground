#!/bin/bash
echo $1 $2 $3

exit

## update system
./update_system.sh

## disable swap memory (if this step fails, the installation will fail)
./disable_swap_memory.sh

## setup host name
MY_HOST_NAME=$1 //"worker"
./setup_host_name $MY_HOST_NAME

## setup /etc/hosts
./setup_etc_hosts_with_eth1_ip.sh $MY_HOST_NAME

## add the master-node into the worker-node /etc/hosts to get visibility between them
MY_HOST_NAME_REF=$2 //"master-node"
MY_HOST_IP_REF=$3 //"192.168.56.1"
./setup_etc_hosts_with_fixed_ips.sh $MY_HOST_IP_REF $MY_HOST_NAME_REF

## Set up the IPV4 bridge on all nodes
./setup_IPV4_bridge_in_modules.sh

## Install kubelet, kubeadm, and kubectl on each node
./install_kubelet_kubeadm_kubectl.sh

## Install docker and containerd
./install_docker_containerd.sh

# Reads the token and the certificate
./worker_join_to_the_cluster.sh

