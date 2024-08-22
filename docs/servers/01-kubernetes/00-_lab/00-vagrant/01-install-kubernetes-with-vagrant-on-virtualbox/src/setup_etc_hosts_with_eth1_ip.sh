#!/bin/bash -e

#```bash
# show the ip
# ip --brief addr show
# setup the current ip and the localhost for the master-node
# sudo vim /etc/hosts
# check the file
# ping master
#```
MY_HOST_NAME=$1
# find current IP address
# MY_ETH_IP=$(ip a s eth1 | grep -E -o 'inet [0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' | cut -d' ' -f2)
# setup "master" and current IP
# sudo echo $MY_ETH_IP $MY_HOST_NAME >> /etc/hosts
sudo echo "10.0.2.15 $MY_HOST_NAME" | sudo tee -a /etc/hosts

echo "Setup /etc/hosts 10.0.2.15 $MY_HOST_NAME" | sudo tee setup_etc_hosts_with_eth1_ip.output.txt

