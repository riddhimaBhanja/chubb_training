package com.app.main;

public class Employee {
    private String name;
    private String bank;
    private double salary;

    public Employee(String name, String bank, double salary) {
        this.name = name;
        this.bank = bank;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getBank() { return bank; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (" + bank + ") - ₹" + salary;
    }
}
