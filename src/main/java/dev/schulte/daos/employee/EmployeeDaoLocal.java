package dev.schulte.daos.employee;

import dev.schulte.entities.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoLocal implements EmployeeDAO {

    private Map<Integer, Employee> employeeMap = new HashMap();
    private int idMaker = 1;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setEmployeeId(idMaker);
        idMaker++;
        this.employeeMap.put(employee.getEmployeeId(), employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return this.employeeMap.get(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>(this.employeeMap.values());
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        this.employeeMap.put(employee.getEmployeeId(), employee);
        System.out.println(employee);
        return employee;

    }

    @Override
    public boolean deleteEmployeeById(int employeeId) {
        Employee employee = this.employeeMap.remove(employeeId);
        if(employee == null){
            return false;
        }
        return true;
    }
}

