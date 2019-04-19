package com.agency.models;

import java.util.*;
import java.util.stream.Collectors;

public class Agency {
    // Agency has many employees working in it - so Agency has one-to-many relation with Employee
    private List<Employee> employeeList;

    public Agency(){};

    public Agency(List<Employee> e){
        this.employeeList = e;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * This method computes the number of employees
     * with the same number of minimum contracts
     * @param employees - List of employees sorted according to the number of contracts each Employee has
     * @return counter - number of employees with the same minimum amount of contracts
     */
    private int computeLimitNum(List<Employee> employees){
        //Min possible
        int counter = 1;
        for (int i = 0 ; i<employees.size()-1;i++){
            if(employees.get(i).getContracts().size() == employees.get(i+1).getContracts().size()){
                counter++;
            }else break;
        }
        return counter;
    }

    /**
     * Used to get an employee that has the least number of contracts
     * The algorithm makes use of the computeLimitNum() method to get the number
     * of employees that have the same number of
     * @return
     */
    public Employee getEmployeeWithLeastContracts(){
        //Sort the Employees in order of the number of contracts they've got
        List<Employee> sortedList =  employeeList.stream().sorted((o1, o2) -> Integer.valueOf(o1.getContracts().size()).compareTo(Integer.valueOf(o2.getContracts().size()))).collect(Collectors.toList());
        //Return the employee - findAny() method picks up a random employee object from the stream.
        return sortedList.stream().limit(computeLimitNum(sortedList)).findAny().get();
    }

    public int getOverallCost(){
        //Get distinct set of contracts
        Set<Contract> contracts = new HashSet<>();
        //Populate the set of contracts
        employeeList.forEach(employee -> employee.getContracts().forEach(contract -> contracts.add(contract)));
        //Return the sum of contracts in the contracts set using the getCost() method.
        return contracts.stream().collect(Collectors.summingInt(Contract::getCost));
    }
}
