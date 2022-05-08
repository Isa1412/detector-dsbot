FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV JDA_TOKEN=token
ENV BOT_DB_USERNAME=ddsb_db_user
ENV BOT_DB_PASSWORD=ddsb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.jda.token=${JDA_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-jar", "app.jar"]