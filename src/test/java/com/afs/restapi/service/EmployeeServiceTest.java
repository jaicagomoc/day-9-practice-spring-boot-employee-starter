package com.afs.restapi.service;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.EmployeeCreateException;
import com.afs.restapi.repository.EmployeeJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    @Test
    void should_set_employee_active_status_to_true_by_default_when_create_employee_given_employee_service() {
        //given
        Employee employee = getEmployeeBob();
        when(mockedEmployeeJpaRepository.save(employee)).thenReturn(employee);
        //when
        Employee savedEmployeeResponse = employeeService.create(employee);
        //then
        Assertions.assertTrue(savedEmployeeResponse.isActiveStatus());
    }
//    @Test
//    void should_throw_exception_when_saveEmployee_given_employee_service_and_employee_age_is_less_than_65() {
//        //given
//        Employee employee = getEmployeeBob();
//        //when
//        EmployeeCreateException employeeCreateException = Assertions
//                .assertThrows(EmployeeCreateException.class, () -> {
//                    employeeService.create(employee);
//                });
//        //then
//        Assertions.assertEquals("Employee must be 18~65 years old.", employeeCreateException.getMessage());
//    }
//    @Test
//    void should_update_employee_active_status_to_false_when_deleteEmployee_given_employee_service_employee_id() {
//        //given
//        Employee employee = getEmployeeBob();
//        when(mockedEmployeeJpaRepository.findById(employee.getId())).thenReturn(employee);
//        //when
//        employeeService.delete(employee.getId());
//        //then
//        verify(mockedEmployeeJpaRepository).updateEmployee(argThat((tempEmployee) -> {
//            Assertions.assertFalse(tempEmployee.isActiveStatus());
//            Assertions.assertEquals(employee.getId(), employee.getId());
//            Assertions.assertEquals(employee.getName(), employee.getName());
//            Assertions.assertEquals(employee.getAge(), employee.getAge());
//            Assertions.assertEquals(employee.getGender(), employee.getGender());
//            Assertions.assertEquals(employee.getSalary(), employee.getSalary());
//            return true;
//        }), eq(employee.getId()));
//    }

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
