package com.minsura.service;


import com.minsura.exception.ResourceNotFoundException;
import com.minsura.model.Employee;
import com.minsura.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import static org.mockito.BDDMockito.given;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john_doe998@gmail.com")
                .build();
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
        Assertions.assertThat(savedEmployee).isNotNull();


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
    public void givenEmployeesList_whenGetAllEmployees_thenReturnsEmployeesList() {

        //given - precondition or set up

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Sarah")
                .lastName("Smith")
                .email("sarah250@gmail.com")
                .build();

        //stubbing findAll() method
        BDDMockito.given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        //when - action or the behavior that is being tested
        //stubbing for getAllEmployees() method
        List<Employee> employeesList = employeeService.getAllEmployees();


        //then- verify the output
        Assertions.assertThat(employeesList).isNotNull(); // check that the list is not empty
        Assertions.assertThat(employeesList.size()).isEqualTo(2); // list size
        System.out.println(employeesList.size());


    }


}