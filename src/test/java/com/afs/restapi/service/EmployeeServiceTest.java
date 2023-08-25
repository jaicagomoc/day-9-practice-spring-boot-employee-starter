package com.afs.restapi.service;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.EmployeeJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    private EmployeeService employeeService;
    private EmployeeJpaRepository mockedEmployeeJpaRepository;

    @BeforeEach
    void setUp() {
        mockedEmployeeJpaRepository = Mockito.mock(EmployeeJpaRepository.class);
        employeeService = new EmployeeService(mockedEmployeeJpaRepository);
    }

    @Test
    void should_return_saved_employee_when_saveEmployee_given_employee_service_and_employee_valid_age() {
        //given
        Employee employee = getEmployeeBob();
        Employee savedEmployee = getEmployeeLily();
        when(mockedEmployeeJpaRepository.save(employee)).thenReturn(savedEmployee);
        //when
        Employee savedEmployeeResponse = employeeService.create(employee);
        //then
        Assertions.assertEquals(savedEmployee.getId(), savedEmployeeResponse.getId());
        Assertions.assertEquals(savedEmployee.getName(), savedEmployeeResponse.getName());
        Assertions.assertEquals(savedEmployee.getAge(), savedEmployeeResponse.getAge());
        Assertions.assertEquals(savedEmployee.getGender(), savedEmployeeResponse.getGender());
        Assertions.assertEquals(savedEmployee.getSalary(), savedEmployeeResponse.getSalary());

    }

    private static Employee getEmployeeBob() {
        Employee employee = new Employee();
        employee.setName("Bob");
        employee.setAge(22);
        employee.setGender("Male");
        employee.setSalary(10000);
        return employee;
    }

    private static Employee getEmployeeSusan() {
        Employee employee = new Employee();
        employee.setName("Susan");
        employee.setAge(23);
        employee.setGender("Female");
        employee.setSalary(11000);
        return employee;
    }

    private static Employee getEmployeeLily() {
        Employee employee = new Employee();
        employee.setName("Lily");
        employee.setAge(24);
        employee.setGender("Female");
        employee.setSalary(12000);
        return employee;
    }
}
