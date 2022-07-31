package dev.schulte.daos;

import dev.schulte.entities.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {

    // Create
    Employee createEmployee(Employee employee);

    // Read
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();

    // Update
    Employee updateEmployee(Employee employee);

    // Delete
    boolean deleteEmployeeById(int id);
}
