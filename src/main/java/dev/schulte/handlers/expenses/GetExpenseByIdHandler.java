package dev.schulte.handlers.expenses;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
        Expense expense = App.expenseServices.retrieveExpenseById(expenseId);
        if(expense == null){
            ctx.status(404);
            ctx.result("Could not find expense");
        }else{
            Gson gson = new Gson();
            String json = gson.toJson(expense);
            ctx.result(json);
        }
    }
}
