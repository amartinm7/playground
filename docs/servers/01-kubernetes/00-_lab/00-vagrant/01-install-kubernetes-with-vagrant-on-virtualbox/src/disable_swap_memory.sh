#!/bin/bash

## disable swap memory (if this step fails, the installation will fail)
sudo swapoff -a
sudo sed -i 's|/swap.img|#/swap.img|g' /etc/fstab