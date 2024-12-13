# Base image
FROM icr.io/appcafe/open-liberty:kernel-java21-openj9-ubi-minimal

# War file -> Liberty Dropins Dir
COPY target/weather-service.war /config/dropins/weather-service.war

# Server Config -> Liberty Config File
COPY src/main/liberty/config/server.xml /config/

EXPOSE 9092

CMD ["/opt/ibm/wlp/bin/server", "run", "defaultServer"]