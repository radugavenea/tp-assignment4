package dataAccessLayer;

import entities.Person;

import java.util.Set;

/**
 * Created by radu on 14.05.2017.
 */
public class PersonDAO {

    private final String personFileName = "ser/person.ser";

    /**
     *  gets all people from the storage file "person.ser"
     * @return a HashSet structure of all people from file, null if no person
     */
    public Set<Person> getAllPerson(){
        return (Set<Person>) SerializationHelper.readFromFile(personFileName);
    }

}
