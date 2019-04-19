package com.coursework.models;

import java.util.List;

public class Employee {
    //Give employee a unique id
    private Integer id;
    // Employee has a particular name
    private String employeeName;
    //A single employee can work on many contracts - so Employee has one-to-many relationship with Contract
    private List<Contract> contracts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    /**
     * Adds the passed in contract to the list
     * @param c - contract to be added
     * @return true- if the contract is added successfully otherwise return false
     */
    public boolean assignContract(Contract c){ return getContracts().add(c);}

    /**
     * Checks if the user has got any contracts
     * @return true - if the user has been assigned contracts, otherwise false
     */
    public boolean hasContracts(){return this.getContracts().size() != 0;}

    /**
     * Checks if the employee is not working on any contracts
     * @return true - if user has got no contracts, false otherwise
     */
    public boolean hasNoContracts(){return this.contracts.size() == 0;}

    /**
     * Removes the contract from the list
     * @param c - the contract object to be removed from the list
     * @return true - if the contract is deleted, false otherwise
     */
    public boolean deleteContract(Contract c){
        return this.getContracts().remove(c);
    }
}
