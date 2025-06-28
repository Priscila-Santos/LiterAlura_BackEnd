# Dockerfile para aplicação Spring Boot (Java 21)
FROM eclipse-temurin:21-jdk-alpine

# Diretório de trabalho
WORKDIR /app

# Copia o build final para dentro do container
COPY target/literalura-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta definida no application.properties
EXPOSE 8282

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
