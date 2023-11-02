# proxmox

## create image to install into the laptop (usb unit is required)

Follow these steps:

- connect your `usb unit` to the laptop
- download iso image from website
- convert iso to dmg `hdiutil convert proxmox-ve_*.iso -format UDRW -o proxmox-ve_*.dmg`
- list the usb `diskutil list`
- unmount the usb to be formated and installed: `diskutil unmountDisk /dev/diskX`, where is the number of the unit getting from the step before.
- sudo dd if=proxmox-ve_*.dmg bs=1M of=/dev/rdiskX, setup the `x`, and the `r` means rapid.

## install into the laptop

- connect your `usb unit` with the image to the laptop
- Press F12
- disable bios security boostraping
- reboot
- Press F12
- install proxmox, choose the country, the language and the keyboard
- accept the hard drive
- setup the ethernet `en`, don't choose `wifi`
- go to the router, and look for the ip of the router. The router `ip` will be the `gateway ip` on the config. Choose a valid `ip` from the range inside the gateway

![settings-01.jpg](img%2Fsettings-01.jpg)

![settings-02.jpg](img%2Fsettings-02.jpg)

- accept all and reboot
- connect the laptop via ethernet to the router

# connect to console

- use the `https://CIDR_ip:8006` to connect remotely to the laptop
- accept to visit the place
- login with the root / passwod

![console.jpg](img%2Fconsole.jpg)
