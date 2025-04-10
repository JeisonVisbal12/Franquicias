# Imagen base de Tomcat con JDK 17
FROM tomcat:10.1-jdk17-temurin

# Elimina el contenido por defecto de webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia el WAR generado en la carpeta webapps
COPY /FranquiciaPruebaNequi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto por defecto de Tomcat
EXPOSE 8080