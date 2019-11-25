package com.optum.eem.demo.service;

import com.optum.eem.demo.model.Employee;
import io.opentracing.Span;
import java.util.Collection;
import java.util.Optional;

public interface EmployeeService {
  // Create
  boolean addEmployee(Employee employee);

  // Read One
  Optional<Employee> getEmployee(int id);

  // Read all
  Collection<Employee> loadAllEmployees();

  // Update
  boolean updateEmployee(int id, Employee employee);

  // Update
  boolean patchEmployee(int id, Employee employee);

  // Delete
  boolean deleteEmployee(int id, Span rootSpan);
}
