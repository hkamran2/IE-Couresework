package com.agency.models;

import java.util.List;

public class Contract {
    // give the contract a unique id
    private Integer id;
    // cost affiliated with a contract
    private Integer cost;
    //Max. employees that can work on the Contract
    private Integer maxEmployees;
    // One contract can have many employees working on it - so Contract has one-to-many relation with Employee
    private List<Employee> employees;

    private Agency agency;

    public Contract(){};

    public Contract(Integer id, Integer cost){
        this.id = id;
        this.cost = cost;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(Integer maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public boolean addDesigner(){
        if (this.employees.size()<this.maxEmployees){
            return this.employees.add(agency.getEmployeeWithLeastContracts());
        }
        return false;
    }
}
