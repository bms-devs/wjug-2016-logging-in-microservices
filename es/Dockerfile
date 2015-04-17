FROM elasticsearch
MAINTAINER Hugues BRETIN

ENV MAX_LOCKED_MEMORY "unlimited"
ENV ES_HEAP_SIZE 1g

EXPOSE 9200
EXPOSE 9300

RUN /usr/share/elasticsearch/bin/plugin --install royrusso/elasticsearch-HQ
RUN /usr/share/elasticsearch/bin/plugin --install xyu/elasticsearch-whatson/0.1.3
