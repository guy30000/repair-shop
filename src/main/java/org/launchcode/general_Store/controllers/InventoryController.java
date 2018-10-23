package org.launchcode.general_Store.controllers;

import org.launchcode.general_Store.models.Inventory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "add")
    public String displayAddInventory(Model model) {
        model.addAttribute("title", "Create inventory item");
        System.out.println("InventoryController-adddisp");
        model.addAttribute(new Inventory());
        return "general_Store/Inventory/add";
    }

//    @RequestMapping(value = "add")
//    public String processAddInventory(Model model, Errors errors, @ModelAttribute @Valid Inventory newInvItem) {
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Create inventory item");
//            return "general_Store/Inventory/add";
//        }
//
//        model.addAttribute("title", "Create inventory item");
//        System.out.println("InventoryController-addpro");
//        return "general_Store/Inventory";
//    }

}
