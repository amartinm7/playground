#!/bin/bash

TOKEN=$(cat /vagrant_data/token.txt)
CERTIFICATE_HASH=$(cat /vagrant_data/certificate.txt)
# Join to the cluster
sudo kubeadm join master-node:6443 --token "$TOKEN" --discovery-token-ca-cert-hash sha256:"$CERTIFICATE_HASH"