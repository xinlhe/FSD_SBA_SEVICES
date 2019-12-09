FROM openjdk:8-jdk-alpine 
VOLUME /tmp
ADD SBA_MicroService_CompanyClient.jar SBA_MicroService_CompanyClient.jar
RUN sh -c 'touch /SBA_MicroService_CompanyClient.jar'
ENV JAVA_OPTS=""
#Djava.security.egd
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /SBA_MicroService_CompanyClient.jar" ]