#!/bin/sh

usage()
 {
 echo "Usage: $0 release"
 exit 1
 }

if [ $# -ne 1 ] ; then
    usage
else
    export release=$1
fi

echo "Build and Push eemapi:"${release}" to Docker repo"

docker build -t eemapi:${release} .
#docker tag eemapi:${release} ericgoebelbecker/eemapi:${release}
#docker push ericgoebelbecker/eemapi:${release}

