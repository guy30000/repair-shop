package org.launchcode.general_Store.controllers;

import org.launchcode.general_Store.models.Inventory;
import org.launchcode.general_Store.models.InventorySearch;
import org.launchcode.general_Store.models.data.InventoryDao;
import org.launchcode.general_Store.models.forms.ReceiveInvForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("buttonName", "Create New Item");
        System.out.println("InventoryController-adddisp");
        model.addAttribute(new Inventory());
        return "general_Store/Inventory/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddInventory(@ModelAttribute @Valid Inventory newInvItem, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create inventory item");
            model.addAttribute("buttonName", "Create New Item");
            model.addAttribute("inventory", newInvItem);
            return "general_Store/Inventory/add";
        }
        inventoryDao.save(newInvItem);
        return "general_Store/Inventory/index";
    }

    @RequestMapping(value = "receive", method = RequestMethod.GET)
    public String displayReceiveInv(Model model, @RequestParam(required = false) String sortBy) {
        model.addAttribute("title", "");
        model.addAttribute("inventory", inventoryDao.findAll());
        System.out.println("inv " + sortBy);
        //model.addAttribute(new SearchForm());
        return "general_Store/Inventory/receive";
    }

    @RequestMapping(value = "receive", method = RequestMethod.POST)
    public String ProcessReceiveInv(Model model, @RequestParam(required = false) String keyword, @RequestParam(required = false) String search, @RequestParam(required = false) String addInv, @RequestParam(required = false) String itemId, @RequestParam(required = false) String quantity, @ModelAttribute ReceiveInvForm recForm, @RequestParam(required = false) String sortBy) {
        /////////////////////////////////// Search FUnction
        if (search != null && search.equals("Search Inventory")) {
            ArrayList<Inventory> searchResults = new ArrayList<>();
            for ( Inventory singleItem : inventoryDao.findAll()) {
                if (singleItem.getName().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getVendor().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getSku().equalsIgnoreCase(keyword) ){
                    searchResults.add(singleItem);
                }
            }


            model.addAttribute("title", "Search: " + keyword);
            model.addAttribute("inventory", searchResults);
            return "general_Store/Inventory/receive";
        }
        /////////////////////////////////// End Search FUnction^
        /////////////////////////////////// Sort by...
//        System.out.println("Sort Inv by -  " + sortBy);
//        if (sortBy.equals("Name")) {
//            //https://stackoverflow.com/questions/5279570/sql-phpmyadmin-alter-table-order-by-id-ascending-make-permanent
//            String sqlSortQuery =
//                    "SELECT  * FROM inventoryDao.findAll() ORDER BY `ID` ASC";
//
//            //SELECT statement... [WHERE condition | GROUP BY `field_name(s)` HAVING condition] ORDER BY `field_name(s)` [ASC | DESC];
//
//            System.out.println("Sort Inv by - n --- " + sortBy);
//            model.addAttribute("title", "");
//            model.addAttribute("inventory", inventoryDao.findAll());
//            return "general_Store/Inventory/receive";
//        }
        /////////////////////////////////// end sort buy ^
        /////////////////////////////////// Receive Inventory
        if (addInv != null && addInv.equals("Add Inventory")) {
            if (recForm.getItemId().size() == recForm.getQuantity().size()) {
                System.out.println("sddinv test- " + addInv);
                for (int i = 0; i < recForm.getItemId().size(); i++) {
                    if (recForm.getQuantity().get(i) == "") {
                        System.out.println("Null skipping");
                        continue;
                    } else {
                        int idOfCurrentItem = Integer.valueOf((String) recForm.getItemId().get(i));
                        int newQuantity = Integer.valueOf((String) recForm.getQuantity().get(i));
                        //Inventory itemToBeUpdated = inventoryDao.findOne(idOfCurrentItem);
                        Inventory itemToBeUpdated = inventoryDao.findOne(idOfCurrentItem);
                        itemToBeUpdated.setStock(itemToBeUpdated.getStock() + newQuantity);
                        inventoryDao.save(itemToBeUpdated);
                        System.out.println("stock b " + idOfCurrentItem + " - " + newQuantity);
                        System.out.println("stock " + recForm.getQuantity().get(i) + " - " + recForm.getItemId().get(i));
                        }
                }
            } else {
                System.out.println("ERROR: Invetroty list size is different from quantities list");
            }
        }
        /////////////////////////////////// /End Receive Inventory  ^
        model.addAttribute("title", "");
        model.addAttribute("inventory", inventoryDao.findAll());
        return "general_Store/Inventory/receive";
    }

    //////////////////////////////View
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String displayViewInv(Model model) {
        model.addAttribute("title", "");
        model.addAttribute("inventory", inventoryDao.findAll());
        System.out.println("view  ");
        //model.addAttribute(new SearchForm());

        return "general_Store/Inventory/view";
    }

    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String processSearchViewInv(Model model, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String keyword, @RequestParam(required = false) String search, @RequestParam(required = false) String addInv, @RequestParam(required = false) String itemId, @RequestParam(required = false) String quantity, @ModelAttribute ReceiveInvForm recForm) {
        if (search != null && search.equals("Search Inventory")) {
            System.out.println("Hello buttz 2 " + keyword + " - " + search);
            ArrayList<Inventory> searchResults = new ArrayList<>();
            for ( Inventory singleItem : inventoryDao.findAll()) {
                if (singleItem.getName().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getVendor().toLowerCase().contains(keyword.toLowerCase()) || singleItem.getSku().equalsIgnoreCase(keyword) ){
                    searchResults.add(singleItem);
                    System.out.println("Hello buttz 7 " + keyword + " - "+ singleItem.getName() );
                }
            }

            new InventorySearch(keyword); //this line is not flagged but not what it needs to be
            model.addAttribute("title", "View: " + keyword);
            model.addAttribute("inventory", searchResults);
        }
        return "general_Store/Inventory/view";
    }

    @RequestMapping(value = "view/edit/{itemIdV}", method = RequestMethod.GET)
    public String displayViewEditForm(Model model, @PathVariable int itemIdV) {
        model.addAttribute("title", "Veiw/Edit item: " + inventoryDao.findOne(itemIdV).getName());
        model.addAttribute("inventory", inventoryDao.findOne(itemIdV));
        model.addAttribute("buttonName", "Save Changes");
        System.out.println("view edit " + inventoryDao.findOne(itemIdV).getName() );
        return "general_Store/Inventory/add";
    }

    @RequestMapping(value = "view/edit/{itemIdV}", method = RequestMethod.POST)
    public String processViewEditForm(@ModelAttribute @Valid Inventory editItem, Errors errors, Model model, @RequestParam(required = false) int itemId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Veiw/Edit item: ");
            model.addAttribute("inventory", editItem);
            model.addAttribute("buttonName", "Save Changes");
            return "general_Store/Inventory/add";
        }

            editItem.setId(itemId);
            System.out.println("Edit Save - " + editItem.getName() + " - " + itemId + "  -  " + editItem.getDescription() + " - " );
            inventoryDao.save(editItem);
            model.addAttribute("inventory", inventoryDao.findAll());
            return "general_Store/Inventory/view";
    }

}
