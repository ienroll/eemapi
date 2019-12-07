#!/usr/bin/env bash
export HOST_IP=`ifconfig en0 |awk '/inet / {print $2; }'`
echo "HOST_IP=$HOST_IP"
find . -name '*.yml' -print0 | xargs -0 sed -i "" "s/{{HOST_IP}}/${HOST_IP}/g"

docker-compose up -d
