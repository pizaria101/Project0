package dev.schulte.services;

import dev.schulte.entities.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {

    Employee registerEmployee(Employee employee);

    Employee retrieveEmployeeById(int id);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(int id);

    Employee updateEmployee(Employee employee);
}
