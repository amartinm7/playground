# Elastic Stack (ELK) E-elasticsearch, L-logstash (log shipping), K-kibana

Kibana is an open source data visualization and user interface for Elasticsearch. We can think of Elasticsearch as a database and Kibana as the web user interface that we can use to create graphs, queries, indexes in Elasticsearch.

![ms-example-architecture.webp](_img%2Fms-example-architecture.webp)

![ELK-stack.webp](_img%2FELK-stack.webp)

Also you can see the image,

Logstash is collecting logs and transform
Elasticsearch is searching and analysing logs
Kibana is visualize ana manage logs

![ELK-sequence-diagrama.webp](_img%2FELK-sequence-diagrama.webp)


RUN on DOCKER
Verify that Elasticsearch is up and running

Elasticsearch is up and running
http://localhost:9200

Check the indexes
http://localhost:9200/_aliases


Verify that Kibana is up and running

Navigate to http://localhost:5601 to ensure Kibana is up and running