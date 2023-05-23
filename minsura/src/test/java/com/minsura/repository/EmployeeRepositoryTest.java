package com.minsura.repository;


import com.minsura.model.Employee;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


// Testing the EmployeeRepository
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    // JUnit test for save employee method
    @DisplayName("JUnit test for save employee method")
    @Test
    public void given_EmployeeObject_whenSave_thenReturnSavedEmployeeObject() {

        //given - precondition or set up

        Employee employee = Employee.builder()
                .firstName("Mahmoud")
                .lastName("osman")
                .email("osman778@yahoo.com")
                .build();

        //when - action or the behavior that is being tested
        Employee savedEmployee = employeeRepository.save(employee);

        //then- verify the output

        Assertions.assertThat(savedEmployee).isNotNull();

        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);


    }

}