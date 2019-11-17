FROM anapsix/alpine-java:8_server-jre
MAINTAINER Supraja Doma "supraja.doma@optum.com"
ENV JAVA_VERSION 8u31
ENV PACKAGE_ROOT /app
ADD app /app/
ENTRYPOINT ["/app/eemapi/bin/start.sh"]
CMD []