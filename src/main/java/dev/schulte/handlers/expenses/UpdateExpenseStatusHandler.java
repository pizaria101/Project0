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
        int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
        Expense getExpense = App.expenseServices.retrieveExpenseById(expenseId);
        if(getExpense == null){
            ctx.status(404);
            ctx.result("Could not find reimbursement request");
        }
        if(!getExpense.getStatus().equals(Status.PENDING)){
            ctx.status(422);
            ctx.result("Status cannot be changed");
        }else {
            String string = ctx.pathParam("status").toUpperCase();
            Gson gson = new Gson();
            Expense updatedStatus = App.expenseServices.updateExpenseStatus(expenseId, Status.valueOf(string));
            String json = gson.toJson(updatedStatus);
            ctx.result(json);
        }

    }
}
