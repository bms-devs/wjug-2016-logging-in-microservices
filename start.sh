#!/bin/bash

cd "$(dirname "$0")"

eval $(docker-machine env default)

docker-compose build
docker-compose up

