# Étape 1 : builder le projet avec Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : exécuter le jar avec Java
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render fournit la variable PORT → on l’utilise
ENV PORT=9090
EXPOSE 9090

CMD ["java", "-jar", "app.jar"]
