FROM eclipse-temurin:17-jdk-focal as base
#ENV PORT=8080
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve
#RUN ./mvnw dependency:go-offline
COPY src ./src

FROM base as testing
RUN ["./mvnw", "test"]

FROM base as development
CMD ["./mvnw", "spring-boot:run"]

FROM base as staging
CMD ["./mvnw", "spring-boot:run"]

FROM base as production
#EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]