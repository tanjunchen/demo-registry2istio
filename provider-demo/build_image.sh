#!/bin/bash

set -e

#IMAGE="docker.io/tanjunchen/csm-consul-mesh-provider:dev"
IMAGE="docker.io/tanjunchen/csm-consul-mesh-provider:dev-consul-mesh"

rm -rf provider-demo-1.0-SNAPSHOT.jar

THIS_DIR=$(cd "$(dirname "$0")"; pwd)

mvn clean package
cp target/provider-demo-1.0-SNAPSHOT.jar ${THIS_DIR}/

cd ${THIS_DIR}

docker build -f Dockerfile -t $IMAGE .
docker push $IMAGE
rm -rf provider-demo-1.0-SNAPSHOT.jar
