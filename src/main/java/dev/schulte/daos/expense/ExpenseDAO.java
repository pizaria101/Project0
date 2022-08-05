package dev.schulte.daos.expense;

import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;

import java.util.List;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense getExpenseById(int expenseId);
    List<Expense> getAllExpenses();
    List<Expense> getStatus(Status status);

    Expense updateExpense(Expense expense);
    Expense updateExpenseStatus(int expenseId, Status status);

    boolean deleteExpenseById(int expenseId);


}
