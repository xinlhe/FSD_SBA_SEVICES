FROM openjdk:8-jdk-alpine 
VOLUME /tmp
ADD FSD-FinalAssignment-MicroService-EurekaServer.jar FSD-FinalAssignment-MicroService-EurekaServer.jar
RUN sh -c 'touch /FSD-FinalAssignment-MicroService-EurekaServer.jar'
ENV JAVA_OPTS=""
#Djava.security.egd
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /FSD-FinalAssignment-MicroService-EurekaServer.jar" ]