package dataAccessLayer;

import entities.Account;
import entities.Person;

import javax.jnlp.PersistenceService;
import java.io.*;
import java.util.*;

/**
 * Created by radu on 12.05.2017.
 */
public class SerializationHelper {

    private static String bankFileName = "ser/bank.ser";
    private static String accountFileName = "ser/account.ser";
    private static String personFileName = "ser/person.ser";

    /**
     *  Checks if there is existent file with specified name
     * @param name
     * @return true if there is a file, false otherwise
     */
    public static boolean fileExists(String name){
        File file = new File(name);
        if(file.exists()){
            return true;
        }
        return false;
    }


    /**
     *  Creates a new file with specified name if there is none existent
     * @param name
     */
    public static void createNewFile(String name){

        File file = new File(name);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Method used to store serialized data into specified file
     * @param object
     * @param fileName
     */
    public static void writeIntoFile(Object object, String fileName){

        try {
            if(!fileExists(fileName)){
                createNewFile(fileName);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Method used to retrieve and deserialize data from a specified file
     * @param fileName
     * @return
     */
    public static Object readFromFile(String fileName){
        Object object = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createNewFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }


    /**
     *  Method used for initialization of the storage files with predefined data
     */
    public static void initializeFiles(){
        writeIntoFile(createPersonHashSet(), personFileName);
        writeIntoFile(createAccountsList(), accountFileName);
        writeIntoFile(createBankHash(),bankFileName);
    }


    private static Set<Person> createPersonHashSet(){
        HashSet<Person> personHashSet = new HashSet<Person>();
        personHashSet.add(new Person(1,"11111111111", "Dorel Gigel", "Baritiu 26"));
        personHashSet.add(new Person(2,"22222222222", "Petunia Ghita", "Titulescu 234"));
        personHashSet.add(new Person(3,"23232323233", "Gigi", "la el acasa"));
        personHashSet.add(new Person(4,"12141414141", "Floarea Trica", "Baritiu 28"));

        return personHashSet;
    }

    private static List<Account> createAccountsList(){
        LinkedList<Account> accounts = new LinkedList<Account>();
        accounts.add(new Account(1,"1234567890", "spending", 222.30,1));
        accounts.add(new Account(2,"5626535233", "saving", 123.00,2));
        accounts.add(new Account(3,"2343434888", "saving", 2355.35,4));
        accounts.add(new Account(4,"1421424244", "spending", 333.30,3));
        accounts.add(new Account(5,"2222343434", "spending", 422.45,1));
        accounts.add(new Account(6,"5464656455", "saving", 2.35,2));
        accounts.add(new Account(7,"8765434555", "saving", 7677.50,2));
        accounts.add(new Account(8,"3455454333", "spending", 123.45,4));
        accounts.add(new Account(9,"9876543333", "saving", 388.30,4));
        accounts.add(new Account(10,"1323232345", "saving", 939.93,3));
        accounts.add(new Account(11,"4567345562", "saving", 5453.30,2));
        accounts.add(new Account(12,"8765444444", "spending", 22.55,4));
        accounts.add(new Account(13,"3330020200", "spending", 30.50,3));
        accounts.add(new Account(14,"1010001010", "spending", 100.00,2));

        return accounts;
    }


    private static Map<Person,List<Account>> createBankHash(){
        Set<Person> personHashSet = createPersonHashSet();
        List<Account> accounts = createAccountsList();

        HashMap<Person,List<Account>> bankAccountsHash = new HashMap<Person, List<Account>>();
        for(Person p : personHashSet){
            List<Account> personAccountsList = new LinkedList<Account>();
            for(Account a : accounts){
                if(p.getId() == a.getPersonId()){
                    personAccountsList.add(a);
                }
            }
            bankAccountsHash.put(p,personAccountsList);
        }

        return bankAccountsHash;
    }

}
