package com.minsura.service;


import com.minsura.model.Employee;
import com.minsura.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;


class EmployeeServiceTest {


    private EmployeeService employeeService;

    private EmployeeRepository employeeRepository;


    //1
    @BeforeEach
    public void setup() {

        employeeRepository = Mockito.mock(EmployeeRepository.class); //1 mock the repository
        employeeService = new EmployeeService(employeeRepository); //2

    }


    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {

        //given - precondition or set up
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john_doe998@gmail.com")
                .build();

//        System.out.println(employeeRepository);
//        System.out.println(employeeService);
        //use BDD mockito method for findByEmail method in EmployeeService class
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());

        //now for save() method in EmployeeService class
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or the behavior that is being tested

        Employee savedEmployee = employeeService.saveEmployee(employee);


//        System.out.println(savedEmployee);

        //then- verify the output
        Assertions.assertThat(savedEmployee).isNotNull();


    }


}