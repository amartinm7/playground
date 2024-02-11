#!/bin/bash -e

### update system
#sh /vagrant/update_system.sh
#
### disable swap memory (if this step fails, the installation will fail)
#sh /vagrant/disable_swap_memory.sh
#
### Set up the IPV4 bridge on all nodes
#sh /vagrant/setup_overlay_IPV4_bridge_in_modules.sh
#
### Install kubelet, kubeadm, and kubectl on each node
#sh /vagrant/install_kubelet_kubeadm_kubectl.sh
#
### Install docker and containerd
#sh /vagrant/install_docker_containerd.sh

# sh /vagrant/common.sh

## setup host name
MY_MASTER_NODE_NAME=$1 #"master-node"
MY_MASTER_NODE_IP=$2 #"master-node"
MY_WORKER_NODE_NAME=$3 #"worker-node"
MY_WORKER_NODE_IP=$4 #"worker-node"
#sh #vagrant/setup_host_name.sh $MY_MASTER_NODE_NAME
#
### setup /etc/hosts
#sh /vagrant/setup_etc_hosts_with_eth1_ip.sh $MY_MASTER_NODE_NAME
#
### add the woker-node into the master-node /etc/hosts to get visibility between them
#sh /vagrant/setup_etc_hosts_with_fixed_ips.sh $MY_MASTER_NODE_NAME $MY_MASTER_NODE_IP $MY_WORKER_NODE_NAME $MY_WORKER_NODE_IP
#
### Initialize the Kubernetes cluster on the master node (master node only)
# sh /vagrant/initialize_k8s_cluster_on_master_node.sh $MY_MASTER_NODE_NAME

if [ ! -d /etc/systemd/resolved.conf.d ]; then
	sudo mkdir /etc/systemd/resolved.conf.d/
fi
cat <<EOF | sudo tee /etc/systemd/resolved.conf.d/dns_servers.conf
[Resolve]
DNS=DNS=8.8.8.8 1.1.1.1
EOF

sudo systemctl restart systemd-resolved

## Installing the network plugin on the master node
sh /vagrant/install_network_plugin_on_master_node.sh

## Create a token and sha256 certificate
sh /vagrant/create_token_and_sha256_certificate.sh

# Install the kubernetes dashboard
# sh /vagrant/install_dashboard.sh

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
