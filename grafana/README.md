### Run Grafana via Docker - Refer: https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/

```
docker run -d --name grafana -p 3000:3000 grafana/grafana
  docker-compose -f grafana/docker-compose.yaml up -d

```