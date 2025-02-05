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
  - Setup the scripts to be executed in order create the kubernetes cluster. The current script will have more references to another scripts.

### Accelerate the proccess

Everytime we run the vagrant file `vagrant up` the master and worker starts to download a lot of dependencies as updating the SO, downloading the docker and kubernetes files. This is doing that the process is so slow, because we have to spend a lot of time to do it every time. So in order to improve th process, the first thing that we have to do is to create a box with the downloaded updates. Once done, we package the current box. The box can be named 'my_k8s_so_updates'. To that, we do `vagrant up` and exec only the so dependecies script. We create the box `vagrant package worker --output k8s_cli_docker.box`. Add the box to the vagrant image list `vagrant box add --name k8s_cli_docker ./k8s_cli_docker.box`. After that we can use it into the vagrant file as `master.vm.box="k8s_cli_docker"` and `worker.vm.box="k8s_cli_docker"`. We iterate the process creating more boxes with the docker and kubernetes files. So in this way, when we run the vagrant, all it's downloaded and you don't have to spend a lot of time in the process.

Example: 

```bash
## common.sh script: download SO dependencies, install docker and k8s cli
cat <<EOF | sudo tee common.sh
#!/bin/bash -e

## update system
sh /vagrant/update_system.sh

## disable swap memory (if this step fails, the installation will fail)
sh /vagrant/disable_swap_memory.sh

## Set up the IPV4 bridge on all nodes
sh /vagrant/setup_overlay_IPV4_bridge_in_modules.sh

## Install kubelet, kubeadm, and kubectl on each node
sh /vagrant/install_kubelet_kubeadm_kubectl.sh

## Install docker and containerd
sh /vagrant/install_docker_containerd.sh
EOF
```

setup vagrantfile
```bash
  config.vm.define "worker" do |worker1|
    worker1.vm.box = "master_node"
    worker1.vm.network "public_network", ip: "192.168.56.2", bridge: "wlp0s20f3"
    worker1.vm.provision "shell", path: "common.sh"
  end
```
Execute `vagrant up`, after that `vagrant package worker --output k8s_cli_docker.box`. Now we can use the box image as `worker1.vm.box = "k8s_cli_docker"`. you can see all the box images installed in the host `vagrant box list`

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
# list box images
vagrant box list
# create box image
vagrant package worker --output k8s_cli_docker.box
# add the image
vagrant box add --name k8s_cli_docker ./k8s_cli_docker.box
# remove the image
vagrant box remove k8s_cli_docker
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
