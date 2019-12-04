### Run Prometheus via Docker - Refer: https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/

```
cd prometheus

docker run -d --name prometheus -p 9090:9090 -v $PWD/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
```
