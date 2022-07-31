package dev.schulte.app;

import dev.schulte.daos.EmployeeDaoLocal;
import dev.schulte.entities.Employee;
import dev.schulte.handlers.*;
import dev.schulte.services.EmployeeServices;
import dev.schulte.services.EmployeeServicesImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static EmployeeServices employeeServices = new EmployeeServicesImpl(new EmployeeDaoLocal());

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();

        app.post("/employees", createEmployeeHandler);
        app.get("/employees/{id}", getEmployeeByIdHandler);
        app.get("/employees", getAllEmployeesHandler);
        app.delete("/employees/{id}", deleteEmployeeHandler);
        app.put("/employees/{id}", updateEmployeeHandler);

        app.start();

    }
}
