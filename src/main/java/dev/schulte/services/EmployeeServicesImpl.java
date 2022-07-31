package dev.schulte.services;

import dev.schulte.daos.EmployeeDAO;
import dev.schulte.entities.Employee;

import java.util.List;
import java.util.Map;

public class EmployeeServicesImpl implements EmployeeServices{

    private final EmployeeDAO employeeDAO;

    public EmployeeServicesImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        if(employee.getFname().length() == 0){
            throw new RuntimeException("Must have a first name");
        }
        if(employee.getLname().length() == 0){
            throw new RuntimeException("Must have a last name");
        }

        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public Employee retrieveEmployeeById(int id) {

        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return this.employeeDAO.getAllEmployees();
    }

    @Override
    public boolean deleteEmployee(int id) {
        boolean isSuccessful = this.employeeDAO.deleteEmployeeById(id);
        return isSuccessful;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(employee.getFname().length() == 0){
            throw new RuntimeException("Must have a first name");
        }
        if(employee.getLname().length() == 0){
            throw new RuntimeException("Must have a last name");
        }
        return this.employeeDAO.updateEmployee(employee);
    }
}
