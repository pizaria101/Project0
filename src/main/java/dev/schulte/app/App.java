package dev.schulte.app;

import dev.schulte.daos.EmployeeDaoLocal;
import dev.schulte.daos.ExpenseDaoLocal;
import dev.schulte.handlers.employee.*;
import dev.schulte.handlers.expenses.*;
import dev.schulte.services.EmployeeServices;
import dev.schulte.services.EmployeeServicesImpl;
import dev.schulte.services.ExpenseServices;
import dev.schulte.services.ExpenseServicesImpl;
import io.javalin.Javalin;

public class App {

    public static EmployeeServices employeeServices = new EmployeeServicesImpl(new EmployeeDaoLocal());

    public static ExpenseServices expenseServices = new ExpenseServicesImpl(new ExpenseDaoLocal());

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();

        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        GetAllExpensesHandler getAllExpensesHandler = new GetAllExpensesHandler();
        GetExpenseByIdHandler getExpenseByIdHandler = new GetExpenseByIdHandler();
        UpdateExpenseHandler updateExpenseHandler = new UpdateExpenseHandler();
        UpdateExpenseStatusHandler updateExpenseStatusHandler = new UpdateExpenseStatusHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();

        app.post("/employees", createEmployeeHandler);
        app.get("/employees/{id}", getEmployeeByIdHandler);
        app.get("/employees", getAllEmployeesHandler);
        app.put("/employees/{id}", updateEmployeeHandler);
        app.delete("/employees/{id}", deleteEmployeeHandler);

        app.post("/expenses", createExpenseHandler);
        app.get("/expenses", getAllExpensesHandler);
        app.get("/expenses/{id}", getExpenseByIdHandler);
        app.put("/expenses/{id}", updateExpenseHandler);
        app.patch("/expenses/{id}/{status}", updateExpenseStatusHandler);
        app.delete("/expenses/{id}", deleteExpenseHandler);

        //app.get("/employees/{id}/expenses", null);
        //app.post("/employees/{id}/expenses", null);


        app.start();

    }
}
