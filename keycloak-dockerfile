FROM quay.io/keycloak/keycloak:latest

# Set environment variables
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Copy the realm configuration file
COPY realm.json /opt/keycloak/data/import/realm.json

# Start Keycloak with import option
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--import-realm"]
