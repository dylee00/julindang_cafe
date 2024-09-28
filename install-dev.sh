gradlew bootJar
docker compose --file dev.docker-compose.yml build
docker compose --file dev.docker-compose.yml up -d
