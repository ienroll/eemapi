docker stop eemapi
docker stop alertmanager
docker stop grafana
docker stop node-exporter
docker stop jaeger
docker stop prometheus

docker rm eemapi
docker rm alertmanager
docker rm grafana
docker rm node-exporter
docker rm jaeger
docker rm prometheus
