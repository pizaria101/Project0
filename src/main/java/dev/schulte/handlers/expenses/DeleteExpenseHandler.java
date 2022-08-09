package dev.schulte.handlers.expenses;

import dev.schulte.app.App;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
        Expense getExpense = App.expenseServices.retrieveExpenseById(expenseId);
        if(getExpense == null){
            ctx.status(404);
            ctx.result("Could not find request");
        }else if(getExpense.getStatus() == Status.APPROVED || getExpense.getStatus() == Status.DENIED){
            ctx.status(422);
            ctx.result("Request cannot be deleted");
            return;
        }else{
        boolean result = App.expenseServices.deleteExpense(expenseId);
            ctx.status(200);
        }
    }
}
