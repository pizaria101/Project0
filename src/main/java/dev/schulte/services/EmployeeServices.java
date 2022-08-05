package dev.schulte.services;

import dev.schulte.entities.Employee;

import java.util.List;

public interface EmployeeServices {

    Employee registerEmployee(Employee employee);

    Employee retrieveEmployeeById(int employeeId);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(int employeeId);

    Employee updateEmployee(Employee employee);
}
