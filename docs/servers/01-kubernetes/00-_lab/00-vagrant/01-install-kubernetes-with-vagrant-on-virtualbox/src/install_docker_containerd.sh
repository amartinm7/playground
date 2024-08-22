#!/bin/bash

## Install docker
sudo apt install -y docker.io

sudo mkdir /etc/containerd

sudo sh -c "containerd config default | sudo tee /etc/containerd/config.toml"

sudo sed -i 's/ SystemdCgroup = false/ SystemdCgroup = true/' /etc/containerd/config.toml

## restart services
sudo systemctl restart docker.service
sudo systemctl enable docker.service
sudo systemctl restart containerd.service
sudo systemctl enable containerd.service
sudo systemctl restart kubelet.service
sudo systemctl enable kubelet.service

# sudo systemctl status docker.service
# sudo systemctl status containerd.service
# sudo systemctl status kubelet.service
