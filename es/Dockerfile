FROM elasticsearch
MAINTAINER Hugues BRETIN

ENV MAX_LOCKED_MEMORY "unlimited"
ENV ES_HEAP_SIZE 1g

EXPOSE 9200
EXPOSE 9300

RUN /usr/share/elasticsearch/bin/plugin -DproxyPort=8080 -DproxyHost=proxy --install royrusso/elasticsearch-HQ
RUN /usr/share/elasticsearch/bin/plugin -DproxyPort=8080 -DproxyHost=proxy --install xyu/elasticsearch-whatson/0.1.3
