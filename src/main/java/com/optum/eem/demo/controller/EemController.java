package com.optum.eem.demo.controller;

import com.google.common.collect.ImmutableMap;
import com.optum.eem.demo.model.Employee;
import com.optum.eem.demo.service.EmployeeService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "eemapi")
@RequiredArgsConstructor
public class EemController {

  private final EmployeeService employeeService;
  private final Tracer tracer;

  @ApiOperation(value = "Create Employee ", response = ResponseEntity.class)
  @RequestMapping(value = "/api/tutorial/1.0/employees", method = RequestMethod.POST)
  public ResponseEntity createEmployee(@RequestBody Employee employee) {

    Span span = tracer.buildSpan("create employee").start();

    HttpStatus status = HttpStatus.FORBIDDEN;

    log.info("Receive Request to add employee {}", employee);
    if (employeeService.addEmployee(employee)) {
      status = HttpStatus.CREATED;
      span.setTag("http.status_code", 201);
    } else {
      span.setTag("http.status_code", 403);
    }
    span.finish();
    return new ResponseEntity(null, status);
  }

  @ApiOperation(value = "Get Employee ", response = ResponseEntity.class)
  @RequestMapping(value = "/api/tutorial/1.0/employees/{id}", method = RequestMethod.GET)
  public ResponseEntity getEmployee(@PathVariable("id") String idString) {

    Employee employee = null;
    HttpStatus status = HttpStatus.NOT_FOUND;

    Span span = tracer.buildSpan("get employee").start();

    try {
      int id = Integer.parseInt(idString);
      log.info("Received Request for employee {}", id);
      employee =
          employeeService
              .getEmployee(id)
              .orElseThrow(() -> new NoSuchElementException("Employee not found."));

      status = HttpStatus.OK;

    } catch (NumberFormatException | NoSuchElementException nfe) {
      log.error("Error getting employee: ", nfe);
    } finally {
      span.finish();
    }
    return new ResponseEntity<>(employee, status);
  }

  @ApiOperation(value = "Get All Employees ", response = ResponseEntity.class)
  @RequestMapping(value = "/api/tutorial/1.0/employees", method = RequestMethod.GET)
  public ResponseEntity getAllEmployees() {

    Span span = tracer.buildSpan("get employees").start();

    log.info("Receive Request to Get All Employees");
    Collection<Employee> employees = employeeService.loadAllEmployees();

    span.finish();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Employee ", response = ResponseEntity.class)
  @RequestMapping(value = "/api/tutorial/1.0/employees/{id}", method = RequestMethod.PUT)
  public ResponseEntity updateEmployee(
      @PathVariable("id") String idString, @RequestBody Employee employee) {

    Span span = tracer.buildSpan("update employee").start();

    HttpStatus status = HttpStatus.NO_CONTENT;

    try {
      int id = Integer.parseInt(idString);
      log.info("Received Request to update employee {}", id);

      if (employeeService.updateEmployee(id, employee)) {
        status = HttpStatus.OK;
      }
    } catch (NumberFormatException | NoSuchElementException nfe) {
      status = HttpStatus.BAD_REQUEST;
    } finally {
      span.finish();
    }
    return new ResponseEntity(null, status);
  }

  @ApiOperation(value = "Patch Employee ", response = ResponseEntity.class)
  @RequestMapping(value = "/api/tutorial/1.0/employees/{id}", method = RequestMethod.PATCH)
  public ResponseEntity patchEmployee(
      @PathVariable("id") String idString, @RequestBody Employee employee) {

    Span span = tracer.buildSpan("get employees").start();

    HttpStatus status = HttpStatus.NO_CONTENT;

    try {
      int id = Integer.parseInt(idString);
      log.info("Received Request to patch employee {}", id);

      if (employeeService.patchEmployee(id, employee)) {
        return new ResponseEntity(null, HttpStatus.OK);
      }
    } catch (NumberFormatException | NoSuchElementException nfe) {
      status = HttpStatus.BAD_REQUEST;
    } finally {
      span.finish();
    }

    return new ResponseEntity(null, status);
  }

  @ApiOperation(value = "Delete Employee ", response = ResponseEntity.class)
  @RequestMapping(value = "/api/tutorial/1.0/employees/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteEmployee(@PathVariable("id") String idString) {

    Span span = tracer.buildSpan("delete employee").start();

    HttpStatus status = HttpStatus.NO_CONTENT;

    try {
      int id = Integer.parseInt(idString);
      log.info("Received Request to delete employee {}", id);
      span.log(ImmutableMap.of("event", "delete-request", "value", idString));
      if (employeeService.deleteEmployee(id, span)) {
        span.log(ImmutableMap.of("event", "delete-success", "value", idString));
        span.setTag("http.status_code", HttpStatus.OK.value());
        status = HttpStatus.OK;
      } else {
        span.log(ImmutableMap.of("event", "delete-fail", "value", "does not exist"));
        span.setTag("http.status_code", HttpStatus.NO_CONTENT.value());
      }
    } catch (NumberFormatException | NoSuchElementException nfe) {
      span.log(ImmutableMap.of("event", "delete-fail", "value", idString));
      span.setTag("http.status_code", HttpStatus.NO_CONTENT.value());
    } finally {
      span.finish();
    }

    return new ResponseEntity(null, status);
  }
}
