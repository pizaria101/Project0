package dev.schulte.services;

import dev.schulte.entities.Employee;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;

import java.util.List;

public interface ExpenseServices {

    Expense registerExpense(Expense expense);

    Expense retrieveExpenseById(int id);

    List<Expense> getAllExpenses();

    List<Expense> getStatus(Status status);

    Expense updateExpense(Expense expense);

    Expense updateExpenseStatus(int id, Status status);

    boolean deleteExpense(int id);
}
