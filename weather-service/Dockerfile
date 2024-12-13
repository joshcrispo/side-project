# Base image
FROM openliberty/open-liberty:full-java21-openj9-ubi

# War file -> Liberty Dropins Dir
COPY target/weather-service.war /config/dropins/weather-service.war

# Server Config -> Liberty Config File
COPY src/main/liberty/config/server.xml /config/

EXPOSE 9092

CMD ["/opt/ibm/wlp/bin/server", "run", "defaultServer"]