package dev.schulte.handlers.employee;

import dev.schulte.app.App;
import dev.schulte.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
        Employee getEmployee = App.employeeServices.retrieveEmployeeById(employeeId);
        if(getEmployee == null){
            ctx.status(404);
            ctx.result("Could not find employee");
        }else{
        boolean result = App.employeeServices.deleteEmployee(employeeId);
        ctx.status(200);
        }
    }
}
