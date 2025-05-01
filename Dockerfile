
# Etapa 1: build con Maven y JDK 17
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: runtime con JRE 17
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el jar generado
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto que usará la aplicación HTTP
EXPOSE 8080

# Definimos la variable PORT por defecto
ENV PORT=8080

# Arrancamos pasando el server.port desde la env var PORT
ENTRYPOINT ["java","-jar","app.jar"]