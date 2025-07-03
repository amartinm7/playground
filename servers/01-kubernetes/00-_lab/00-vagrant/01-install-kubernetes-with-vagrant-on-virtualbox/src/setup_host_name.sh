#!/bin/bash -e

# change host name
echo "Setup hostname as machine name MY_HOST_NAME: $1" | sudo tee setup_host_name.output.txt
#
MY_HOST_NAME=$1
#
sudo hostnamectl set-hostname "$MY_HOST_NAME"
# reload the changes
exec bash
