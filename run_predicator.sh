#!/bin/bash

# Navega para a pasta do projeto Maven antes de executar os comandos.
cd PrecoCasaPredictor/

# Compila o projeto
mvn clean install

# Executa o JAR gerado
java -jar target/PrecoCasaPredictor-1.0-SNAPSHOT.jar