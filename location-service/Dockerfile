# Base image
FROM openliberty/open-liberty:full-java11-openj9-ubi

# War file -> Liberty Dropins Dir
COPY target/location-service.war /config/dropins/

# Server Config -> Liberty Config File
COPY src/main/liberty/config/server.xml /config/

EXPOSE 9091

CMD ["/opt/ibm/wlp/bin/server", "run", "defaultServer"]