package org.launchcode.general_Store.models;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.launchcode.general_Store.models.data.InventoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.launchcode.general_Store.models.Inventory;
import org.launchcode.models.Cheese;

import java.util.ArrayList;
//Used CheeseDate from E:\_SSD Libraries\Google Drive\Launch Code\Unit_3\CHeese versins\cheese-mvc-video-models-part2-end for help here
public class Cart {
//cheese = Inventory
    //cheeses= cart

    @Autowired
    private InventoryDao inventoryDao;

    static ArrayList<Inventory> cart = new ArrayList<>(); //this initilizes an arraylist called cart that should accept inventory entries


    public static ArrayList<Inventory> getAll() {  //retuns entire cart. Should be used in view and to add the invoice to db
        return cart;
    }

//    public static void add(Inventory newItem) {cart.add(newItem);}  //this was from the cheese but doesn look like it will work for me

//    public static void add(int id) {  // copied the remove commands as that looks more usefull. I should be able to add Inv item by id.
//        Inventory cartItemToAdd = getById(id);
//        cart.add(cartItemToAdd);
//    }
//
//    public static void remove(int id) {  //obvioulsy, remove item by id. Not needing anytime soon.
//        Inventory cartItemToRemove = getById(id);
//        cart.remove(cartItemToRemove);
//    }
//
//
//    public static Inventory getById(int id) {  //this is used in the add/remove methds to get by id
//
//        Inventory theItem = null;
//       // InventoryDao inventoryDao = null;
//        for ( Inventory candidateItem : inventoryDao.findAll()) {  /// Inspecting loading the database here
//            if (candidateItem.getId() == id) {
//                theItem = candidateItem;
//            }
//        }
//        return theItem;
//    }

    public static void clearCart() { //this shoud effective reset teh array list. Used when sending the completed order to DB or just clearing car
        ArrayList<Inventory> cart = new ArrayList<>();
    }

}
