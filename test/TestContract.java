import com.coursework.models.Agency;
import com.coursework.models.Contract;
import com.coursework.models.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TestContract {
    //Custom data set
    private Agency agency;
    private Contract c1;
    private Contract c2;
    private Employee e1;
    private Employee e2;
    @Before
    public void init(){
        agency = new Agency();
        c1 = new Contract();
        c1.setMaxEmployees(1);
        c2 = new Contract();
        c2.setMaxEmployees(2);
        c2.setEmployees(new ArrayList<Employee>(Arrays.asList(e1)));
        e1 = new Employee();
        e1.setContracts(Arrays.asList(c1,c2));
        e2 = new Employee();
        e2.setContracts(new ArrayList<>());
        c1.setEmployees(new ArrayList<>(Arrays.asList(e1)));
        agency.setEmployeeList(Arrays.asList(e1,e2));
    }
    @Test
    public void testAddDesignerWhenMoreEmployeesCanBeAddedToContractList(){
        Assert.assertTrue("", c2.addDesigner(agency));
    }
    @Test
    public void testAddDesignerWhenTheContractListIsFull(){
        Assert.assertFalse("Should be false",c1.addDesigner(agency));
    }
    @Test
    public void testHasEmployees(){
        Assert.assertTrue("Should be true",c1.hasEmployees() );
    }
    @Test
    public void testHasReachedMaxEmployees(){
        Assert.assertTrue("Should be true", c1.maxLimitReached());
    }
    @Test
    public void testRemoveContract(){
        Assert.assertTrue("Should be true", c1.removeEmployee(e1));
    }
    @Test
    public void testContractAgreed(){
        Assert.assertFalse("Should be false",c1.contractAgreed() );
    }
}
