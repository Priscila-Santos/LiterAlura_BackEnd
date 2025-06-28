# Etapa 1: builder com Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: runtime com JDK leve
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copia o jar gerado no builder
COPY --from=builder /app/target/literalura-0.0.1-SNAPSHOT.jar app.jar

# Porta usada pela aplicação
EXPOSE 8282

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
