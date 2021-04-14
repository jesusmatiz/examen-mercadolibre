FROM openjdk:11.0-jdk-slim-stretch

ENTRYPOINT   [ "java","-jar","/home/ExamenMercadoLibre-1.0.0-SNAPSHOT.jar" ] 
