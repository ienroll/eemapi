package com.optum.eem.demo;

import com.optum.eem.demo.controller.EemController;
import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class EemApiApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(EemApiApplication.class, args);
        log.info("EemApiApplication started");

        EemController commandController = context.getBean(EemController.class);
        commandController.init();
    }

    @Bean
    public static JaegerTracer getTracer() {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
        Configuration config = new Configuration("Eem Api Application").withSampler(samplerConfig).withReporter(reporterConfig);
        return config.getTracer();
    }
}
