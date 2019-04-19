package com.coursework.models;
import java.util.*;
import java.util.stream.Collectors;

public class Agency {
    // Agency has many employees working in it - so Agency has one-to-many relation with Employee
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() { return employeeList; }

    public void setEmployeeList(List<Employee> employeeList) { this.employeeList = employeeList; }

    /**
     * Checks if the agency employees working on contracts
     * @return true - if the user has got employees otherwise false
     */
    public boolean hasEmployees(){ return this.getEmployeeList().size() != 0 ;}

    /**
     * This method computes the number of employees
     * with the same number of minimum contracts
     * @param employees - List of employees sorted according to the number of contracts each Employee has
     * @return counter - number of employees with the same minimum amount of contracts
     */
    public int computeLimitNum(List<Employee> employees){
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
     * of employees that have the same number of contracts
     * @return Employee - with the least amount of contracts, null if the size is 0
     */
    public Employee getEmployeeWithLeastContracts(){
        //If agency does not have any contracts then return null
        if(!hasEmployees()) return null;
        //If the employee list has only on employee then return the first element
        if(employeeList.size() == 1) return employeeList.get(0);
        //Sort the Employees in order of the number of contracts they've got
        List<Employee> sortedList =  employeeList.stream().sorted((o1, o2) -> Integer.valueOf(o1.getContracts().size()).compareTo(Integer.valueOf(o2.getContracts().size()))).collect(Collectors.toList());
        //Return the employee - findAny() method picks up a random employee object from the stream.
        return sortedList.stream().limit(computeLimitNum(sortedList)).findAny().get();
    }

    /**
     * Calculates the overall cost of the contracts that agency has
     * @return int - total cost of the contracts
     */
    public int getOverallCost(){
        //Get distinct set of contracts
        Set<Contract> contracts = new HashSet<>();
        //Populate the set of contracts
        employeeList.forEach(employee -> employee.getContracts().forEach(contract -> contracts.add(contract)));
        //Return the sum of contracts in the contracts set using the getCost() method.
        return contracts.stream().collect(Collectors.summingInt(Contract::getCost));
    }

    /**
     * Removes an employee from the agency
     * Simultaneously deletes the employee from
     * the contract list
     * @param e
     * @return
     */
    public boolean removeEmployee(Employee e ){
        // Delete the employee form the list of contracts it is working on.
        if(e.hasContracts()) for (Contract c : e.getContracts()) { c.removeEmployee(e); }
        return getEmployeeList().remove(e);
    }

    /**
     * Remove Contract from each employee
     * which means that agency is no longer
     * dealing with the particular contract
     * @param c - Contract to be removed
     */
    public boolean removeContract(Contract c){
        //terminate if list is empty
        if(getEmployeeList().isEmpty()) return false;
        for (Employee e : getEmployeeList()) {
            e.deleteContract(c);
        }
        return true;
    }
}
