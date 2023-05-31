package com.minsura.service;

import com.minsura.dao.EmployeeDAO;
import com.minsura.exception.ResourceNotFoundException;
import com.minsura.model.Employee;
import com.minsura.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeDAO {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());

        if (savedEmployee.isPresent()) {
            throw new ResourceNotFoundException("Email already exists" + employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        return null;
    }

    @Override
    public Employee updateEmployeeByFields(Long id, Map<String, Object> fields) {
        return null;
    }

    @Override
    public void deleteEmployeeById(Long id) {

    }
}
