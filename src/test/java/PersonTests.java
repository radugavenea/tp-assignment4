import businessLayer.Bank;
import businessLayer.BankProc;
import dataAccessLayer.BankHashMapDAO;
import dataAccessLayer.SerializationHelper;
import entities.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by radu on 14.05.2017.
 */
public class PersonTests {

    private String bankFileForTest = "ser/test/bank.ser";
    private BankProc bankService;

    @Before
    public void init(){
        bankService = new Bank(new BankHashMapDAO(bankFileForTest));
        SerializationHelper.initializeBankFile(bankFileForTest);
    }

    @Test
    public void getAllPersonTest(){
        assert bankService.getAllPerson().size() == 4;
    }

    @Test
    public void addNewPersonTest(){
        int count = bankService.getAllPerson().size();
        bankService.addNewPerson(new Person(100,"234234324","nume","eeeee"));
        assert bankService.getAllPerson().size() == count + 1;
    }

    @Test
    public void editPersonTest(){
        Person person = bankService.getPersonById(1);
        String personName = person.getName();
        person.setName("Ciciiiiici");
        assert !bankService.getPersonById(1).getName().equals(personName);
    }

    @Test
    public void deletePersonTest(){
        int count = bankService.getAllPerson().size();
        bankService.deletePerson(bankService.getPersonById(1));
        assert bankService.getAllPerson().size() == count - 1;
    }

    @After
    public void tearDown(){}
}
