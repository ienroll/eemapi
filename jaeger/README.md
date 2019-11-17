### Run Jaeger open tracing via Docker - Refer: 
  https://www.scalyr.com/blog/jaeger-tracing-tutorial/  
  https://dzone.com/articles/opentracing-spring-boot-instrumentation

```
docker run -d --name jaeger -p 16686:16686 -p 6831:6831/udp jaegertracing/all-in-one
OR docker run --rm -it --network=host jaegertracing/all-in-one
```

### Jaeger vs Zipkin - https://sematext.com/blog/jaeger-vs-zipkin-opentracing-distributed-tracers/