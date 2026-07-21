# Dockerfile - Version avec Tomcat officiel
FROM tomcat:9.0-jdk17-temurin

# Supprimer l'application par défaut
RUN rm -rf /usr/local/tomcat/webapps/*

# Copier le WAR
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]