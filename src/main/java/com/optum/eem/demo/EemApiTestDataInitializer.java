package com.optum.eem.demo;

import com.optum.eem.demo.config.DatabaseConfig;
import com.optum.eem.demo.model.Employee;
import com.optum.eem.demo.service.EmployeeService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EemApiTestDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

  private final Environment env;
  private final DatabaseConfig databaseConfig;

  private final EmployeeService employeeService;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.info("Loading test data for Eem Api Application: {}", env.getProperty("database.name"));
    log.info("Database properties: {}", databaseConfig.toString());

    // Add some employees
    employeeService.addEmployee(
        Employee.builder()
            .employeeId(1)
            .email("john@doe.com")
            .firstName("John")
            .lastName("Doe")
            .phone("555-1212")
            .joinDate(LocalDateTime.now())
            //            .joinDate(new Date())
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeId(2)
            .email("jenny@doe.com")
            .firstName("Jenny")
            .lastName("Doe")
            .phone("867-5309")
            .joinDate(LocalDateTime.now())
            //            .joinDate(new Date())
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeId(3)
            .email("clark@doe.com")
            .firstName("Clark")
            .lastName("Kent")
            .phone("555-1213")
            .joinDate(LocalDateTime.now())
            //            .joinDate(new Date())
            .build());
  }
}
