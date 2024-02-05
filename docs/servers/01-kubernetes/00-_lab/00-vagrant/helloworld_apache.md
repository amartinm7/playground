# helloworld with apache

## Summary: 
- The main point here is to expose the 80 port in the guess to the 8080 on the host machine
- use the shared folder between machines with a symbolic link `ln -fs /vagrant /var/www`
- access from the host to the guess on the `localhost:8080` address. The guess serves the index fil of the host machine via shared folder, and the host retuns the result to the client on the port 8080.

## Steps
Create new project, and execute `vagrant init`
In the VagrantFile, setup the next properties:
```bash
Vagrant.configure("2") do |config|
  config.vm.box = "hashicorp/bionic64"
  config.vm.network "forwarded_port", guest: 80, host: 8080
  config.vm.provision "shell", path: "bootstrap.sh"
  # setup shared folder, optional
  # config.vm.synced_folder "./data", "/vagrant_data"
end
```
Create "bootstrap.sh" file in the current folder

```bash
cat <<EOF | sudo tee bootstrap.sh
#!/usr/bin/env bash

apt-get update
apt-get install -y apache2
if ! [ -L /var/www ]; then
  rm -rf /var/www
  ln -fs /vagrant /var/www
fi
EOF
```

Create a html folder, and inside, create the index.html file
```bash
cat <<EOF | sudo tee index.html
<!DOCTYPE html>
<html>
  <body>
    <h1>Get started with Vagrant!</h1>
  </body>
</html>
EOF
```
execute `vagrant up`, and `vagrant ssh` in another terminal in the same folder to login into the guess machine. 
into the guess host, check if the index.html is into the `/var/www` folder. To do that:
```bash
vagrant ssh
cd /var/www
ls -al
cat index.html
```
Once there, verify the index.html file is accessed by the guess machine to the host machine with the command ` wget -qO- 127.0.0.1` or `curl http://localhost` 
```text
vagrant@vagrant: wget -qO- 127.0.0.1
<!DOCTYPE html>
<html>
  <body>
    <h1>Get started with Vagrant!</h1>
  </body>
</html>
```
Access to the index.html on the host machine in the address `localhost:8080`

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
    master.vm.network "public_network", ip: "192.168.56.1", bridge: "wlp0s20f3"
    # master.vm.provision "shell", path: "bootstrap-master.sh"
  end

  config.vm.define "worker1" do |worker1|
    worker1.vm.box = "hashicorp/bionic64"
    worker1.vm.network "public_network", ip: "192.168.56.2", bridge: "wlp0s20f3"
    # worker1.vm.provision "shell", path: "bootstrap-worker.sh"
    worker1.vm.provision "shell", ", inline: <<-SHELL
      python3 -m http.server 9000
      cat <<EOF | sudo tee index.html
      <!DOCTYPE html>
      <html>
        <body>
          <h1>Get started with Vagrant!</h1>
        </body>
      </html>
      EOF
    SHELL
  end
end
EOF
```
The way to access to every instance is using `vagrant ssh master`, `vagrant ssh worker1`
connect to master and do `wget -qO- 127.0.0.1:9000`
```bash
vagrant@vagrant: wget -qO- 127.0.0.1:9000
<!DOCTYPE html>
<html>
  <body>
    <h1>Get started with Vagrant!</h1>
  </body>
</html>
```


