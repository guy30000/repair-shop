package org.launchcode.general_Store.controllers;

import org.launchcode.general_Store.models.Inventory;
import org.launchcode.general_Store.models.data.InventoryDao;
import org.launchcode.general_Store.models.forms.ReceiveInvForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "general_Store/Inventory")
public class InventoryController {

    @Autowired
    private InventoryDao inventoryDao;


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Inventory");
        System.out.println("InventoryController");
        return "general_Store/Inventory/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddInventory(Model model) {
        model.addAttribute("title", "Create inventory item");
        System.out.println("InventoryController-adddisp");
        model.addAttribute(new Inventory());
        return "general_Store/Inventory/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddInventory(@ModelAttribute @Valid Inventory newInvItem, Errors errors, Model model ) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create inventory item");
            model.addAttribute("inventory", newInvItem);
            return "general_Store/Inventory/add";
        }
        inventoryDao.save(newInvItem);
        return "general_Store/Inventory/index";
    }

    @RequestMapping(value = "receive", method = RequestMethod.GET)
    public String displayReceiveInv( Model model) {
        model.addAttribute("title", "");
        model.addAttribute("inventory", inventoryDao.findAll());
        System.out.println("inv");
        //model.addAttribute(new SearchForm());
        return "general_Store/Inventory/receive";
    }

    @RequestMapping(value = "receive", method = RequestMethod.POST)
    public String ProcessReceiveInv( Model model, @RequestParam(required=false) String keyword,
                @RequestParam(required=false) String search,
                @RequestParam(required=false) String addInv,
                                     @RequestParam(required=false) String itemId,
                                     @RequestParam(required=false) String quantity,
                @ModelAttribute ReceiveInvForm recForm) {
//        if (search != null && search.equals("Search Inventory")) {
//            System.out.println("Hello buttz 2 " + keyword +" - "+ search);
////            if (inventoryDao.findAll().equals(keyword))    {
////                System.out.println("Hello buttz 4 " + keyword +" - "+ search);
////            }
////            if (keyword.equals(inventoryDao.findAll())){
////                System.out.println("Hello buttz 4 " + keyword +" - "+ search);
////            }
//        }
        System.out.println("Hello buttz 5 " + recForm.getItemId() +" - "+ recForm.getQuantity() );
        System.out.println("Hello buttz 6 " + itemId +" - "+ quantity );


    //Receive Inventory
        if (recForm.getItemId().size() == recForm.getQuantity().size()) {
                for (int i = 0; i < recForm.getItemId().size(); i++) {
                if ( recForm.getQuantity().get(i) == ""){ System.out.println("Null skipping"); continue;
                } else {
                    int idOfCurrentItem = Integer.valueOf((String) recForm.getItemId().get(i));
                    int newQuantity = Integer.valueOf((String) recForm.getQuantity().get(i));
                    Inventory itemToBeUpdated = inventoryDao.findOne(idOfCurrentItem);
                    itemToBeUpdated.setStock(itemToBeUpdated.getStock() + newQuantity);
                    inventoryDao.save(itemToBeUpdated);
                    System.out.println("stock b " + idOfCurrentItem + " - " + newQuantity);
                    System.out.println("stock " + recForm.getQuantity().get(i) + " - " + recForm.getItemId().get(i));
                } } } else { System.out.println("ERROR: Invetroty list size is different from quantities list");  }
    //End Receive Inventory  ^


        System.out.println("Hello buttz 3 " + keyword +" - "+ search + " - " + addInv);
        model.addAttribute("title", "");
        model.addAttribute("inventory", inventoryDao.findAll());
        return "general_Store/Inventory/receive";
    }



}
