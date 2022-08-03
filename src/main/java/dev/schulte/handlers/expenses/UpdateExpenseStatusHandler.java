package dev.schulte.handlers.expenses;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseStatusHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense getExpense = App.expenseServices.retrieveExpenseById(id);
        if(getExpense == null){
            ctx.status(404);
            ctx.result("Could not find reimbursement request");
        }
        String string = ctx.pathParam("status");
        String status = string.toLowerCase();
        switch (status){
            case "approved":
                String expenseJson = ctx.body();
                Gson gson = new Gson();
                Expense expense = gson.fromJson(expenseJson, Expense.class);
//                if(getExpense.getStatus() == Status.APPROVED){
//                    String expenseJson = ctx.body();
//                    Gson gson = new Gson();
//                    Expense expense = gson.fromJson(expenseJson, Expense.class);
//                    Expense approvedExpense = App.expenseServices.updateExpenseStatus(id, Status.APPROVED);
//                    String json = gson.toJson(approvedExpense);
//                    ctx.result(json);
//                }
//                if(getExpense.getStatus() == Status.DENIED){
//                    String expenseJson = ctx.body();
//                    Gson gson = new Gson();
//                    Expense expense = gson.fromJson(expenseJson, Expense.class);
//                    Expense deniedExpense = App.expenseServices.updateExpenseStatus(id, Status.DENIED);
//                    String json = gson.toJson(deniedExpense);
//                    ctx.result(json);
//                }
        }
    }
}
