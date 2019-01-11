package models;

import java.util.ArrayList;

public class Reader extends LibraryItem {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    static ArrayList<Reader> allReaders = new ArrayList<>();

    public Reader(String id, String firstName, String lastName, String phoneNumber, String email, ArrayList<Reader> allReaders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        Reader.allReaders = allReaders;

    }

    @Override
    public String getDueDate() {
        return null;
    }

    @Override
    public void setDueDate() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ArrayList<Reader> getAllReaders() {
        return allReaders;
    }
}
