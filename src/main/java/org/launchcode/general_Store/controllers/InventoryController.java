package org.launchcode.general_Store.controllers;

import org.launchcode.general_Store.models.Inventory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "general_Store/Inventory")
public class InventoryController {

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
            System.out.println("InventoryController-Errors " );
            return "general_Store/Inventory/add";
        }


        System.out.println("adding tests d " + newInvItem.getDescription());
        System.out.println("adding tests sp " + newInvItem.getSalePrice());
        System.out.println("adding tests s " + newInvItem.getSku());
        System.out.println("adding tests pc " + newInvItem.getPurchaseCost());
        System.out.println("adding tests v " + newInvItem.getVendor());
        System.out.println("adding tests i " + newInvItem.getId());
        System.out.println("adding tests iS " + newInvItem.getInitialStock());
        return "general_Store/Inventory/index";
    }

}
