FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV JDA_TOKEN=token
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.jda.token=${JDA_TOKEN}", "-jar", "app.jar"]