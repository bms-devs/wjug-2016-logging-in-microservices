#!/bin/bash

cd "$(dirname "$0")"

eval $(docker-machine env default)

docker run -d \
    --name docker-statsd-influxdb-grafana \
    -p 3003:9000 \
    -p 3004:8083 \
    -p 8086:8086 \
    -p 22022:22 \
    -p 8125:8125/udp \
    samuelebistoletti/docker-statsd-influxdb-grafana

