##Installation

 ## Update prometheus.yaml asnd alerts.yaml with host IP Address:
  Run ifconfig | grep inet
  Copy the IP Address and update the files prometheus.yaml and alerts.yaml
   
  Install prometheus, node-exporter, jaeger, grafana, alertmanager images using the following command:
   ./install.sh

  To integrate Dockerized apps refer to - install.pdf(https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/)

##ServiceNow
  https://developer.servicenow.com/app.do#!/dashboard?v=newyork
  
  https://dev73294.service-now.com/nav_to.do?uri=%2Fhome.do%3F
  Username: admin
  Password: Eemapi123
  
   Ex: 
   `GET :`
      https://dev73294.service-now.com/api/now/v1/table/incident?sysparm_query=number=INC0000015
      https://dev73294.service-now.com/api/now/v1/table/incident?sysparm_query=assignment_group=1925d04d41e456c3c337ff6896e7fdae  
`   POST:`
      curl "https://dev73294.service-now.com/api/now/table/incident" \
      --request POST \
      --header "Accept:application/json" \
      --header "Content-Type:application/json" \
      --data "{}" \
      --user 'user':'password'


## Swagger-UI
http://localhost:9000/swagger-ui.html
  
## Application REST endpoints:
http://localhost:9000/api/tutorial/1.0/employees

##Actuator/Metrics:
http://localhost:9000/actuator

##Prometheus:
http://localhost:9090/

##Grafana:
http://localhost:3000/

##Jaegar (https://www.scalyr.com/blog/jaeger-tracing-tutorial/):
http://localhost:16686/

##Alertmanager
http://localhost:9093



