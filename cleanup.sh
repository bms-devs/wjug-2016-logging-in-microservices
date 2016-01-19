#!/bin/bash

cd "$(dirname "$0")"

eval $(docker-machine env default)

docker-compose stop
docker-compose kill
docker-compose rm

