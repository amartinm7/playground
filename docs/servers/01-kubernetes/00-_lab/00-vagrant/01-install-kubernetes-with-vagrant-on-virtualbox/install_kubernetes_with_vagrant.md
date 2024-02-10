# installing kubernetes with vagrant into virtual box

The main goal is create three vagrant scripts to create the master-node image, and the two worker-nodes images.

After that, you can run vagrant and the you will have the kubernetes cluster running with a simple click.

## Process

- generate a Vagrant file to configure the pipeline. Todo that, write down on the current folder `vagrant init`. This generates a Vagrantfile. 
- Over the Vagrantfile, uncomment the parts that you need.
- Define the hypervisor or provider. Virtualbox is fine, but you have to install it into your host.
- Define the shared folders between the host machine (your host) and the guess machine (the created box)
- Define the guests. To create a kubernets cluster, we want a master and worker node `config.vm.define "master"` and `config.vm.define "worker"`
  - Define a public network and the current ip for every guest. The brige interface is the interface is currently used to access to internet. In my case is wifi ethernet. You can check the interfaces with the command ` ip --brief addr show` for instance.
  - Open the forwarded_port into the master box to allow communicate the master host with the host
  - Setup the scripts to be executed in order create the kubernetes cluster

## Install virtualbox

## Install vagrant

https://developer.hashicorp.com/vagrant/install#Linux

## vagrant command
```bash
# creates a vagrant file
vagrant init
# Bring a box online.
vagrant up
# Show current box status.
vagrant status
# Pause the current box.
vagrant suspend
# Resume the current box.
vagrant resume
# Shutdown the current box. 
vagrant halt
# Destroy the current box.
vagrant destroy
# Take a snapshot of the current box.
vagrant snapshot
``` 
## Create vagrantfile

https://app.vagrantup.com/bento/boxes/ubuntu-22.04

```bash 
vagrant init bento/ubuntu-22.04
``` 
This commnand creates a vagrantfile with the provider setup as ubuntu and more things to config.

## Execute vagrantfile

Over the current folder execute:

```bash
## run the virtual machine
vagrant up
## connect to the virtual machine via ssh
vagrant ssh
```
A ssh username and private key are created so if you run vagrant ssh, you can access to the virtual machine

output
```
==> default: Clearing any previously set network interfaces...
==> default: Preparing network interfaces based on configuration...
    default: Adapter 1: nat
==> default: Forwarding ports...
    default: 22 (guest) => 2222 (host) (adapter 1)
==> default: Booting VM...
==> default: Waiting for machine to boot. This may take a few minutes...
    default: SSH address: 127.0.0.1:2222
    default: SSH username: vagrant
    default: SSH auth method: private key
    default: 
    default: Vagrant insecure key detected. Vagrant will automatically replace
    default: this with a newly generated keypair for better security.
    default: 
    default: Inserting generated public key within guest...
    default: Removing insecure key from the guest if it's present...
    default: Key inserted! Disconnecting and reconnecting using new SSH key...
==> default: Machine booted and ready!
==> default: Checking for guest additions in VM...
==> default: Mounting shared folders...
```
## Shared folder
The current folder is a shared folder between the host and guest operative system. It's bidirectional. Create a `data` folder a change the line to setup the `./data` folder on the Vagrantfile:

```bash
  config.vm.synced_folder "./data", "/vagrant_data"
```
Reload the changes and access to the shrared folder:
```bash
vagrant halt
vagrant up
vagrant ssh
cd /vagrant_data && ls -al
```

## Inspect the vagrant box
```bash
## run the virtual machine
vagrant up
## connect to the virtual machine via ssh
vagrant ssh
## watch the used memory
free -h
## watch the host name
hostnamectl
## watch the interfaces: eth0 (cable) and lo (wifi)
ip --brief addr show
## watch /etc/hosts
cat /etc/hosts
```
