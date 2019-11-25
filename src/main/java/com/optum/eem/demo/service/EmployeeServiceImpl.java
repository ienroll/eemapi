package com.optum.eem.demo.service;

import com.optum.eem.demo.model.Employee;
import com.optum.eem.demo.repo.EmployeeRepository;
import io.opentracing.Span;
import io.opentracing.Tracer;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  private final Tracer tracer;

  // Create
  public boolean addEmployee(Employee employee) {
    return employeeRepository.addEmployee(employee);
  }

  // Read One
  public Optional<Employee> getEmployee(int id) {
    return employeeRepository.getEmployee(id);
  }

  // Read all
  public Collection<Employee> loadAllEmployees() {
    return employeeRepository.loadAllEmployees();
  }

  // Update
  public boolean updateEmployee(int id, Employee employee) {
    return employeeRepository.updateEmployee(id, employee);
  }

  // Update
  public boolean patchEmployee(int id, Employee employee) {
    return employeeRepository.patchEmployee(id, employee);
  }

  // Delete
  public boolean deleteEmployee(int id, Span rootSpan) {
    return employeeRepository.deleteEmployee(id, rootSpan);
  }
}
