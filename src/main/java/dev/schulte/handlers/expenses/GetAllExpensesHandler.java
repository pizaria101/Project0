package dev.schulte.handlers.expenses;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllExpensesHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String status = ctx.queryParam("status");
        Gson gson = new Gson();
        if(status == null){
            List<Expense> expenses = App.expenseServices.getAllExpenses();
            String json = gson.toJson(expenses);
            ctx.result(json);
        }else{
            List<Expense> expenses = App.expenseServices.getStatus(Status.valueOf(status));
            String json = gson.toJson(expenses);
            ctx.result(json);
        }
    }
}
