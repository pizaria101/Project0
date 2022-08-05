package dev.schulte.app;

import dev.schulte.daos.employee.EmployeeDaoLocal;
import dev.schulte.daos.employee.EmployeeDaoPostgres;
import dev.schulte.daos.expense.ExpenseDaoLocal;
import dev.schulte.daos.expense.ExpenseDaoPostgres;
import dev.schulte.handlers.employee.*;
import dev.schulte.handlers.expenses.*;
import dev.schulte.services.EmployeeServices;
import dev.schulte.services.EmployeeServicesImpl;
import dev.schulte.services.ExpenseServices;
import dev.schulte.services.ExpenseServicesImpl;
import io.javalin.Javalin;

public class App {

    public static EmployeeServices employeeServices = new EmployeeServicesImpl(new EmployeeDaoPostgres());

    public static ExpenseServices expenseServices = new ExpenseServicesImpl(new ExpenseDaoPostgres());

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
        app.get("/employees/{employeeId}", getEmployeeByIdHandler);
        app.get("/employees", getAllEmployeesHandler);
        app.put("/employees/{employeeId}", updateEmployeeHandler);
        app.delete("/employees/{employeeId}", deleteEmployeeHandler);

        app.post("/expenses", createExpenseHandler);
        app.get("/expenses", getAllExpensesHandler);
        app.get("/expenses/{expenseId}", getExpenseByIdHandler);
        app.put("/expenses/{expenseId}", updateExpenseHandler);
        app.patch("/expenses/{expenseId}/{status}", updateExpenseStatusHandler);
        app.delete("/expenses/{expenseId}", deleteExpenseHandler);

        //app.get("/employees/{id}/expenses", null);
        //app.post("/employees/{id}/expenses", null);


        app.start();

    }
}
