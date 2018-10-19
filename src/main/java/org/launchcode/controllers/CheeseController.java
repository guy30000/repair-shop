package org.launchcode.controllers;
// cross reference
// E:\_SSD Libraries\Google Drive\Launch Code\Unit_3\CHeese versins\cheese-mvc-video-one2many-start
// E:\_SSD Libraries\Google Drive\Launch Code\Unit_3\cheese-mvc-persistent-master

import org.launchcode.models.Cheese;

import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    CategoryDao categoryDao;  //9:50

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll()); //12:00 (after template editing)
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll()); //14:00
            return "cheese/add";
        }
        newCheese.setCategory(categoryDao.findOne(categoryId));
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam (required=false) int[] cheeseIds) {
        //added the (required=false) as part of the info below
//updated on my own projuect added iff statement so hitting remove with no selection wouldnt break it
        if (cheeseIds != null) {
        for (int cheeseId : cheeseIds) {
            System.out.println("remove cheese  " + cheeseIds);
            cheeseDao.delete(cheeseId);
        }}

        return "redirect:";
    }


    //Bonus mission, edit cheese
    @RequestMapping(value = "edit/{cheeseIdV}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseIdV){

        model.addAttribute("title", "Edit Cheese: " + cheeseDao.findOne(cheeseIdV).getName());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("cheese", cheeseDao.findOne(cheeseIdV));
        Cheese editingCheese = cheeseDao.findOne(cheeseIdV);
        System.out.println("Chezcntrlr edit/id " + editingCheese.getName() + " - " + editingCheese.getId());
        return "cheese/edit";
    }
    @RequestMapping(value = "edit/{cheeseIdV}", method = RequestMethod.POST)
    //public String processEditForm(int cheeseId, String name, String description, String type){
    public String processEditForm(@ModelAttribute  @Valid Cheese editCheese, Errors errors, @RequestParam int categoryId, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll()); //14:00
            return "cheese/edit";
        }

        System.out.println("Chezcntrlr edit-presave " + editCheese.getName() + " - " + editCheese.getId());
        editCheese.setCategory(categoryDao.findOne(categoryId));
        cheeseDao.save(editCheese);
        System.out.println("Chezcntrlr edit-process " + editCheese.getName() + " - " + editCheese.getId());

        return "redirect:/cheese"; //place holder
    }

}
