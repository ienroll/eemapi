package com.optum.eem.demo.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration // Use this OR add it to EnableConfigurationProperties - to add this bean to app cxt
@ConfigurationProperties(prefix = "database")
@Data
@NoArgsConstructor
public class DatabaseConfig {
  private String hostName;
  private String port;
  private String name;
}
