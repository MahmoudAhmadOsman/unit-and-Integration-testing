package com.minsura.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsura.model.Employee;
import com.minsura.service.EmployeeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest //loads all the required components to test this class
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    // creates mock instance of EmployeeService & adds it to the application context that then injects into EmployeeController
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper; // serializes Java objects


    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("James")
                .lastName("Smith")
                .email("james_smith2023@gmail.com")
                .build();

        //Stub the method
        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        //when - action or the behavior that is being tested

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));


        //then- verify the output
        response.andDo(MockMvcResultHandlers.print()) // print the result
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));


    }


}