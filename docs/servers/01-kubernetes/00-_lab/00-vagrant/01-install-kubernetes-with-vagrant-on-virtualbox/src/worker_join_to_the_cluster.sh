#!/bin/bash

MY_MASTER_NODE_NAME=$1 #"master"

TOKEN=$(cat /vagrant_data/token.txt)
CERTIFICATE_HASH=$(cat /vagrant_data/certificate.txt)
# Join to the cluster
sudo kubeadm join "$MY_MASTER_NODE_NAME":6443 --token "$TOKEN" --discovery-token-ca-cert-hash sha256:"$CERTIFICATE_HASH"