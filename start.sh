#!/bin/bash

echo "Limpiando y compilando el Backend de Spring Boot..."
cd schoolevents_api

# 1. Limpieza: Elimina la carpeta 'target' para asegurar una compilación limpia.
./mvnw clean

# 2. Compilación: Recompila el proyecto, lo que fuerza la regeneración de mappers.
#    Si la compilación falla aquí, el script se detendrá.
./mvnw compile

# Si la compilación fue exitosa, iniciamos la aplicación
echo "Iniciando la aplicación Spring Boot..."
./mvnw spring-boot:run &
BACKEND_PID=$!
cd ..

echo "Esperando 10 segundos para que el backend se inicialice..."
sleep 10 

echo "Iniciando Frontend de Angular..."
cd schoolevents-ui
ng serve --proxy-config proxy.conf.json

trap "kill $BACKEND_PID" EXIT