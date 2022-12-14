package dev.schulte.handlers.expenses;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
        Expense getExpense = App.expenseServices.retrieveExpenseById(expenseId);
        if(getExpense == null){
            ctx.status(404);
            ctx.result("Could not find reimbursement request");
        }if(!getExpense.getStatus().equals(Status.PENDING)){
            ctx.status(422);
            ctx.result("Request cannot be altered");
        }
        else{
            String expenseJson = ctx.body();
            Gson gson = new Gson();
            Expense expense = gson.fromJson(expenseJson, Expense.class);
            Expense updatedExpense = App.expenseServices.updateExpense(expense);
            String json = gson.toJson(updatedExpense);
            ctx.result(json);
        }
    }
}
