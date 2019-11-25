package com.optum.eem.demo.config;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JacksonConfig {

  private static final String dateFormat = "yyyy-MM-dd";
  private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    ObjectMapper objectMapper = builder.build();
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.setSerializationInclusion(Include.NON_NULL); // delete fields with null values
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    return objectMapper;
  }

  @Bean
  @ConditionalOnProperty(
      value = "spring.jackson.date-format",
      matchIfMissing = true,
      havingValue = "none")
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder -> {
      builder.modules(new JavaTimeModule());
      builder.simpleDateFormat(dateTimeFormat);
      builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
      builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
    };
  }

  @Bean
  public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(objectMapper());
    return converter;
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(
        LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
    javaTimeModule.addDeserializer(
        LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));

    objectMapper.registerModule(javaTimeModule);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.setSerializationInclusion(Include.NON_NULL); // delete fields with null values
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    return objectMapper;
  }
}
