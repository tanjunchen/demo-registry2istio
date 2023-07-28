#!/bin/bash

set -e

#IMAGE="docker.io/tanjunchen/csm-consul-mesh-consumer:dev"
IMAGE="docker.io/tanjunchen/csm-consul-mesh-consumer:dev-consul-mesh"

# 清理当前的应用程序
rm -rf consumer-demo-1.0-SNAPSHOT.jar

THIS_DIR=$(cd "$(dirname "$0")"; pwd)
# 打包javaagent
mvn clean package
cp target/consumer-demo-1.0-SNAPSHOT.jar ${THIS_DIR}/
cd ${THIS_DIR}

## 开始构建镜像
docker build -f Dockerfile -t $IMAGE .
docker push $IMAGE

rm -rf consumer-demo-1.0-SNAPSHOT.jar