package dev.schulte.services;

import dev.schulte.daos.ExpenseDAO;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;

import java.util.List;

public class ExpenseServicesImpl implements  ExpenseServices{

    private final ExpenseDAO expenseDAO;

    public ExpenseServicesImpl(ExpenseDAO expenseDAO){
        this.expenseDAO = expenseDAO;
    }

    @Override
    public Expense registerExpense(Expense expense) {
        if(expense.getCost() == 0){
            throw new RuntimeException("Must have a reimbursement amount");
        }
        if(expense.getCost() < 0){
            throw new RuntimeException("Request cannot be negative");
        }
        if(expense.getEmployee() == 0 || expense.getEmployee() < 0){
            throw new RuntimeException("Request must be made by a valid employee");
        }
        expense.setStatus(Status.PENDING);
        this.expenseDAO.createExpense(expense);
        return expense;
    }

    @Override
    public Expense retrieveExpenseById(int id) {
        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public List<Expense> getStatus(Status status) {
        return this.expenseDAO.getStatus(status);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        if(expense.getCost() == 0){
            throw new RuntimeException("Must have a reimbursement amount");
        }
        if(expense.getCost() < 0){
            throw new RuntimeException("Request cannot be negative");
        }
        if(expense.getEmployee() == 0 || expense.getEmployee() < 0){
            throw new RuntimeException("Request must be made by a valid employee");
        }
        expense.setStatus(Status.PENDING);
        this.expenseDAO.updateExpense(expense);
        return expense;
    }

    @Override
    public Expense updateExpenseStatus(int id, Status status) {
        Expense expense = this.expenseDAO.getExpenseById(id);
        if(expense.getStatus() == Status.APPROVED){
            throw new RuntimeException("This request has already been approved");
        }
        if(expense.getStatus() == Status.DENIED){
            throw new RuntimeException("This request has already been denied");
        }
        expense.setStatus(status);
        return expense;
    }

    @Override
    public boolean deleteExpense(int id) {
        Expense expense = this.expenseDAO.getExpenseById(id);
        if(expense.getStatus() == Status.APPROVED || expense.getStatus() == Status.DENIED){
            throw new RuntimeException("This request cannot be deleted");
        }
        boolean isSuccessful = this.expenseDAO.deleteExpenseById(id);
        return isSuccessful;
    }
}
