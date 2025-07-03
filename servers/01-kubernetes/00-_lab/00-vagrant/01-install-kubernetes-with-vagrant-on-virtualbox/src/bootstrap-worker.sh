#!/bin/bash -e

## update system
#h /vagrant/update_system.sh

## disable swap memory (if this step fails, the installation will fail)
#h /vagrant/disable_swap_memory.sh

## Set up the IPV4 bridge on all nodes
#h /vagrant/setup_overlay_IPV4_bridge_in_modules.sh

## Install kubelet, kubeadm, and kubectl on each node
#h /vagrant/install_kubelet_kubeadm_kubectl.sh

## Install docker and containerd
#h /vagrant/install_docker_containerd.sh

## setup host name
MY_MASTER_NODE_NAME=$1 #"master"
MY_MASTER_NODE_IP=$2 #"master"
MY_WORKER_NODE_NAME=$3 #"worker-node"
MY_WORKER_NODE_IP=$4 #"worker-node"
sh /vagrant/setup_host_name.sh "$MY_WORKER_NODE_NAME"

## setup /etc/hosts
sh /vagrant/setup_etc_hosts_with_eth1_ip.sh "$MY_WORKER_NODE_NAME"

## add the master-node into the worker-node /etc/hosts to get visibility between them
sh /vagrant/setup_etc_hosts_with_fixed_ips.sh "$MY_MASTER_NODE_NAME" "$MY_MASTER_NODE_IP" "$MY_WORKER_NODE_NAME" "$MY_WORKER_NODE_IP"

if [ ! -d /etc/systemd/resolved.conf.d ]; then
	sudo mkdir /etc/systemd/resolved.conf.d/
fi
cat <<EOF | sudo tee /etc/systemd/resolved.conf.d/dns_servers.conf
[Resolve]
DNS=DNS=8.8.8.8 1.1.1.1
EOF

sudo systemctl restart systemd-resolved
# Reads the token and the certificate
sh /vagrant/worker_join_to_the_cluster.sh "$MY_MASTER_NODE_NAME"

