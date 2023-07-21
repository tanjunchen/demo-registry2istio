FROM docker.io/tanjunchen/mircoservice-os:centos7.1_java8_1.0.0
ENV APP_HOME /home/app
COPY provider-demo-1.0-SNAPSHOT.jar $APP_HOME/
COPY start.sh $APP_HOME/
WORKDIR ${APP_HOME}
CMD ["/bin/bash","start.sh"]