package dev.schulte.services;

import dev.schulte.daos.expense.ExpenseDAO;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;

import java.util.ArrayList;
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
        expense.setStatus(Status.PENDING);
        this.expenseDAO.createExpense(expense);
        return expense;
    }

    @Override
    public Expense retrieveExpenseById(int expenseId) {

        return this.expenseDAO.getExpenseById(expenseId);
    }

    @Override
    public List<Expense> getAllExpenses() {

        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public List<Expense> getStatus(Status status) {
        List<Expense> allExpenses = this.getAllExpenses();
        List<Expense> sortedExpenses = new ArrayList();
        for(Expense expense : allExpenses){
            if(expense.getStatus().equals(status)){
                sortedExpenses.add(expense);
            }
        }
        return sortedExpenses;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        if(expense.getCost() == 0){
            throw new RuntimeException("Must have a reimbursement amount");
        }
        if(expense.getCost() < 0){
            throw new RuntimeException("Request cannot be negative");
        }
        if(!expense.getStatus().equals(Status.PENDING)){
            throw new RuntimeException("Request cannot be altered");
        }
        this.expenseDAO.updateExpense(expense);
        return expense;
    }

    @Override
    public Expense updateExpenseStatus(int expenseId, Status status) {
        Expense expense = this.expenseDAO.getExpenseById(expenseId);
        if(expense.getStatus().equals(Status.APPROVED)){
            throw new RuntimeException("Request has already been approved");
        }
        if(expense.getStatus().equals(Status.DENIED)){
            throw new RuntimeException("Request has already been denied");
        }
        expense.setStatus(status);
        this.expenseDAO.updateExpense(expense);
        return expense;
    }

    @Override
    public boolean deleteExpense(int expenseId) {
        Expense expense = this.expenseDAO.getExpenseById(expenseId);
        if(expense.getStatus().equals(Status.APPROVED) || expense.getStatus().equals(Status.DENIED)){
            throw new RuntimeException("This request cannot be deleted");
        }
        boolean isSuccessful = this.expenseDAO.deleteExpenseById(expenseId);
        return isSuccessful;
    }
}
