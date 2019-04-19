import com.coursework.models.Agency;
import com.coursework.models.Contract;
import com.coursework.models.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestAgency{
    //Minimal custom dataset required to test functionality of the Agency class
    private Agency agency;
    private Employee e1;
    private Employee e2;
    private Employee e3;
    private Employee e4;
    private Contract c1;
    private Contract c2;
    private Contract c3;

    /**
     * Instantiate the data before every test runs
     */
    @Before
    public void init(){
        agency = new Agency();
        //c1 obj => e1 and e2 work on it
        c1 = new Contract();
        c1.setId(1);
        c1.setCost(500);
        c1.setMaxEmployees(3);
        List<Employee> c1List = new ArrayList<Employee>(Arrays.asList(e1,e2));
        c1.setEmployees(c1List);

        //c2 obj => e1 and e2 work on it
        c2 = new Contract();
        c2.setId(2);
        c2.setMaxEmployees(3);
        c2.setCost(1000);
        c2.setEmployees(new ArrayList<>(Arrays.asList(e1)));
        c3 = new Contract();
        c3.setId(3);
        c3.setCost(1000);
        c3.setMaxEmployees(1);
        c3.setEmployees(Arrays.asList(e3));
        //e1 obj => works on two contracts c1 & c2
        e1 = new Employee();
        e1.setId(1);
        e1.setEmployeeName("Jack");
        e1.setContracts(new ArrayList<>(Arrays.asList(c1,c2)));
        //e2 obj => works on one contract c1
        e2 = new Employee();
        e2.setId(2);
        e2.setEmployeeName("Chris");
        e2.setContracts(new ArrayList<>(Arrays.asList(c1)));
        //Works on all the three contracts
        e3 = new Employee();
        e3.setId(3);
        e3.setContracts(new ArrayList<>(Arrays.asList(c1,c2,c3)));
        //e4 obj => works on contract c1
        e4 = new Employee();
        e4.setId(4);
        e4.setContracts(new ArrayList<>(Arrays.asList(c1)));
        //Add the employees to the agency object
        agency.setEmployeeList(new ArrayList<>(Arrays.asList(e1,e2,e3,e4)));
    }
    @Test
    public void testComputerLimitNumWhenThereAreOnlyOneElementsInTheList(){
        List<Employee> employees = new ArrayList<>(Arrays.asList(e1));
        Assert.assertEquals(1,agency.computeLimitNum(employees));
    }
    @Test
    public void testComputerLimitNumWhereThereAreMultipleElements(){
        List<Employee> sortedEmployees = agency.getEmployeeList().stream().sorted((o1, o2) -> Integer.valueOf(o1.getContracts().size()).compareTo(Integer.valueOf(o2.getContracts().size()))).collect(Collectors.toList());
        Assert.assertEquals(2,agency.computeLimitNum(sortedEmployees));
    }
    @Test
    public void testGetEmployeesWithLeastContractsWhenThereAreNoEmployeesWithSameLeastNumberOfContracts(){
        //e2 should be expected as it is the object with the least contracts
        Assert.assertEquals(e2,agency.getEmployeeWithLeastContracts());
    }
    @Test
    public void testGetEmployeesWithLeastContractsWhenThereAreEmployeesWithSameLeastNumberOfContracts(){
        //e3 should be expected as it the method should return either e1 or e2 because they have the same number of contracts.
        Assert.assertNotEquals(e3,agency.getEmployeeWithLeastContracts());
    }
    @Test
    public void testGetOverallCost() {
        //total cost will be 2500 => 500(c1)+1000(c2)+1000(c3)
       Assert.assertEquals(2500, agency.getOverallCost());
    }
    @Test
    public void  testRemoveEmployee(){
        Assert.assertTrue("Should be true" , agency.removeEmployee(e1));
    }
    @Test
    public void testRemoveContract(){
        Assert.assertTrue("should be true", agency.removeContract(c2));
    }

}
