# helloworld with apache

- The main point here is to expose the 80 port in the guess to the 8080 on the host machine
- use the shared folder between machines with a symbolic link `ln -fs /vagrant /var/www`
- access from the host to the guess on the `localhost:8080` address. The guess serves the index fil of the host machine via shared folder, and the host retuns the result to the client on the port 8080.

Create new project, and execute `vagrant init`
In the VagrantFile, setup the next properties:
```bash
Vagrant.configure("2") do |config|
  config.vm.box = "hashicorp/bionic64"
  config.vm.network "forwarded_port", guest: 80, host: 8080
  config.vm.provision "shell", path: "bootstrap.sh"
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
execute `vagrant up`, and `vagrant ssh` in another terminal in the same folder to login into the guess machine. Once there, verify the index.html file is accessed by the guess machine to the host machine with the command ` wget -qO- 127.0.0.1`
```text
vagrant@vagrant: wget -qO- 127.0.0.1
<!DOCTYPE html>
<html>
  <body>
    <h1>Get started with Vagrant!</h1>
  </body>
</html>
```



