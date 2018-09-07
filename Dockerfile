FROM java:8-jre-alpine

ADD target/route-service-*.jar /route-service.jar

ENTRYPOINT ["java", "-Dserver.port=8080", "-Dspring.profiles.active=docker", "-jar", "./route-service.jar"] 
