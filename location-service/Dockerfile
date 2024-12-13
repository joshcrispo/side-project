# Base image
FROM icr.io/appcafe/open-liberty

# War file -> Liberty Dropins Dir
COPY target/location-service.war /config/dropins/location-service.war

# Server Config -> Liberty Config File
COPY src/main/liberty/config/server.xml /config/

EXPOSE 9091

CMD ["/opt/ibm/wlp/bin/server", "run", "defaultServer"]