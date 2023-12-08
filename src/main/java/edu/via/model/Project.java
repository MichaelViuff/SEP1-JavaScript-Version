package edu.via.model;

public class Project
{

    private Employee employee;

    private String name;
    private double budget;
    private int id;

    public Project(String name, double budget) {
        this.name = name;
        this.budget = budget;
        employee = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(this.id == 0)
            this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "employee=" + employee +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", id=" + id +
                '}';
    }
}
