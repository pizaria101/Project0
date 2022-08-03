package dev.schulte.entities;

public class Expense {

    private int id;
    private double cost;
    private Status status;
    private int employee;
    private String description;
    private Type type;
    //lodging, misc, travel,
    //prioritize cost
    //expense.getType.name

    public Expense(){

    }

    public Expense(int id, double cost, Status status, int employee, String description, Type type) {
        this.id = id;
        this.cost = cost;
        this.status = status;
        this.employee = employee;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", cost=" + cost +
                ", status=" + status +
                ", employee=" + employee +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
