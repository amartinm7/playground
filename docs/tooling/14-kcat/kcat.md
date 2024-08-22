# Kcat

Kcat is client for kafka

https://github.com/edenhill/kcat

```bash
brew install kcat
``` 

## configuration
setup as many profiles as you want:
- config/kafkacat-pre.conf
- config/kafkacat-pro.conf

```bash
bootstrap.servers=kafka-server.com:9094
security.protocol=sasl_ssl
sasl.mechanisms=SCRAM-SHA-256
sasl.username=my-username
sasl.password=my-encryptedpass-tylMsBLdkKZyjrQbH9GBCzkvtQ9vn99h
```

## consuming events

In order to consume over a topic, the kafka admin has to give rights to the connected user on the topic

```bash
#!/bin/bash

# consuming messages on PROD
# kcat -F ~/.config/kafkacat-pro.conf -t pub.topic.xxx

kcat -b localhost:9092 -t pub.topic.xxx

# Read messages from stdin, produce to 'syslog' topic with snappy compression

tail -f /var/log/syslog | kcat -b mybroker -t syslog -z snappy

# Read the last 2000 messages from 'syslog' topic, then exit

kcat -C -b mybroker -t syslog -p 0 -o -2000 -e

# Decode Avro key (-s key=avro), value (-s value=avro) or both (-s avro) to JSON using schema from the Schema-Registry:

kcat -b mybroker -t ledger -s avro -r http://schema-registry-url:8080

# Output consumed messages according to format string:

kcat -b mybroker -t syslog -f 'Topic %t[%p], offset: %o, key: %k, payload: %S bytes: %s\n'

kcat -F ~/.config/kafkacat-pro.conf  -t "pub.topic.xxx" -Z -C -o s@1691020800000 -o e@1691107199000  -e -f '%s\n' | grep "492089954"

kcat -F ~/.config/kafkacat-pro.conf  -t "pub.topic.xxx" -Z -C -e -f '%s\n' | grep "492089954"

kcat -F ~/.config/kafkacat-pro.conf  -t "pub.topic.xxx" -C -f '%s\n' | grep "492089954"

kcat -F ~/.config/kafkacat-pro.conf  -t "pub.topic.xxx" -Z -C -o s@1690934400000 -o e@1691107200000 | jq -c 'select( .adMedia."adId" == "492089954" )'

kcat -F ~/.config/kafkacat-pro.conf  -t "pub.topic.xxx" -Z -C -o s@1692921600000 -o e@1693267200000 | grep "AdGenerated"


## https://www.epochconverter.com/ to filter by dates start end s@1690934400000 e@1691107200000

kcat -F ~/.config/kafkacat-pro.conf -t "pub.topic.xxx" -Z -C -e -f '%s\n' | grep "492089954"

kcat -F ~/.config/kafkacat-pro.conf -t "pub.topic.xxx" -Z -C -o s@1691020800000 -o e@1691107199000 -e -f '%s\n' >> mediaEvents. txt

kcat -F ~/.config/kafkacat-pro.conf -t "pub.topic.xxx" -Z -C -o s@1691020800000 -o e@1691107199000 | jq -c 'select(.adMedia."adId"="487481813"'

kcat -F ~/.config/kafkacat-pro.conf -t "pub.topic.xxx" -o -2000 -e -f 'Topic %t[%p], offset: %o, key: %k, payload: %S bytes: %s\n' | grep "http://schema.net/events/cross/-Message.json/1.7.json"

```

kcat -b mybroker -t syslog -f 'Topic %t[%p], offset: %o, key: %k, payload: %S bytes: %s\n'

## Producing events

In order to produce over a topic, the kafka admin has to give rights to the connected user on the topic

Create the ad_generared.json file, and execute the next command line:

```bash
# producing messages on local
kcat -b localhost:9092 -t pub.topic.xxx -P -T ad_generared.json
```