#!/bin/bash

docker rm -f testdb
docker rm -f pgadmin
docker rm -f jira1-ct
docker rm -f jira2-ct
docker rm -f confluence1-ct
docker network rm testnet
# docker image rm postgres:9.5.19