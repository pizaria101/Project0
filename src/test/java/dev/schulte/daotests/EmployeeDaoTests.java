package dev.schulte.daotests;

import dev.schulte.daos.EmployeeDAO;
import dev.schulte.daos.EmployeeDaoLocal;
import dev.schulte.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoLocal();

    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee = new Employee(0,"Tom","Finnegan");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }

    @Test
    @Order(2)
    void get_employee_by_id_test(){
        Employee employee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Tom", employee.getFname());
    }

    @Test
    @Order(3)
    void get_all_employees_test(){
        List<Employee> employees = employeeDAO.getAllEmployees();
        Assertions.assertTrue(employees.size() > 0);
    }

    @Test
    @Order(4)
    void update_employee_test(){
        Employee employee2 = new Employee(1, "Jason","Jarminey");
        employeeDAO.updateEmployee(employee2);
        Employee employee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Jason", employee.getFname());
    }

    @Test
    @Order(5)
    void delete_employee_test(){
        boolean result = employeeDAO.deleteEmployeeById(1);
        Assertions.assertTrue(result);
    }
}
