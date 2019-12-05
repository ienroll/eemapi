docker-compose -f prometheus/docker-compose.yaml up -d
docker-compose -f node-exporter/docker-compose.yaml up -d
docker-compose -f alertmanager/docker-compose.yaml up -d
docker-compose -f grafana/docker-compose.yaml up -d
docker-compose -f jaeger/docker-compose.yaml up -d
docker-compose up -d
