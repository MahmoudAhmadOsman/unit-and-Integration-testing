package com.minsura.dao;

import com.minsura.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeDAO {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

//    Employee updateEmployee(Long id, Employee employee);
Employee updateEmployee(Employee updatedEmployee);

    Employee updateEmployeeByFields(Long id, Map<String, Object> fields);

    void deleteEmployeeById(Long id);
}
