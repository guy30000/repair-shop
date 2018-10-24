package org.launchcode.general_Store.controllers;

import org.launchcode.general_Store.models.Inventory;
import org.launchcode.general_Store.models.data.InventoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @RequestMapping(value = "receive", method = RequestMethod.POST)
    public String displayReceiveInv( Model model) {


        return "general_Store/Inventory/receive";
    }

}
