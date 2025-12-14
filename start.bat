@echo off

echo Iniciando Backend de Spring Boot...
REM Navega a schoolevents-api y abre una nueva ventana de comandos para el backend
cd schoolevents-api
start cmd /k "mvn spring-boot:run"
REM O usa: start cmd /k "gradle bootRun" si usas Gradle
cd ..

echo Esperando 10 segundos para que el backend se inicialice...
timeout /t 10 /nobreak

echo Iniciando Frontend de Angular...
cd schoolevents-ui
ng serve --proxy-config proxy.conf.json