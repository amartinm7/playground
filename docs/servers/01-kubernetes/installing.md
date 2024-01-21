# installing kubernetes

https://www.cherryservers.com/blog/install-kubernetes-on-ubuntu

https://pabpereza.notion.site/Install-kubernetes-1-28-on-Ubuntu-Server-20-04-LTS-6f8d28eadb6242f0a78c40eaa0211167

## update system

```bash
sudo apt update
sudo apt upgrade
```

## disable swap memory

```bash
sudo free -h
sudo swappoff -a 
sudo free -h
sudo vim /etc/fstab 
## comment the line starts with swap using a #

# or do this 
sudo sudo sed -i '/ swap / s/^/#/' /etc/fstab
sudo free -h

sudo reboot
```

## setup host name

```bash
# change host name
sudo hostnamectl set-hostname "master-node"
# reload the changes
sudo exec bash 
```

## setup /etc/hosts

```bash
# show the ip
ip --brief addr show
# setup the current ip and the localhost for the master-node
sudo vim /etc/hosts 
# check the file
ping master-node
```

## Set up the IPV4 bridge on all nodes

```bash
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
```

## Install kubelet, kubeadm, and kubectl on each node
```bash 
sudo apt-get update

sudo apt-get install -y apt-transport-https ca-certificates curl

sudo mkdir /etc/apt/keyrings

curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-archive-keyring.gpg

echo "deb [signed-by=/etc/apt/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list

sudo apt-get update

sudo apt install -y kubelet kubeadm kubectl
```

## Install docker
```bash 
sudo apt install docker.io
```

## setup containerd
```bash 
sudo mkdir /etc/containerd

sudo sh -c "containerd config default > /etc/containerd/config.toml"

sudo sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml
```
## restart services
```bash 
sudo systemctl restart containerd.service
sudo systemctl restart kubelet.service
sudo systemctl enable kubelet.service
```

## optional is kubelet is not working: /etc/docker/daemon.json

```bash
cat /etc/docker/daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"]
}
```

## optional if kubelet is not working: /var/lib/kubelet/config.yaml

```bash
sudo cat /var/lib/kubelet/config.yaml
apiVersion: kubelet.config.k8s.io/v1beta1
authentication:
  anonymous:
    enabled: false
  webhook:
    cacheTTL: 0s
    enabled: true
  x509:
    clientCAFile: /etc/kubernetes/pki/ca.crt
authorization:
  mode: Webhook
  webhook:
    cacheAuthorizedTTL: 0s
    cacheUnauthorizedTTL: 0s
cgroupDriver: systemd
clusterDNS:
- 172.16.0.10
clusterDomain: cluster.local
cpuManagerReconcilePeriod: 0s
evictionPressureTransitionPeriod: 0s
fileCheckFrequency: 0s
healthzBindAddress: 127.0.0.1
healthzPort: 10248
httpCheckFrequency: 0s
imageMinimumGCAge: 0s
kind: KubeletConfiguration
logging:
  flushFrequency: 0
  options:
    json:
      infoBufferSize: "0"
  verbosity: 0
memorySwap: {}
nodeStatusReportFrequency: 0s
nodeStatusUpdateFrequency: 0s
resolvConf: /run/systemd/resolve/resolv.conf
rotateCertificates: true
runtimeRequestTimeout: 0s
shutdownGracePeriod: 0s
shutdownGracePeriodCriticalPods: 0s
staticPodPath: /etc/kubernetes/manifests
streamingConnectionIdleTimeout: 0s
syncFrequency: 0s
volumeStatsAggPeriod: 0s
``




