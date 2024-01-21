# installing kubernetes

https://www.cherryservers.com/blog/install-kubernetes-on-ubuntu

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


