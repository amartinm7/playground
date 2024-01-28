# Vagrant basics

https://developer.hashicorp.com/vagrant/docs/index.html

## Install virtualbox

https://www.virtualbox.org/

## Install vagrant

https://developer.hashicorp.com/vagrant/install#Linux

## vagrant command
```bash
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



