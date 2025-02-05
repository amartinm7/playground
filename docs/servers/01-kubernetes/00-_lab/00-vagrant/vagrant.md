# Vagrant basics

https://developer.hashicorp.com/vagrant/docs/index.html

## Install virtualbox

https://www.virtualbox.org/

## Install vagrant on linux

https://developer.hashicorp.com/vagrant/install#Linux

## Install vagrant macosx without virtualbox

```bash
brew install --cask vagrant
# install the plugin in order avoid installing virtualbox
vagrant plugin install vagrant-vbguest
```

## vagrant command
```bash
# creates a vagrant file
vagrant init
# Bring a box online.
vagrant up
# reload changes
vagrant reload
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
# Login into a guess machine
vagrant ssh
# exit on logout on a guess machine
vagrant logout
# list the installed box on local
vagrant box list
# remove a box
vagrant box remove hashicorp/bionic64
# install the shared universal link plugin
# Vagrant Share is a plugin that lets you share your Vagrant environment to anyone around the world with an Internet connection. It will give you a URL that will route directly to your Vagrant environment from any device in the world that is connected to the Internet.
# Open a web browser and visit the URL from your output. It should load the Apache page we setup earlier.
vagrant plugin install vagrant-share
vagrant share
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

other option is to use the a sh script and create a symbolic link:
```bash
ln -fs /vagrant /var/www
```
where /vagrant is the current host folder and /var/www is the guest folder

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

## Configure port forwarding
uncomment the this line `config.vm.network :forwarded_port, guest: 80, host: 8080` on the Vagrantfile, and reload the changes `vagrant reload`. You can access `http://127.0.0.1:8080`

### Test it
Create an `index.html` in the shared folder of the `host` machine 
```bash
cat <<EOT >> index.html
<!doctype html>
<html>
  <head>
    <title>This is the title of the webpage!</title>
  </head>
  <body>
    <p>This is an example paragraph. Anything in the <strong>body</strong> tag will appear on the page, just like this <strong>p</strong> tag and its contents.</p>
  </body>
</html>
EOT
```

open a `ssh` connection over the `guess` machine:
```bash
## run the virtual machine
vagrant up
## connect to the virtual machine via ssh
vagrant ssh
# install python 3
sudo apt-get update
sudo apt-get install python3.6
# execute the http server
sudo python3 -m http.server 80
```
over the `host` machine open a browser a visit the page `http://127.0.0.1:8080` to watch the index page served by the `guess` machine. (http not https)

## Several boxes on the same Vagrantfile
The idea behind is use the vagranfile as a Dockercompose file. I mean, you can define several instances of boxes and up and running all of them using only a Vagrantfile.
```bash
cat <<EOF | sudo tee /Vagrantfile
# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  config.vm.synced_folder "./data", "/vagrant_data"

  config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
  #   vb.gui = true
  #
  #   # Customize the amount of memory on the VM:
    vb.memory = "2048"
    vb.cpus = "2"
  end

  config.vm.define "master" do |master|
    master.vm.box = "hashicorp/bionic64"
    master.vm.network "private_network", ip: "192.168.56.1"
    master.vm.provision "shell", path: "bootstrap-master.sh"
  end

  config.vm.define "worker1" do |worker1|
    worker1.vm.box = "hashicorp/bionic64"
    worker1.vm.network "private_network", ip: "192.168.56.2"
    worker1.vm.provision "shell", path: "bootstrap-worker.sh"
  end

  config.vm.define "worker2" do |worker2|
    worker2.vm.box = "hashicorp/bionic64"
    worker2.vm.network "private_network", ip: "192.168.56.3"
    worker2.vm.provision "shell", path: "bootstrap-worker.sh"
  end
end
EOF
```
The way to access to every instance is using `vagrant ssh master`, `vagrant ssh worker1`, `vagrant ssh worker2`


