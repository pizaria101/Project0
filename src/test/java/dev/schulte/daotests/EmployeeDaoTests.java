package dev.schulte.daotests;

import dev.schulte.daos.employee.EmployeeDAO;
import dev.schulte.daos.employee.EmployeeDaoLocal;
import dev.schulte.daos.employee.EmployeeDaoPostgres;
import dev.schulte.entities.Employee;
import dev.schulte.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table employee(\n" +
                    "\temployee_id serial primary key,\n" +
                    "\tfname varchar(30) not null,\n" +
                    "\tlname varchar(30) not null\n" +
                    ");";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee = new Employee(0,"Tom","Finnegan");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0,savedEmployee.getEmployeeId());
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
        System.out.println(employee);
    }

    @Test
    @Order(5)
    void delete_employee_test(){
        boolean result = employeeDAO.deleteEmployeeById(1);
        Assertions.assertTrue(result);
    }

    @AfterAll
    static void teardown(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table employee";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
