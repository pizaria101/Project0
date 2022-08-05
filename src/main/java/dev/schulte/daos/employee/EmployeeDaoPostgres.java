package dev.schulte.daos.employee;

import dev.schulte.entities.Employee;
import dev.schulte.entities.Expense;
import dev.schulte.utils.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into employee values (default, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("employee_id");
            employee.setEmployeeId(generatedKey);
            return employee;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee where employee_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("employee_id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));

            return employee;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFname(rs.getString("fname"));
                employee.setLname(rs.getString("lname"));
                employeeList.add(employee);
            }
            return employeeList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update employee set fname = ?, lname = ? where employee_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());
            preparedStatement.setInt(3, employee.getEmployeeId());

            preparedStatement.executeUpdate();
            return employee;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeById(int employeeId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from employee where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
