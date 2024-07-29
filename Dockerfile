FROM maven:3.9.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17
COPY --from=build /app/target/*.jar /myapp.jar
EXPOSE 8080
CMD ["java", "-jar", "/myapp.jar"]