package com.minsura.service;


import com.minsura.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, Employee employee);

    Employee updateEmployeeByFields(Long id, Map<String, Object> fields);

    void deleteEmployeeById(Long id);
}
