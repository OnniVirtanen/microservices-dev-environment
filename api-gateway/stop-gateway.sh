#!/usr/bin/bash

# Remove all resources that were made in start-gateway.sh
docker kill kong-gateway
docker kill kong-database
docker container rm kong-gateway
docker container rm kong-database
docker network rm app-network # app-network is also removed