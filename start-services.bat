@echo off
echo Iniciando microservicios...

start cmd /k "cd Estado && mvn spring-boot:run"
start cmd /k "cd GestionDeUsuario && mvn spring-boot:run"
start cmd /k "cd Contrataciones && mvn spring-boot:run"
start cmd /k "cd Roles && mvn spring-boot:run"

echo Todos los microservicios han sido iniciados en ventanas separadas.
pause