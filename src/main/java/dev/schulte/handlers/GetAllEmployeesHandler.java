package dev.schulte.handlers;

import com.google.gson.Gson;
import dev.schulte.app.App;
import dev.schulte.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class GetAllEmployeesHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        List<Employee> employee = App.employeeServices.getAllEmployees();
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.result(json);
    }
}
