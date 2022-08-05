package dev.schulte.daos.employee;

import dev.schulte.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    // Create
    Employee createEmployee(Employee employee);

    // Read
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();

    // Update
    Employee updateEmployee(Employee employee);

    // Delete
    boolean deleteEmployeeById(int employeeId);
}
