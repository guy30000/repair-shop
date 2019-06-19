package org.launchcode.general_Store.controllers;

import org.launchcode.general_Store.models.Cart;
import org.launchcode.general_Store.models.Inventory;
import org.launchcode.general_Store.models.Invoice;
import org.launchcode.general_Store.models.data.InventoryDao;
import org.launchcode.general_Store.models.data.InvoicesDao;
import org.launchcode.general_Store.models.forms.AddToCartForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Calendar;



@Controller
@RequestMapping(value = "general_Store/Checkout")
public class CheckoutController {

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private InvoicesDao invoicesDao;

    @Autowired
    private InvoicesDao thisInvoice;

    static ArrayList<Integer> testReciptArray = new ArrayList<>(); //this is for testing

    @RequestMapping(value = "")
    public String displayregister(Model model) {
        model.addAttribute("title", "Checkout");
        System.out.println("CheckoutController");
        model.addAttribute("title", "");
        model.addAttribute("inventory", inventoryDao.findAll());
        return "general_Store/Checkout/checkout";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processregister(Model model, @RequestParam(required = false) String keyword, @RequestParam(required = false) String search, @RequestParam(required = false) String addToOrder, @ModelAttribute AddToCartForm addToCartForm) {
        /////////////////////////////////// Search FUnction
        if (search != null && search.equals("Search Inventory")) {
            ArrayList<Inventory> searchResults = new ArrayList<>();
            for ( Inventory singleItem : inventoryDao.findAll()) {
                if (singleItem.getName().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getVendor().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getSku().equalsIgnoreCase(keyword) ){
                    searchResults.add(singleItem);
                }
            }
            //model.addAttribute("title", "Search: " + keyword);
            model.addAttribute("inventory", searchResults);
            return "general_Store/Checkout/checkout";
        } ///////////////////////////////// End Search FUnction^
        ///////////////////////////////// Add to cart function
        if (addToOrder != null && addToOrder.equals("Add to Cart")) {
            if (addToCartForm.getItemId().size() == addToCartForm.getQuantity().size()) {
                System.out.println("sddinv test- " + addToCartForm);
                for (int i = 0; i < addToCartForm.getItemId().size(); i++) {
                    if (addToCartForm.getQuantity().get(i) == "") {
                        System.out.println("Null skipping");
                        continue;
                    } else {
                        //array is initilized at top of page
                        int idOfCurrentItem = Integer.valueOf((String) addToCartForm.getItemId().get(i));
                        //int idOfCurrentItem2 = Integer.valueOf((String) addToCartForm.getItemId().get(5)); //delet this line it is for testing
                        int purchaseQuantity = Integer.valueOf((String) addToCartForm.getQuantity().get(i));
                        Inventory itemToBeUpdated = inventoryDao.findOne(idOfCurrentItem);
                        Inventory testFullReciptList = inventoryDao.findOne(idOfCurrentItem); //this is for testing
                        //Inventory itemToBeUpdated2 = inventoryDao.findOne(idOfCurrentItem2);//delet this line it is for testing
                        testReciptArray.add(itemToBeUpdated.getId());
                        itemToBeUpdated.setStock(itemToBeUpdated.getStock() - purchaseQuantity);
                       // inventoryDao.save(itemToBeUpdated);
                        ///Tinkiring with invoice
                        System.out.println("Test invoice test1- " + Integer.valueOf((String) addToCartForm.getItemId().get(i)) + " - " + idOfCurrentItem);
//                        Cart.add(Integer.valueOf((String) addToCartForm.getItemId().get(i)));  //breaker
  //                      Cart.add(idOfCurrentItem);  //breaker
 //                       System.out.println("Test invoice test2- " + Cart.getById(0));

                        Calendar cal = Calendar.getInstance();
                        String currentTimeAndSate = ((cal.get(Calendar.MONTH) + 1 ) +"/"+ (cal.get(Calendar.DAY_OF_MONTH)) +"/"+ cal.get(Calendar.YEAR) +" "+ cal.get(Calendar.HOUR_OF_DAY) +":"+cal.get(Calendar.MINUTE));;

                        Invoice testInvoice =  new Invoice();
                        testInvoice.setTimeAndDate(currentTimeAndSate);
                       // testInvoice.setItemsInCart();
                        testInvoice.addInvItem(itemToBeUpdated);
                        testInvoice.addInvItem(itemToBeUpdated);
                        //testInvoice.addInvItem(itemToBeUpdated2);
                        System.out.println("Test invoice test3- " + testInvoice.getItemsInCart());
                        System.out.println("Test recipt " + itemToBeUpdated.getName() );
                       // InvoicesDao.save(testInvoice);
                        // Changed things oon Invoice
                        ///end tinker with invoice
                        //thisInvoice.setItemsInCart .add(addToCartForm.getItemId().get(i));


                        // notes somethign like this will give you single variable to contain inventory. But i've only gotten it to work with a completed list.
//                        Iterable<Inventory> testtesttest = inventoryDao.findAll(); ///temp test

                    }

                }}}
                    ///////////////////////////////// End Add to cart function ^

                    model.addAttribute("invoice", inventoryDao.findAll());//This line is to be changed when you figure out invoice
                    model.addAttribute("title", "");
                    model.addAttribute("inventory", inventoryDao.findAll());
                    return "general_Store/Checkout/checkout";

    }





 }
