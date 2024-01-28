# installing kubernetes with vagrant into virtual box

The main goal is create three vagrant scripts to create the master-node image, and the two worker-nodes images.

After that, you can run vagrant and the you will have the kubernetes cluster running with a simple click.

## Install virtualbox

## Install vagrant

https://developer.hashicorp.com/vagrant/install#Linux

## Create vagrantfile

https://app.vagrantup.com/bento/boxes/ubuntu-22.04

```bash 
vagrant init bento/ubuntu-22.04
``` 
This commnand creates a vagrantfile with the provider setup as ubuntu and more things to config.


