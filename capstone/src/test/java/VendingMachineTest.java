import com.techelevator.Item;
import com.techelevator.VendingMachine;
import org.junit.Assert;
import org.junit.Test;
import com.techelevator.Duck;
import com.techelevator.Penguin;
import com.techelevator.Pony;
import com.techelevator.Cat;

import java.io.File;
import java.math.BigDecimal;

public class VendingMachineTest {

    VendingMachine testV = new VendingMachine();

    @Test
    public void createLogFileTest() {
        testV.createLogFile();
        File testFile = new File("C:\\Users\\Student\\workspace\\java-minicapstonemodule1-team3\\capstone\\log.txt");
        Assert.assertTrue(testFile.exists());
    }

    @Test
    public void feedMoneyTest() {
        testV.feedMoney("5");
        Assert.assertEquals(new BigDecimal(5), testV.getCurrentBalance());
        testV.feedMoney("5.69");
        Assert.assertEquals(new BigDecimal("10.69"), testV.getCurrentBalance());
    }

    @Test
    public void dispenseTest() {
        Item testDuck = new Duck("Jake Duck", new BigDecimal(50));
        Item testPenguin = new Penguin("Tom Penguin", new BigDecimal(350));
        Item testPony = new Pony("Ian Pony", new BigDecimal(200));
        Item testCat = new Cat("Sarah Cat", new BigDecimal(4000));
        testV.feedMoney("5000");
        testV.dispense(testDuck);
        Assert.assertEquals(new BigDecimal(4950), testV.getCurrentBalance());
        testV.dispense(testPenguin);
        Assert.assertEquals(new BigDecimal(4600), testV.getCurrentBalance());
        testV.dispense(testPony);
        Assert.assertEquals(new BigDecimal(4400), testV.getCurrentBalance());
        testV.dispense(testCat);
        Assert.assertEquals(new BigDecimal(400), testV.getCurrentBalance());
    }

}
