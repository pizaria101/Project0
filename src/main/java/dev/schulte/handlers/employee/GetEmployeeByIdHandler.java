package dev.schulte.handlers.employee;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
        Employee employee = App.employeeServices.retrieveEmployeeById(employeeId);
        if(employee == null){
            ctx.status(404);
            ctx.result("Could not find employee");
        }else{
            Gson gson = new Gson();
            String json = gson.toJson(employee);
            ctx.result(json);
        }
    }
}
