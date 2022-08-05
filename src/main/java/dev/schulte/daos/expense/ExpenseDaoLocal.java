package dev.schulte.daos.expense;

import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDaoLocal implements ExpenseDAO {

    private Map<Integer, Expense> expenseMap = new HashMap();
    private int idMaker = 1;

    @Override
    public Expense createExpense(Expense expense) {
        expense.setExpenseId(idMaker);
        // String.format("%.2f",d)
        idMaker++;
        this.expenseMap.put(expense.getExpenseId(), expense);
        return expense;
    }

    @Override
    public Expense getExpenseById(int expenseId) {

        return this.expenseMap.get(expenseId);
    }

    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<Expense>(this.expenseMap.values());
        return expenses;
    }

    @Override
    public List<Expense> getStatus(Status status) {
        List<Expense> expenseList = new ArrayList<Expense>(this.expenseMap.values());
        List<Expense> statusList = new ArrayList();
        for(Expense e : expenseList){
            if(e.getStatus() == status){
                statusList.add(e);
            }
        }
        return statusList;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        this.expenseMap.put(expense.getExpenseId(), expense);
        return expense;
    }

    @Override
    public Expense updateExpenseStatus(int expenseId, Status status) {
        Expense expense = this.expenseMap.get(expenseId);
        expense.setStatus(status);
        this.expenseMap.put(expenseId, expense);
        return expense;
    }

    @Override
    public boolean deleteExpenseById(int expenseId) {
        Expense expense = this.expenseMap.remove(expenseId);
        if(expense == null){
            return false;
        }
        return true;
    }
}
