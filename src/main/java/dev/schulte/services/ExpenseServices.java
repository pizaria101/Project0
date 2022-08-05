package dev.schulte.services;

import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;

import java.util.List;

public interface ExpenseServices {

    Expense registerExpense(Expense expense);

    Expense retrieveExpenseById(int expenseId);

    List<Expense> getAllExpenses();

    List<Expense> getStatus(Status status);

    Expense updateExpense(Expense expense);

    Expense updateExpenseStatus(int expenseId, Status status);

    boolean deleteExpense(int expenseId);
}
