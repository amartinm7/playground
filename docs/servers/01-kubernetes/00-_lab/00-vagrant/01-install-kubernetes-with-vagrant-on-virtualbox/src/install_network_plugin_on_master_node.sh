#!/bin/bash

echo "Installing tigera-operator" | sudo tee install_network_plugin_on_master_node.output.txt

export KUBECONFIG=/etc/kubernetes/admin.conf

curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/tigera-operator.yaml

kubectl create -f tigera-operator.yaml

echo "Installing calico" | sudo tee -a install_network_plugin_on_master_node.output.txt

curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/custom-resources.yaml

sudo sed -ie 's/192.168.0.0/172.24.0.0/g' custom-resources.yaml

kubectl create -f custom-resources.yaml

kubectl get pods --all-namespaces
