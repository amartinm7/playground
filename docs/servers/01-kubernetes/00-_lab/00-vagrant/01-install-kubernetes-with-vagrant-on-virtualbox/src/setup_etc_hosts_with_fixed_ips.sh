#!/bin/bash -e

## setup fixed IP.
MY_MASTER_NODE_NAME=$1 #"master"
MY_MASTER_NODE_IP=$2 #"master"
MY_WORKER_NODE_NAME=$3 #"worker-node"
MY_WORKER_NODE_IP=$4 #"worker-node"
# 
sudo echo "$MY_MASTER_NODE_IP" "$MY_MASTER_NODE_NAME" | sudo tee -a /etc/hosts
sudo echo "$MY_WORKER_NODE_IP" "$MY_WORKER_NODE_NAME" | sudo tee -a /etc/hosts
