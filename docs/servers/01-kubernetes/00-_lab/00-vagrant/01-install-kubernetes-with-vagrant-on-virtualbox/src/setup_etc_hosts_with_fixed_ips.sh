#!/bin/bash

## setup fixed IP.
# Use cases:
# - to the master-node, setup the worker ip,
# - to the worker-node, setup the master-node ip,
MY_HOST_WORKER_NAME=$1
MY_HOST_WORKER_IP=$2
# sudo echo "$MY_HOST_WORKER_IP" "$MY_HOST_WORKER_NAME" >> /etc/hosts
sudo echo "$MY_HOST_WORKER_IP" "$MY_HOST_WORKER_NAME" | sudo tee -a /etc/hosts
