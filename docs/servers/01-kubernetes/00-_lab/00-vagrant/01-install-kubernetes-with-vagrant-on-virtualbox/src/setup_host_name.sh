#!/bin/bash

# change host name
echo "Reading param MY_HOST_NAME: $1"
#
MY_HOST_NAME=$1
#
sudo hostnamectl set-hostname "$MY_HOST_NAME"
# reload the changes
exec bash
