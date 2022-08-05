package dev.schulte.entities;

import java.util.Objects;

public class Employee {

    private int employeeId;
    private String fname;
    private String lname;

    public Employee(){

    }

    public Employee(int employeeId, String fname, String lname) {
        this.employeeId = employeeId;
        this.fname = fname;
        this.lname = lname;
    }

    public int getEmployeeId() {

        return employeeId;
    }

    public void setEmployeeId(int employeeId) {

        this.employeeId = employeeId;
    }

    public String getFname() {

        return fname;
    }

    public void setFname(String fname) {

        this.fname = fname;
    }

    public String getLname() {

        return lname;
    }

    public void setLname(String lname) {

        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && fname.equals(employee.fname) && lname.equals(employee.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, fname, lname);
    }
}
