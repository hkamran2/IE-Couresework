package com.coursework.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    public boolean addDesigner(Agency agency){
        //Do the check
        if (!maxLimitReached()){
            //Get the employee object
            Employee e = agency.getEmployeeWithLeastContracts();
            //Create the contract object
            Contract c = new Contract();
            c.setId(this.getId());
            c.setEmployees(this.getEmployees());
            c.setMaxEmployees(this.getMaxEmployees());
            c.setCost(this.getCost());
            // assing the contract to the employee after adding to the employee list
            if(this.getEmployees().add(e)) return e.assignContract(c);
        }
        //Return false otherwise
        return false;
    }

    /**
     * Checks if the contract has employees working on it
     * @return true - if the employees are present, otherwise false
     */
    public boolean hasEmployees(){return this.getEmployees().size() != 0;}

    /**
     * Checks if the more employees can be added or not to the contract
     * @return true - if the number of the employees equal to the max employees, otherwise false
     */
    public boolean maxLimitReached(){return this.getEmployees().size() == this.maxEmployees;}

    /**
     * Remove an employee from this list
     * @param e - employee to be removed
     * @return true if the employee is removed otherwise false
     */
    public boolean removeEmployee(Employee e){return this.getEmployees().remove(e);}

    /**
     * A contract is presumed to be agreed if
     * the contract object has an id and cost
     * affiliated with it. This method checks
     * if the id and cost have been initialised
     * @return true if id and cost are associated with the object, otherwise false
     */
    public boolean contractAgreed(){
        if(this.id!=null && this.cost!= null) return true;
        return false;
    }
}
