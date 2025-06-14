@echo off
echo ==============================
echo INICIANDO MICROSERVICIOS...
echo ==============================

start cmd /k "cd categoria && mvn spring-boot:run"
start cmd /k "cd Contrataciones && mvn spring-boot:run"
start cmd /k "cd dirección && mvn spring-boot:run"
start cmd /k "cd Estado && mvn spring-boot:run"
start cmd /k "cd GestionDeUsuario && mvn spring-boot:run"
start cmd /k "cd Proyecto && mvn spring-boot:run"
start cmd /k "cd resena && mvn spring-boot:run"
start cmd /k "cd respuesta && mvn spring-boot:run"
start cmd /k "cd Roles && mvn spring-boot:run"
start cmd /k "cd servicio && mvn spring-boot:run"
start cmd /k "cd Soporte && mvn spring-boot:run"

echo ==============================
echo Todos los microservicios fueron iniciados en ventanas separadas.
echo ==============================
pause