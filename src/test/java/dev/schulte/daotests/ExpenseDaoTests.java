package dev.schulte.daotests;

import dev.schulte.daos.expense.ExpenseDAO;
import dev.schulte.daos.expense.ExpenseDaoLocal;
import dev.schulte.daos.expense.ExpenseDaoPostgres;
import dev.schulte.entities.Expense;
import dev.schulte.entities.Status;
import dev.schulte.entities.Type;
import dev.schulte.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTests {

    static ExpenseDAO expenseDAO = new ExpenseDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table expense(\n" +
                    "expense_id serial primary key,\n" +
                    "expense_cost decimal(6,2) not null,\n" +
                    "status varchar(10) not null,\n" +
                    "employee int references employee(employee_id),\n" +
                    "description varchar(100),\n" +
                    "expense_type varchar(10) not null\n" +
                    ");\n";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();;
        }
    }

    @Test
    @Order(1)
    void create_expense_test(){
        Expense expense = new Expense(0,254.23, Status.PENDING,1,"Drove to Alabama",Type.travel);
        Expense savedExpense = expenseDAO.createExpense(expense);
        System.out.println(savedExpense);
        Assertions.assertNotEquals(0,savedExpense.getExpenseId());
    }

    @Test
    @Order(2)
    void get_expense_by_id_test(){
        Expense expense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(254.23, expense.getCost());
    }

    @Test
    @Order(3)
    void get_all_expenses_test(){
        List<Expense> expenses = expenseDAO.getAllExpenses();
        Assertions.assertTrue(expenses.size() > 0);
    }

    @Test
    @Order(4)
    void get_expense_status_test(){
        List<Expense> expenseStatus = expenseDAO.getStatus(Status.PENDING);
        Assertions.assertTrue(expenseStatus.size() > 0);
    }

    @Test
    @Order(5)
    void update_expense_test(){
        Expense expense2 = new Expense(1, 260.00, Status.PENDING, 1, "Drove to Alabama, got lost", Type.travel);
        expenseDAO.updateExpense(expense2);
        Expense expense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(260.00, expense.getCost());
    }

    @Test
    @Order(6)
    void update_expense_status_test(){
        expenseDAO.updateExpenseStatus(1, Status.APPROVED);
        Expense expense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(Status.APPROVED, expense.getStatus());
    }

    @Test
    @Order(7)
    void delete_expense_by_id_test(){
        boolean result = expenseDAO.deleteExpenseById(1);
        Assertions.assertTrue(result);
    }

    @AfterAll
    static void teardown(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table expense";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
