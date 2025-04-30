# Etapa 1: build del proyecto
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagen final con Java
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Puerto que usará Render
ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]