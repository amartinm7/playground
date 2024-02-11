#!/bin/bash

sudo rm -f /vagrant_data/token.txt /vagrant_data/certificate.txt

# creates token and save it into the vagrant shared folder
sudo kubeadm token create | sudo tee /vagrant_data/token.txt

# creates sha256 certificate and save it into the vagrant shared folder
sudo openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //' | sudo tee /vagrant_data/certificate.txt
