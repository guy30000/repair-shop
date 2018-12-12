package org.launchcode.general_Store.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Invoice {


    @Id
    @GeneratedValue
    private int id;

    //keep this commented out until you figure it out


    // https://www.youtube.com/watch?v=otuxIIbtznA
    @ManyToMany  //2:00 p1
    private List<Inventory> itemsInCart = new ArrayList<>();

    @NotNull
    private String timeAndDate;

    //construcors //just added this
    public Invoice( List<Inventory> itemsInCart, String timeAndDate) {
        this.itemsInCart = itemsInCart;
        this.timeAndDate = timeAndDate;
    }
    public Invoice() {}


    //Calendar timeAndDate = Calendar.getInstance();
    // This will be the format for searching and storing date- System.out.println((cal.get(Calendar.MONTH) + 1 ) +"/"+ (cal.get(Calendar.DAY_OF_MONTH)) +"/"+ cal.get(Calendar.YEAR) +" "+ cal.get(Calendar.HOUR_OF_DAY) +":"+cal.get(Calendar.MINUTE));
    //shows up as "10/5/6 13:46"  Notice teh plus onein date. This is because colander calls Jan 0


//    public Invoice(Int id) {
//        this.id = id;
//    }
//    public Invoice() { }

///getters and setters
/////look at this
    public void addInvItem(Inventory invItem){ itemsInCart.add(invItem);}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Inventory> getItemsInCart() {
        return itemsInCart;
    }
///>>>> ///////////////changed this v
//    public void setItemsInCart(Inventory itemsInCart) {
//        this.itemsInCart = itemsInCart;
//    }
    public void setItemsInCart(List<Inventory> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

}
