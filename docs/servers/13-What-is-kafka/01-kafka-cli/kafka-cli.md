# Kafka-cli

Apache Kafka is a distributed event streaming platform that is widely used for building real-time data pipelines and streaming applications. In this guide, we’ll walk through the steps to install Kafka and Zookeeper on a Mac using Homebrew, and how to perform basic operations such as creating a topic, producing messages, and consuming messages using the command line interface.

## Prerequisites
Before getting started, ensure that you have Homebrew installed on your Mac.
Install HomeBrew : https://brew.sh/

## Installing Kafka and Zookeeper

First, let’s install Zookeeper, which is a centralized service for managing configuration information, naming, providing distributed synchronization, and providing group services.

```bash
zkserver start
brew install zookeeper
```

Next, we’ll install Apache Kafka.

```bash
brew install kafka
brew services start kafka
```
This will start the Kafka server as a background service.

## Creating a Topic

Topics are categories to which records are published. Let’s create a topic named “test” with a replication factor of 1 and a single partition.

```bash
kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
```

## Producing Messages

To send messages to the “test” topic, we can use the Kafka console producer.

```bash
kafka-console-producer --bootstrap-server localhost:9092 --topic test
```
This will start a console where you can type messages to send to the topic.

## Consuming Messages

To receive messages from the “test” topic, we can use the Kafka console consumer.

```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic test
```
This will start a console where you can see the messages being consumed in real-time.

## Topics List

To fetch all the topics configured on machine, we can use kafka-topics.

```bash
kafka-topics --list --bootstrap-server localhost:9092
```

## Send messages to topic

```
echo '{"adId": "123456789"}' | kcat -P -b localhost:9092 -t topic_name
```

## Consume messages from topic

```
kcat -C -b localhost:9092 -t topic_name -o beginning
```

## revisar config consumer_group

Look for the consumer_group name on application.yml and use it to consume messages from the topic.

```
kafka-consumer-groups --bootstrap-server localhost:9092 --group consumer_group_name --describe
```