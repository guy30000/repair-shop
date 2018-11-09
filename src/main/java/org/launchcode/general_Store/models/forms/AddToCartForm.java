package org.launchcode.general_Store.models.forms;

import javax.validation.constraints.Min;
import java.util.ArrayList;

public class AddToCartForm {

    // private Iterable<Inventory> Inventory;


    @Min(-1)
    private ArrayList quantity;

    private ArrayList itemId;

    public AddToCartForm() {
    }

    public AddToCartForm(ArrayList itemId, ArrayList quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    //gets and sets
    public ArrayList getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList quantity) {
        this.quantity = quantity;
    }

    public ArrayList getItemId() {
        return itemId;
    }

    public void setItemId(ArrayList itemId) {
        this.itemId = itemId;
    }
}

