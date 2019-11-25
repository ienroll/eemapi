package com.optum.eem.demo;

import com.optum.eem.demo.model.Employee;
import com.optum.eem.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EemApiTestDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

  private final EmployeeService employeeService;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.info("Loading test data for Eem Api Application...");

    // Add some employees
    employeeService.addEmployee(
        Employee.builder()
            .employeeId(1)
            .email("john@doe.com")
            .firstName("John")
            .lastName("Doe")
            .phone("555-1212")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeId(2)
            .email("jenny@doe.com")
            .firstName("Jenny")
            .lastName("Doe")
            .phone("867-5309")
            .build());

    employeeService.addEmployee(
        Employee.builder()
            .employeeId(3)
            .email("clark@doe.com")
            .firstName("Clark")
            .lastName("Kent")
            .phone("555-1213")
            .build());
  }

}
