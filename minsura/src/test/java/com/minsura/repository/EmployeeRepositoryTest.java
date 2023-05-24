package com.minsura.repository;


import com.minsura.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


// Testing the EmployeeRepository
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    // JUnit test for save employee method
    @DisplayName("JUnit test for save employee")
    @Test
    public void given_EmployeeObject_whenSave_thenReturnSavedEmployeeObject() {

        //given - precondition or set up

        Employee employee = Employee.builder()
                .firstName("Mahmoud")
                .lastName("Osman")
                .email("osman778@yahoo.com")
                .build();

        //when - action or the behavior that is being tested
        Employee savedEmployee = employeeRepository.save(employee);

        //then- verify the output

        Assertions.assertThat(savedEmployee).isNotNull();

        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);


    }


    // JUnit test for getAll employees method

    @DisplayName("JUnit test for getAll employees")
    @Test
    public void givenEmployeesList_whenFindAll_thenReturnEmployeesList() {

        //given - precondition or set up
        //create employee object
        Employee employee1 = Employee.builder()
                .firstName("Mahmoud")
                .lastName("osman")
                .email("osman778@yahoo.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john_doe900@yahoo.com")
                .build();
        //save the 2 employee objects
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when - action or the behavior that is being tested
        //--findAll() method - the behavior that is being tested
        List<Employee> employeeList = employeeRepository.findAll();


        //then- verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2); // should return 2 object/employees

    }


}