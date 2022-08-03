package dev.schulte.handlers.employee;

import dev.schulte.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.employeeServices.deleteEmployee(id);
        if(result){
            ctx.status(204);
        }else{
            ctx.status(404);
            ctx.result("Could not find employee");
        }
    }
}
