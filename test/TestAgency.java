import com.agency.models.Agency;
import com.agency.models.Contract;
import com.agency.models.Employee;
import com.sun.corba.se.pept.transport.ContactInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestAgency{

    @Test
    public void testGetEmployeesWithLeastContractsWhenThereAreNoEmployeesWithSameLeastNumberOfContracts(){
        Employee e1 = new Employee(1,"John",Arrays.asList(new Contract(),new Contract()));
        Employee e2 = new Employee(2,"Harry", Arrays.asList(new Contract()));
        Agency agency = new Agency();
        agency.setEmployeeList(Arrays.asList(e1,e2));
        Assert.assertEquals(e2,agency.getEmployeeWithLeastContracts());
    }
    @Test
    public void testGetEmployeesWithLeastContractsWhenThereAreEmployeesWithSameLeastNumberOfContracts(){
        Employee e1 = new Employee(1,"John",Arrays.asList(new Contract(),new Contract()));
        Employee e2 = new Employee(2,"Harry", Arrays.asList(new Contract(), new Contract()));
        Employee e3 = new Employee(3,"Tom",Arrays.asList(new Contract(), new Contract(), new Contract()));
        Agency agency = new Agency();
        agency.setEmployeeList(Arrays.asList(e1,e2));
        Assert.assertNotEquals(e3,agency.getEmployeeWithLeastContracts());
    }
    @Test
    public void testGetOverallCost() {
        Contract c1 = new Contract(1, 500); Contract c2 = new Contract(2, 1000);
        Employee e1 = new Employee(1, "Jack", Arrays.asList(c1,c2));
        Employee e2 = new Employee(2,"Jonas",Arrays.asList(c1));
        Agency agency = new Agency();
        agency.setEmployeeList(Arrays.asList(e1,e2));
        Assert.assertEquals(1500, agency.getOverallCost());
    }
}
