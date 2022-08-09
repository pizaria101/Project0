package dev.schulte.app;

import com.google.gson.Gson;
import dev.schulte.daos.employee.EmployeeDaoLocal;
import dev.schulte.daos.employee.EmployeeDaoPostgres;
import dev.schulte.daos.expense.ExpenseDaoLocal;
import dev.schulte.daos.expense.ExpenseDaoPostgres;
import dev.schulte.entities.Expense;
import dev.schulte.handlers.employee.*;
import dev.schulte.handlers.expenses.*;
import dev.schulte.services.EmployeeServices;
import dev.schulte.services.EmployeeServicesImpl;
import dev.schulte.services.ExpenseServices;
import dev.schulte.services.ExpenseServicesImpl;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

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

        Handler getAllExpensesByEmployeeId = ctx -> {
            int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
            if (App.employeeServices.retrieveEmployeeById(employeeId) == null){
                ctx.status(404);
                ctx.result("Employee not found");
            }else{
                Gson gson = new Gson();
                List<Expense> expenses = App.expenseServices.getAllExpenses();
                List<Expense> employeeExpense = new ArrayList();
                for(Expense e : expenses){
                    if(e.getEmployee() == employeeId){
                        employeeExpense.add(e);
                    }
                }
                String json = gson.toJson(employeeExpense);
                ctx.result(json);
            }
        };

        app.get("/employees/{employeeId}/expenses", getAllExpensesByEmployeeId);

        Handler createExpenseByEmployeeId = ctx -> {
            int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
            if (App.employeeServices.retrieveEmployeeById(employeeId) == null){
                ctx.status(404);
                ctx.result("Employee not found");
            }else{
                String json = ctx.body();
                Gson gson = new Gson();
                Expense expense = gson.fromJson(json, Expense.class);
                if(expense.getCost() <= 0){
                    ctx.result("Must have a valid reimbursement amount");
                }else {
                    Expense registeredExpense = App.expenseServices.registerExpense(expense);
                    String expenseJson = gson.toJson(registeredExpense);
                    ctx.status(201);
                    ctx.result(expenseJson);
                }

            }
        };
        app.post("/employees/{employeeId}/expenses", createExpenseByEmployeeId);


        app.start();

    }
}
