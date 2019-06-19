package org.launchcode.repair_shop.models.forms;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class NewPeople {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=150)
    private String firstName;

    @NotNull
    @Size(min=1, max=150)
    private String lastName;


    @Size(min=1, max=1)
    private String middleInitial;

    @Email
    private String email;

    @NotNull
    @Size(min=10,max=10)
    private String phoneNumber;

    public NewPeople(int id, String firstName, String lastName, String middleInitial, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public NewPeople(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
