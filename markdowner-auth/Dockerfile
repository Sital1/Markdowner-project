FROM openjdk:11.0.11-jdk
COPY build/libs/auth-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -Djdk.tls.client.protocols=TLSv1.2 -jar /app.jar