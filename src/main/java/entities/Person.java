package entities;

import java.io.Serializable;

/**
 * Created by radu on 12.05.2017.
 */
public class Person implements Serializable {

    private int id;
    private String personalNumericalCode;
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

}

