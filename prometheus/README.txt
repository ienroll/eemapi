##
Find localhost IP address and add it to /etc/hosts file. Also, update the prometheus.yaml file with that IP address!

### Run Prometheus via Docker - Refer: https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/

```

  docker-compose -f prometheous2/docker-compose.yaml up -d

cd prometheus

docker run --name prometheus -p 9090:9090 -v $PWD/prometheous2/prometheus.yml:/prometheous2/prometheus.yml prom/prometheus
```