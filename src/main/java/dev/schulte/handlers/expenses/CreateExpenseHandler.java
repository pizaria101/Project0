package dev.schulte.handlers.expenses;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
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
}
