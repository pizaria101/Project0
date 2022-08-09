package dev.schulte.daos.expense;

import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;
import dev.schulte.entities.Type;
import dev.schulte.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoPostgres implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into expense values (default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, expense.getCost());
            preparedStatement.setString(2, expense.getStatus().toString());
            preparedStatement.setInt(3, expense.getEmployee());
            preparedStatement.setString(4, expense.getDescription());
            preparedStatement.setString(5, expense.getType().toString());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("expense_id");
            expense.setExpenseId(generatedKey);
            return expense;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, expenseId);

            ResultSet rs = ps.executeQuery();
            if (!rs.next())
            {
                return null;
            }

            Expense expense = new Expense();
            expense.setExpenseId(rs.getInt("expense_id"));
            expense.setCost(rs.getDouble("expense_cost"));
            expense.setStatus(Status.valueOf(rs.getString("status")));
            expense.setEmployee(rs.getInt("employee"));
            expense.setDescription(rs.getString("description"));
            expense.setType(Type.valueOf(rs.getString("expense_type")));

            return expense;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setCost(rs.getDouble("expense_cost"));
                expense.setStatus(Status.valueOf(rs.getString("status")));
                expense.setEmployee(rs.getInt("employee"));
                expense.setDescription(rs.getString("description"));
                expense.setType(Type.valueOf(rs.getString("expense_type")));
                expenseList.add(expense);
            }
            return expenseList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getStatus(Status status) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense where status = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status.name());

            ResultSet rs = ps.executeQuery();

            List<Expense> statusList = new ArrayList();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setCost(rs.getDouble("expense_cost"));
                expense.setStatus(Status.valueOf(rs.getString("status")));
                expense.setEmployee(rs.getInt("employee"));
                expense.setDescription(rs.getString("description"));
                expense.setType(Type.valueOf(rs.getString("expense_type")));
                statusList.add(expense);
            }
            return statusList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update expense set expense_cost = ?, description = ?, expense_type = ? where expense_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, expense.getCost());
            preparedStatement.setString(2, expense.getDescription());
            preparedStatement.setString(3, expense.getType().toString());
            preparedStatement.setInt(4, expense.getExpenseId());

            preparedStatement.executeUpdate();
            return expense;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpenseStatus(int expenseId, Status status) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set status = ? where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.toString());
            ps.setInt(2, expenseId);

            ps.executeUpdate();
            return getExpenseById(expenseId);

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExpenseById(int expenseId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expenseId);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
