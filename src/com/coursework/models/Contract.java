package com.agency.models;

import java.util.ArrayList;
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
    //Contract has one-to-one relationship with agency
    private Agency agency;

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Contract(){};

    public Contract(Integer id, Integer cost){
        this.id = id;
        this.cost = cost;
    }
    public Contract(Integer id , Integer cost , Integer maxEmployees,Agency agency){
        this.id = id;
        this.cost = cost;
        this.maxEmployees = maxEmployees;
        this.employees = new ArrayList<>(maxEmployees);
        this.agency = agency;
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

    /**
     * Adds the designer to the list of employees working on the contract
     * and returns true if the number of employees in the list
     * is less than the max. allowed employees
     * Otherwise, returns false
     * @return - true - if the user is added to the list.
     */
    public boolean addDesigner(){
        //Do the check
        if (this.employees.size()<this.maxEmployees){ return this.employees.add(agency.getEmployeeWithLeastContracts()); }
        //Return false otherwise
        return false;
    }
}
