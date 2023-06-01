package com.minsura.service;


import com.minsura.model.Employee;
import com.minsura.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


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
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        //now test  save() method in EmployeeService class
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or the behavior that is being tested

        Employee savedEmployee = employeeService.saveEmployee(employee);


        System.out.println(savedEmployee); // print the savedEmployee

        //then- verify the output
        Assertions.assertThat(savedEmployee).isNotNull();


    }


}