FROM openjdk:17-oracle

WORKDIR /app

COPY build/libs/module-2-homework-0.0.1-SNAPSHOT.jar app.jar

ARG APP_INITMODE_ENABLE=true
ENV APP_INITMODE_ENABLE="${APP_INITMODE_ENABLE:-true}"

CMD ["java", "-jar", "app.jar"]