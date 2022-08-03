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
        String string = ctx.queryParam("status");
        if(string != null){
            String status = string.toLowerCase();
            switch (status){
                case "pending":
                    List<Expense> pending = App.expenseServices.getStatus(Status.PENDING);
                    Gson gsonPending = new Gson();
                    String jsonPending = gsonPending.toJson(pending);
                    ctx.result(jsonPending);
                    break;
                case "approved":
                    List<Expense> approved = App.expenseServices.getStatus(Status.APPROVED);
                    Gson gsonApproved = new Gson();
                    String jsonApproved = gsonApproved.toJson(approved);
                    ctx.result(jsonApproved);
                    break;
                case "denied":
                    List<Expense> denied = App.expenseServices.getStatus(Status.DENIED);
                    Gson gsonDenied = new Gson();
                    String jsonDenied = gsonDenied.toJson(denied);
                    ctx.result(jsonDenied);
                    break;
                default:
                    ctx.result("Not a valid status");
            }
        }else{
            List<Expense> expenses = App.expenseServices.getAllExpenses();
            Gson gson = new Gson();
            String json = gson.toJson(expenses);
            ctx.result(json);
        }
    }
}
