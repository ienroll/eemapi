##Installation
  Install following Docker images. 
   
  ./prometheus
  ./grafana
  ./jaeger
  ./node-exporter
  ./alertmanager

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

##Application:

  http://localhost:8080/api/tutorial/1.0/employees

  ### Swagger-UI
  http://localhost:8080/swagger-ui.html

##Actuator/Metrics:
http://localhost:8080/actuator

##Prometheus:
http://localhost:9090/

##Grafana:
http://localhost:3000/

##Jaegar (https://www.scalyr.com/blog/jaeger-tracing-tutorial/):
http://localhost:16686/

#Alertmanager
http://localhost:9093