package dev.schulte.daos;

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
        expense.setId(idMaker);
        // String.format("%.2f",d)
        idMaker++;
        this.expenseMap.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        return this.expenseMap.get(id);
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
        this.expenseMap.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public Expense updateExpenseStatus(int id, Status status) {
        Expense expense = this.expenseMap.get(id);
        expense.setStatus(status);
        this.expenseMap.put(id, expense);
        return expense;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense expense = this.expenseMap.remove(id);
        if(expense == null){
            return false;
        }
        return true;
    }
}
