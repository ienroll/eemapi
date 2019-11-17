#! /bin/sh


export LIBS=/app/eemapi/libs
export APP=com.optum.eem.EemApiApplication
export LOG_DIR=${APP_HOME}/logs
export CLASSPATH=${CLASSPATH}:${LIBS}/*
${JAVA_HOME}/bin/java -enableassertions -cp ${CLASSPATH} ${LOGBACKCFG} ${APP}

