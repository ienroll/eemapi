package com.optum.eem.demo;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class EemApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(EemApiApplication.class, args);
    log.info("EemApiApplication started");
  }

  @Bean
  public static JaegerTracer getTracer() {
    Configuration.SamplerConfiguration samplerConfig =
        Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
    Configuration.ReporterConfiguration reporterConfig =
        Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
    Configuration config =
        new Configuration("Eem Api Application")
            .withSampler(samplerConfig)
            .withReporter(reporterConfig);
    return config.getTracer();
  }
}
