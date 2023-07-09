package com.minsura.service;

import com.minsura.dao.EmployeeDAO;
import com.minsura.exception.ResourceNotFoundException;
import com.minsura.model.Employee;
import com.minsura.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeDAO {

    //    @Autowired - you don't need annotation if you have parametrized constructor
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());

        if (savedEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee's email already exists " + employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find this id " + id)));
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }
//    @Override
//    public Employee updateEmployee(Long id, Employee employee) {
//        return null;
//    }

    @Override
    public Employee updateEmployeeByFields(Long id, Map<String, Object> fields) {
        return null;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> deleteEmployee = employeeRepository.findById(id);
        if (deleteEmployee.isEmpty()) {
            throw new ResourceNotFoundException("Employee with this id doesn't exist " + id);
        }
        employeeRepository.deleteById(id);

    }
}
