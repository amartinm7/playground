# kcat - kafkacat

## send/submit messages from a file in **bulk**

file.json contiene una lista de mensajes en formato JSON, uno por línea.

```bash
kcat -F ~/.config/kafkacat-pro.conf -t my-topic.migration -T -P -l file.json
```

## consume messages from a topic from **timestamp**

donde `-o beginning` indica que se deben consumir todos los mensajes desde el timestamp 1751288400000.
https://www.epochconverter.com/

```bash
kcat -F ~/.config/kafkacat-pro.conf -C -t my-topic.migration -o beginning -e

kcat -F ~/.config/kafkacat-pro.conf -C-t my-topic.migration -o s@1751288400000 -e | grep "whatever.com" | jq -r '.id'

kcat -F ~/.config/kafkacat-pro.conf -C-t my-topic.migration -o s@1751288400000 -e | grep \"id\" | jq -r '.id'
```
