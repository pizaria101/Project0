package dev.schulte.daos;

import dev.schulte.entities.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoLocal implements EmployeeDAO{

    private Map<Integer, Employee> employeeMap = new HashMap();
    private int idMaker = 1;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(idMaker);
        idMaker++;
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(int id) {

        return employeeMap.get(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>(this.employeeMap.values());
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        Employee employee = employeeMap.remove(id);
        if(employee == null){
        return false;
        }
        return true;
    }
}

