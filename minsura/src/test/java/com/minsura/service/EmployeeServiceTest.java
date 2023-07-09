package com.minsura.service;


import com.minsura.exception.ResourceNotFoundException;
import com.minsura.model.Employee;
import com.minsura.repository.EmployeeRepository;
//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat; // used static & assertThat subclass

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import static org.mockito.BDDMockito.given;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;


    //1
    @BeforeEach
    public void setup() {
        employee = Employee.builder().id(1L).firstName("John").lastName("Doe").email("john_doe998@gmail.com").build();
    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {

        //given - precondition or set up
        System.out.println(employeeRepository); // print the object
        System.out.println(employeeService); // print the object
        //use BDD Mockito method for findByEmail method in EmployeeService class
        //stubbing findByEmail() method
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        //now test  save() method in EmployeeService class
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or the behavior that is being tested

        Employee savedEmployee = employeeService.saveEmployee(employee);


        System.out.println(savedEmployee); // print the savedEmployee

        //then- verify the output
        assertThat(savedEmployee).isNotNull();


    }


    // JUnit test for saveEmployee method that throws exception
    @DisplayName("JUnit test for saveEmployee method with throws exception")
    @Test
    public void givenExistingEmployeeEmail_whenSaveEmployee_thenThrowsException() {

        //given - precondition or set up
        //stubbing findByEmail() method

        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        //when - action or the behavior that is being tested
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> { //lambda expression
            employeeService.saveEmployee(employee);
        });

        //then- verify the output
        verify(employeeRepository, never()).save(any(Employee.class));

    }


    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method")

    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {

        //given - precondition or set up

        Employee employee1 = Employee.builder().id(2L).firstName("Sarah").lastName("Smith").email("sarah250@gmail.com").build();

        //stubbing findAll() method
        BDDMockito.given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        //when - action or the behavior that is being tested
        //stubbing for getAllEmployees() method
        List<Employee> employeesList = employeeService.getAllEmployees();

        //then- verify the output
        assertThat(employeesList).isNotNull(); // check that the list is not empty
        assertThat(employeesList.size()).isEqualTo(2); // list size

    }


    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method - negative scenario")

    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList() {

        //given - precondition or set up
        Employee employee1 = Employee.builder().id(1L).firstName("Sarah").lastName("Smith").email("sarah250@gmail.com").build();

        BDDMockito.given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //when - action or the behavior that is being tested
        List<Employee> employeesList = employeeService.getAllEmployees();

        //then- verify the output
        assertThat(employeesList).isEmpty();
        assertThat(employeesList.size()).isEqualTo(0);


    }


    // JUnit test for getEmployeeById method
    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {

        //given - precondition or set up

        BDDMockito.given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        //when - action or the behavior that is being tested
        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();
        //then- verify the output
        assertThat(savedEmployee).isNotNull();


    }


    // JUnit test for updateEmployee method
    @DisplayName("JUnit test for updateEmployee method")

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        //given - precondition or set up

        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
        //update employee info
        employee.setFirstName("Aliyah");
        employee.setLastName("Omar");
        employee.setEmail("aliyah88@yahoo.com");

        //when - action or the behavior that is being tested

        Employee updatedEmployee = employeeService.updateEmployee(employee);


        //then- verify the output

        assertThat(updatedEmployee.getFirstName()).isEqualTo("Aliyah");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Omar");
        assertThat(updatedEmployee.getEmail()).isEqualTo("aliyah88@yahoo.com");

        /*========= Print first name, last name & email to see the changes ========= */
        System.out.println(updatedEmployee.getFirstName());
        System.out.println(updatedEmployee.getLastName());
        System.out.println(updatedEmployee.getEmail());


    }


}