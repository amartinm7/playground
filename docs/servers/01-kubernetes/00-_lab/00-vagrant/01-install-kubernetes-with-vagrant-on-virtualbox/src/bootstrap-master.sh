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

#```bash
sudo kubeadm config images pull

sudo kubeadm init --pod-network-cidr=172.24.0.0/16 --cri-socket=unix:///run/containerd/containerd.sock --upload-certs --control-plane-endpoint=$MY_HOST_NAME

# Create kube config
echo "CREATE KUBECONFIG"
./create_kube_config.sh

export KUBECONFIG=/etc/kubernetes/admin.conf

kubectl cluster-info
#```

## Installing the network plugin on the master node
#```bash
curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/tigera-operator.yaml
kubectl create -f tigera-operator.yaml

curl -O https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/custom-resources.yaml
sed -ie 's/192.168.0.0/172.24.0.0/g' custom-resources.yaml
kubectl create -f custom-resources.yaml

kubectl get pods --all-namespaces
#```

## check everything is working well
#```bash
export KUBECONFIG=/etc/kubernetes/admin.conf

## Create a token and sha256 certificate
sudo kubeadm token create > /vagrant_data/token.txt
sudo openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //' > /vagrant_data/certificate.txt

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

# Install the kubernetes dashboard
curl -O https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml
kubectl apply -f recommended.yaml

cat <<EOF | sudo tee dashboard-adminuser.yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: admin-user
  namespace: kubernetes-dashboard
EOF

# apply the ServiceAccount template
kubectl apply -f dashboard-adminuser.yaml

# create ClusterRoleBinding if not exists from the k8s installation
cat <<EOF | sudo tee ClusterRoleBinding.yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: admin-user
  namespace: kubernetes-dashboard
EOF

# apply the ServiceAccount template
kubectl apply -f ClusterRoleBinding.yaml

# create the token to login into the dashboard
kubectl -n kubernetes-dashboard create token admin-user

# create the token and store it into a secret
cat <<EOF | sudo tee admin-user-secret.yml
apiVersion: v1
kind: Secret
metadata:
  name: admin-user
  namespace: kubernetes-dashboard
  annotations:
    kubernetes.io/service-account.name: "admin-user"   
type: kubernetes.io/service-account-token  
EOF

# apply the secret template
kubectl apply -f admin-user-secret.yml

# create the token and store it into the secret
kubectl get secret admin-user -n kubernetes-dashboard -o jsonpath={".data.token"} | base64 -d
