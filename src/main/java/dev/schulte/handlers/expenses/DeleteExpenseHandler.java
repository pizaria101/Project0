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
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense getExpense = App.expenseServices.retrieveExpenseById(id);
        if(getExpense.getStatus() == Status.APPROVED || getExpense.getStatus() == Status.DENIED){
            ctx.status(422);
            ctx.result("Request cannot be deleted");
            return;
        }
        boolean result = App.employeeServices.deleteEmployee(id);
        if(result){
            ctx.status(204);
        }else{
            ctx.status(404);
            ctx.result("Could not find request");
        }
    }
}
