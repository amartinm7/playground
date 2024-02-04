#!/bin/bash

# installing kubernetes

# https://www.cherryservers.com/blog/install-kubernetes-on-ubuntu

# https://pabpereza.notion.site/Install-kubernetes-1-28-on-Ubuntu-Server-20-04-LTS-6f8d28eadb6242f0a78c40eaa0211167

## setup a fixed ip for every host
# To do that, you can go to your router/switch, and from the console, you can setup a fixed ip to a host inside your network.

##
# check the `sudo sed -i` commands works well after execute them.

## update system

# ```bash
sudo apt update
sudo apt upgrade
# ```

## disable swap memory (if this step fails, the installation will fail)

#```bash
sudo free -h
sudo swappoff -a
sudo free -h
sudo vim /etc/fstab
## comment the line starts with swap using a #

# or do this
sudo sudo sed -i '/ swap / s/^/#/' /etc/fstab
sudo free -h

sudo reboot
# ```

## setup host name

#```bash
# change host name
sudo hostnamectl set-hostname "master-node"
# reload the changes
sudo exec bash
#```

## setup /etc/hosts

#```bash
# show the ip
ip --brief addr show
# setup the current ip and the localhost for the master-node
sudo vim /etc/hosts
# check the file
ping master-node
#```

## Set up the IPV4 bridge on all nodes

#```bash
## modify file /etc/modules-load.d/k8s.conf
cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
overlay
br_netfilter
EOF

## enable mods
sudo modprobe overlay
sudo modprobe br_netfilter

## sysctl params required by setup, params persist across reboots
cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables  = 1
net.bridge.bridge-nf-call-ip6tables = 1
net.ipv4.ip_forward                 = 1
EOF

# Apply sysctl params without reboot
sudo sysctl --system
# ```

## Install kubelet, kubeadm, and kubectl on each node
#```bash
sudo apt-get update

sudo apt-get install -y apt-transport-https ca-certificates curl

sudo mkdir /etc/apt/keyrings

curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-archive-keyring.gpg

echo "deb [signed-by=/etc/apt/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list

sudo apt-get update

sudo apt install -y kubelet kubeadm kubectl

sudo apt-mark hold kubelet kubeadm kubectl
#```

## Install docker
#```bash
sudo apt install docker.io
#```

## setup containerd
#```bash
sudo mkdir /etc/containerd

sudo sh -c "containerd config default > /etc/containerd/config.toml"

sudo sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml
#```
## restart services
#```bash
sudo systemctl restart containerd.service
sudo systemctl enable containerd.service
sudo systemctl restart kubelet.service
sudo systemctl enable kubelet.service

sudo systemctl status docker.service
sudo systemctl status containerd.service
sudo systemctl status kubelet.service
#```

## Review the connectivity into the machines

#That is, review the three machines can connect between them. To do that, install the opensshserver

```bash
# install the tools
sudo apt install openssh-client
sudo apt install openssh-server

# test the connectivity
ssh dell@192.168.0.34
ssh worker-one@192.168.0.32
ssh worker-two@192.168.0.31
#```

## Up to here is the same for the three machines, master and worker nodes

---------------

## Initialize the Kubernetes cluster on the master node (master node only)

#```bash
sudo kubeadm config images pull

sudo kubeadm init --pod-network-cidr=172.24.0.0/16 --cri-socket=unix:///run/containerd/containerd.sock --upload-certs --control-plane-endpoint=master-node

mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

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

kubectl get nodes -o wide
kubectl get pods --all-namespaces
kubectl cluster-info
kubectl config view
kubectl config get-contexts

## check if the kubelet is not working. If it's not, check the if the swap is disabled.
sudo systemctl status kubelet.service
sudo systemctl status docker.service
sudo systemctl status containerd.service
#```

## Add worker nodes to the cluster
#You can now join any number of the control-plane node running the following command on each as root:

#```bash
  kubeadm join master-node:6443 --token 70fma1.dty00ypu3ubitg2n \
	--discovery-token-ca-cert-hash sha256:459ff63c64df4bc95cc04a14e91f8b8788fab03cfc6bc7eda41754cfb8a1e316 \
	--control-plane --certificate-key cd98f8ec2a38193db9ca7736e66da9bde0884fa42e7652a0fdcda9687ab73a7e
#```

#Please note that the certificate-key gives access to cluster sensitive data, keep it secret!
#As a safeguard, uploaded-certs will be deleted in two hours; If necessary, you can use
#"kubeadm init phase upload-certs --upload-certs" to reload certs afterward.

#Then you can join any number of worker nodes by running the following on each as root:

#```bash
kubeadm join master-node:6443 --token 70fma1.dty00ypu3ubitg2n \
	--discovery-token-ca-cert-hash sha256:459ff63c64df4bc95cc04a14e91f8b8788fab03cfc6bc7eda41754cfb8a1e316
#```

## Join the worker nodes
#First of all create the token and the certificate token on the next steps over the master machine, and return to this point when it was completed

#use the command to join the worker nodes into the worker machine.
#```bash
kubeadm join
#
sudo kubeadm join master-node:6443 --token $TOKEN --discovery-token-ca-cert-hash sha256:$CERTIFICATE_HASH
#```
### Create token
#Token, launch this command on master server:
#```bash
kubeadm token create
#```
#If you want to see the active tokens:
#```bash
kubeadm token list
#```
### Create token certificate
#Token, launch this command on master server:
#```bash
openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //'
#```
## Install the kubernetes dashboard

#create an user account https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md
#install dashboard https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

#download the dashboard template and install it:
#```bash
curl -O https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml
kubectl apply -f recommended.yaml
#```

### create admin user and secret to store the token
#```bash
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
#```
#execute the next command to enable the proxy an listen on the 8001 port of the current host
#```bash
#kubectl proxy
#```

#check the installation on the next url
#```bash
#http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/
#```
#access to the dashboard and use the token if it's needed
#```bash
#http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/.
#```


## Testing endpoints
#doing an infinitive loop in single line (sleeping 1sec evrytime)
#```bash
#while sleep 1; do curl localhost:8080/v1 && curl localhost:8080/v2; done
#```

# /etc/fstab: static file system information.
#
# Use 'blkid' to print the universally unique identifier for a
# device; this may be used with UUID= as a more robust way to name devices
# that works even if disks are added and removed. See fstab(5).
#
# <file system> <mount point>   <type>  <options>       <dump>  <pass>
# / was on /dev/sda2 during curtin installation
/dev/disk/by-uuid/b2a1e804-89c6-4bce-a130-b7f451d62b29 / ext4 defaults 0 1
# /boot/efi was on /dev/sda1 during curtin installation
/dev/disk/by-uuid/63E3-8F5A /boot/efi vfat defaults 0 1
# /swap.img	none	swap	sw	0	0


