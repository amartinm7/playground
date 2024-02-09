#!/bin/bash

# creates token and save it into the vagrant shared folder
sudo kubeadm token create > /vagrant_data/token.txt

# creates sha256 certificate and save it into the vagrant shared folder
sudo openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //' > /vagrant_data/certificate.txt