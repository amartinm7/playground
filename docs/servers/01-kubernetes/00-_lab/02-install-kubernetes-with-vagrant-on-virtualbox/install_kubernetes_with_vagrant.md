# installing kubernetes with vagrant into virtual box

The main goal is create three vagrant scripts to create the master-node image, and the two worker-nodes images.

After that, you can run vagrant and the you will have the kubernetes cluster running with a simple click.

## Install virtualbox

## Install vagrant

https://developer.hashicorp.com/vagrant/install#Linux

## vagrant command
```bash
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


