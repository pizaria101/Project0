package dev.schulte.handlers.employee;

import dev.schulte.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
        boolean result = App.employeeServices.deleteEmployee(employeeId);
        if(result){
            ctx.status(204);
        }else{
            ctx.status(404);
            ctx.result("Could not find employee");
        }
    }
}
