#!/bin/bash

docker network create --driver bridge testnet

docker run -d -p 5432 \
        -e POSTGRES_PASSWORD=nimda \
        -e "SERVICE_TAGS=postgres,$BUNDLE_NAME" \
        -e SERVICE_NAME=testdb \
        --restart always \
        --hostname testdb \
        --name testdb postgres:9.5.19

#docker network connect --alias testdb testnet testdb

docker run -d -p 80 \
    -e "SERVICE_TAGS=postgres,traefik.enable=true,traefik.frontend.entryPoints=https,traefik.frontend.passHostHeader=true" \
    -e 'PGADMIN_DEFAULT_EMAIL=no@mail.com' \
    -e 'PGADMIN_DEFAULT_PASSWORD=nimda' \
    -e SERVICE_NAME=pgadmin \
    --name pgadmin dpage/pgadmin4

docker network connect --alias pgadmin testnet pgadmin

docker run -d -p 8080 \
--restart always \
-e "SERVICE_TAGS=atlassian,jira,traefik.enable=true,traefik.frontend.entryPoints=https,traefik.frontend.passHostHeader=true" \
-e SERVICE_NAME=jira1 \
-e X_PROXY_NAME='jira1.uprisesoft.ch' \
-e X_PROXY_PORT='443' \
-e X_PROXY_SCHEME='https' \
-e X_MINIMUM_MEMORY='1024m' \
-e X_MAXIMUM_MEMORY='2048m' \
--hostname jira1 \
--name jira1-ct zuara/jira:8.5.1

#docker run -d -p 8080 \
#--restart always \
#-e "SERVICE_TAGS=atlassian,jira,traefik.enable=true,traefik.frontend.entryPoints=https,traefik.frontend.passHostHeader=true" \
#-e SERVICE_NAME=jira2 \
#-e X_PROXY_NAME='jira2.uprisesoft.ch' \
#-e X_PROXY_PORT='443' \
#-e X_PROXY_SCHEME='https' \
#-e X_MINIMUM_MEMORY='1024m' \
#-e X_MAXIMUM_MEMORY='2048m' \
#--hostname jira2 \
#--name jira2-ct zuara/jira:8.5.1

docker run -d -p 8090 \
--restart always \
-e "SERVICE_TAGS=atlassian,confluence,traefik.enable=true,traefik.frontend.entryPoints=https,traefik.frontend.passHostHeader=true" \
-e SERVICE_NAME=confluence1 \
-e X_PROXY_NAME='confluence1.uprisesoft.ch' \
-e X_PROXY_PORT='443' \
-e X_PROXY_SCHEME='https' \
-e X_MINIMUM_MEMORY='1024m' \
-e X_MAXIMUM_MEMORY='2048m' \
--hostname confluence1 \
--name confluence1-ct zuara/confluence:7.1.0

# docker run -it --network testnet ubuntu /bin/bash
# apt-get update && apt-get -y upgrade && apt-get -y install iputils-ping
