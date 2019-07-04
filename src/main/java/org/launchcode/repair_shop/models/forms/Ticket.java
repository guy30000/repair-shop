package org.launchcode.repair_shop.models.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=50)
    private String itemName;

    private String itemDescription;

    @NotNull
    @Size(min=1, max=1000)
    private String itemIssue;

    private ArrayList<String> itemNotes;

    @ManyToOne
    private NewPeople customer;

    public Ticket(int id, String itemName, String itemDescription, String itemIssue, ArrayList itemNotes) {
    }


    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemIssue() {
        return itemIssue;
    }

    public void setItemIssue(String itemIssue) {
        this.itemIssue = itemIssue;
    }

    public List<String> getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(ArrayList<String> itemNotes) {
        this.itemNotes = itemNotes;
    }

    public NewPeople getCustomer() {
        return customer;
    }

    public void setCustomer(NewPeople customer) {
        this.customer = customer;
    }
}
