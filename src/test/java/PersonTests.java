import businessLayer.Bank;
import businessLayer.BankProc;
import dataAccessLayer.BankHashMapDAO;
import dataAccessLayer.SerializationHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by radu on 14.05.2017.
 */
public class PersonTests {

    private String bankFileForTest = "ser/test/bank.ser";
    private BankProc bankProc;

    @Before
    public void init(){
        bankProc = new Bank(new BankHashMapDAO(bankFileForTest));
        SerializationHelper.initializeBankFile(bankFileForTest);
    }

    @Test
    public void getAllPerson(){
        assert bankProc.getAllPerson().size() == 4;
    }

    @After
    public void tearDown(){}
}
