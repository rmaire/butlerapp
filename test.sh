#!/bin/bash

export BUNDLE_NAME=testnet
export BUNDLE_DOMAIN=uprisesoft.ch
export DB_HOSTNAME=testdb
export DB_CONTAINER=testdb
export DB_PW=nimda

export JIRA_1_HOSTNAME=test01j
export JIRA_1_IMAGE=zuara/jira:8.5.1
export JIRA_1_CPU=1000
export JIRA_1_MEM_MIN=1024
export JIRA_1_MEM_MAX=2048
export JIRA_1_CONTAINER=test01j-ct

#export JIRA_2_HOSTNAME=testj02
#export JIRA_2_IMAGE=zuara/jira:8.5.1
#export JIRA_2_CPU=5000
#export JIRA_2_MEM_MIN=1024
#export JIRA_2_MEM_MAX=2048
#export JIRA_2_CONTAINER=jira2-ct

export CONFLUENCE_1_HOSTNAME=test01c
export CONFLUENCE_1_IMAGE=zuara/confluence:7.1.0
export CONFLUENCE_1_CPU=1000
export CONFLUENCE_1_MEM_MIN=1024
export CONFLUENCE_1_MEM_MAX=2048
export CONFLUENCE_1_CONTAINER=test01c-ct

java -jar target/butlerapp-0.1.0.jar --jobfile
#java -jar target/butlerapp-0.1.0.jar --setup
