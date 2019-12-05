# Run
FROM openjdk:11-slim as runtime

ENV APP_HOME /app
ENV JAVA_OPTS=""

RUN mkdir -p $APP_HOME
RUN mkdir $APP_HOME/log

VOLUME $APP_HOME/log
WORKDIR $APP_HOME

COPY target/eemapi-0.0.1-SNAPSHOT.jar $APP_HOME/eemapi.jar

EXPOSE 9000

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=default -jar /app/eemapi.jar" ]
