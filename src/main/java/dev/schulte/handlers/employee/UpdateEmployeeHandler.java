package dev.schulte.handlers.employee;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee retrieveEmployee = App.employeeServices.retrieveEmployeeById(id);
        if(retrieveEmployee == null){
            ctx.status(404);
            ctx.result("Could not find employee");
        }else{
            String employeeJson = ctx.body();
            Gson gson = new Gson();
            Employee employee = gson.fromJson(employeeJson, Employee.class);
            Employee updatedEmployee = App.employeeServices.updateEmployee(employee);
            String json = gson.toJson(updatedEmployee);
            ctx.result(json);
        }
    }
}
