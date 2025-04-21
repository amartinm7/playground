# Zigbee2MQTT

## Insert the zigbee dongle on the usb port

```bash
ls /dev/serial/by-id/
```

## create a docker-compose.yml

```bash
touch docker-compose.yml
```

```yaml
version: '3.8'
services:
  zigbee2mqtt:
    container_name: zigbee2mqtt
    image: koenkk/zigbee2mqtt
    restart: unless-stopped
    ports:
      - 8080:8080 # Web UI opcional
    volumes:
      - ./data:/app/data
    environment:
      - TZ=Europe/Madrid
    devices:
      - "/dev/serial/by-id/usb-Itead_Sonoff_Zigbee_3.0_USB_Dongle_Plus_V2_[ID_UNICO]-if00-port0:/dev/ttyUSB0"

```
change the part `/dev/serial/by-id/usb-Itead_Sonoff_Zigbee_3.0_USB_Dongle_Plus_V2_[ID_UNICO]-if00-port0` for the resulting part of executing this command `ls /dev/serial/by-id/` 

## setup grants

```bash
sudo chmod 666 /dev/serial/by-id/[DISPOSITIVO]
```
or 

```bash
sudo usermod -aG dialout $USER && reboot
```

