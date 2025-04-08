@echo off

REM Demande à l'utilisateur de saisir le nom du fichier WAR
set /p APP_NAME="Entrez le nom du fichier WAR (ex: HelloWorldApp) : "
@REM set APP_NAME=Departementfffff
set SRC_DIR=src
set WEB_DIR=src\webapp
set BUILD_DIR=build
set LIB_DIR=lib
set WEB1_DIR=web
set TOMCAT_WEBAPPS="C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
set SERVLET_API_JAR=%LIB_DIR%\servlet-api.jar
set MYSQL_JAR=%LIB_DIR%\mysql-connector-j-9.1.0.jar

REM Suppression et recréation du dossier temporaire
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%
mkdir %BUILD_DIR%\WEB-INF\classes

REM Compilation des fichiers Java avec le JAR des Servlets
dir /s /b %SRC_DIR%\*.java > sources.txt
javac -cp %SERVLET_API_JAR% -d %BUILD_DIR%\WEB-INF\classes @sources.txt
del sources.txt

REM Copier les fichiers web
xcopy %WEB_DIR% %BUILD_DIR% /E /I /Y
xcopy %WEB1_DIR% %BUILD_DIR% /E /I /Y

REM Ajouter le JAR MySQL dans WEB-INF/lib pour Tomcat
mkdir %BUILD_DIR%\WEB-INF\lib
xcopy %MYSQL_JAR% %BUILD_DIR%\WEB-INF\lib /Y

REM Création du fichier .war dans le dossier build
cd %BUILD_DIR%
jar -cvf %APP_NAME%.war *
cd ..

REM Déploiement vers Tomcat
copy %BUILD_DIR%\%APP_NAME%.war %TOMCAT_WEBAPPS%

echo Déploiement terminé. Redémarrez Tomcat si nécessaire.