import com.coursework.models.Agency;
import com.coursework.models.Contract;
import com.coursework.models.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestEmployee {
    @Test
    public void testAssignContracts(){
        Employee e = new Employee();
        e.setContracts(new ArrayList<>());
        e.assignContract(new Contract());
        Assert.assertTrue("",e.getContracts().size() == 1);
    }
    @Test
    public void testHasContracts(){
        Employee e = new Employee();
        e.setContracts(new ArrayList<>());
        Assert.assertFalse("Should be false",e.hasContracts() );
    }
    @Test
    public void testHasNoContracts(){
        Employee e = new Employee();
        e.setContracts(new ArrayList<>());
        Assert.assertTrue("Should be false",e.hasNoContracts());
    }
    @Test
    public void testDeleteContract(){
        Contract c1 = new Contract();
        c1.setId(1);c1.setCost(111);
        Employee e = new Employee();
        e.setContracts(new ArrayList<>(Arrays.asList(c1)));
        Assert.assertTrue("Should be true", e.deleteContract(c1));
    }
}
