#!/usr/bin/env bash
export HOST_IP=192.168.1.113
find . -name '*.yml' -print0 | xargs -0 sed -i "" "s/{{HOST_IP}}/${HOST_IP}/g"
docker-compose up -d
