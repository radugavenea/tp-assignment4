package entities;

import java.io.Serializable;

/**
 * Created by radu on 12.05.2017.
 */
public class Person implements Serializable {

    private int id;
    private String personalNumericalCode;     // personal numerical code
    private String name;
    private String address;

    public Person(int id, String personalNumericalCode, String name, String address) {
        this.id = id;
        this.personalNumericalCode = personalNumericalCode;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonalNumericalCode() {
        return personalNumericalCode;
    }

    public void setPersonalNumericalCode(String personalNumericalCode) {
        this.personalNumericalCode = personalNumericalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (getPersonalNumericalCode() != null ? !getPersonalNumericalCode().equals(person.getPersonalNumericalCode()) : person.getPersonalNumericalCode() != null) return false;
        if (getName() != null ? !getName().equals(person.getName()) : person.getName() != null) return false;
        return getAddress() != null ? getAddress().equals(person.getAddress()) : person.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getPersonalNumericalCode() != null ? getPersonalNumericalCode().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
