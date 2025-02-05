#!/bin/bash -e

## disable swap memory (if this step fails, the installation will fail)
sudo swapoff -a
sudo sed -i 's|/swap.img|#/swap.img|g' /etc/fstab
sudo sed -i 's|/dev/mapper/vagrant--vg-swap_1|#//dev/mapper/vagrant--vg-swap_1|g' /etc/fstab

## check with free -h, the swap has to be zero