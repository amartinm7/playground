#!/bin/bash

curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/tigera-operator.yaml

kubectl create -f tigera-operator.yaml

curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/custom-resources.yaml

sed -ie 's/192.168.0.0/172.24.0.0/g' custom-resources.yaml

kubectl create -f custom-resources.yaml

kubectl get pods --all-namespaces
