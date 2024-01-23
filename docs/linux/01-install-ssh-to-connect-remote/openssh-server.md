# Ssh to connect remotely

install openssh-server on the machine which is going to be visited
```bash
sudo apt install net-tools
ifconfig
#
sudo apt install openssh-server
# ask for the host ip
ip --brief addr show 
```

in the client machine the ssh-client has to be installed. Otherwise install it
```bash
sudo apt install openssh-client
```

Once we have the client installed we can connect to the other machine as follows
```bash 
ssh dell@192.168.0.34
ssh master@192.168.0.32
ssh worker@192.168.0.31 
```
the first time you connect you have to say `yes` to add the ssh key in your ssh keystore.
In this way you can connect remotely.

## copying files from one host to another

copy the folder of the local-user user to the remote user in the ip and folder
```bash
scp -r /home/local-user 192.168.0.31:/tmp
```
the first time you connect you have to say `yes` to add the ssh key in your ssh keystore.
In this way you can connect remotely.
