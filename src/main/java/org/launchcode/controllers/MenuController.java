package org.launchcode.controllers;

// Many to many video. used this as it has much of the info
// https://www.youtube.com/watch?v=otuxIIbtznA p1
// https://www.youtube.com/watch?v=EeAMVMt2vMU p2 all time stamps after 8:00 are p2
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired //autowiredsd 3:30 p2
    CheeseDao cheeseDao;

    @Autowired
    MenuDao menuDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());  //4:00 p2
        System.out.println("Menu Controller -- index");
        return "Menu/index";
    }

    @RequestMapping (value = "add", method = RequestMethod.GET) //5:30 p2
    public String add(Model model) {
        System.out.println("MenuController -- DisplayAddCategory ");
        model.addAttribute(new Menu());
        model.addAttribute("title", "Create New Menu");

        return "menu/add";
    }

        @RequestMapping (value = "add", method = RequestMethod.POST) //6:55 p2
        public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
            System.out.println("MenuController -- ProcessAddCategory " + menu + " - " + menuDao.save(menu).getName());
            if (errors.hasErrors()) {
                model.addAttribute("title", "Create New Menu");
                return "menu/add";
            }
            menuDao.save(menu);
            return "redirect:view/" + menu.getId(); //8:00 p2 explains how it knows the id //hibrionte gives it a value when saved to database

    }

  //this is called a handler   /stuck on these things
    @RequestMapping (value = "view/{id}", method = RequestMethod.GET) //8:40
    public String viewMenu(Model model, @PathVariable int id ){
        System.out.println("MenuController -- viewMenu " + id );
        //menuDao menu =menuDao.find
        //viewMenu(id)
        model.addAttribute("title", menuDao.findOne(id).getName() + " Menu");
        model.addAttribute("menu", menuDao.findOne(id));
//        model.addAttribute("menuId", menuDao.findOne(id).getId()); //these 2 lines replaec teh one above to match the utube. But I'm pretty sure I templated it correcetly
//        model.addAttribute("cheeses", menuDao.findOne(id).getCheeses());

        return "menu/view"; //this was a struggle

    }

    @RequestMapping (value = "add-item/{id}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int id ){
        System.out.println("MenuController -- addItem display" + id );

        //would have never gotten this on my own //10:30
        Menu menu = menuDao.findOne(id);
        AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll(), menu); //creating instance of

        model.addAttribute("title", "Add Cheese to " + menuDao.findOne(id).getName() + " Menu");
        model.addAttribute("form", form);
        return "menu/add-item";

    }

    @RequestMapping (value = "add-item", method = RequestMethod.POST) //17:00
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }
    Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
    Menu theMenu = menuDao.findOne(form.getMenuId());
    theMenu.addItem(theCheese); //19:15 explaining how this doesnt change the database but the nexline does
    menuDao.save(theMenu);
        System.out.println("MenuCont -- adding " +theCheese.getName()+ " to mnu " + theMenu.getName());

    return "redirect:/menu/view/" + theMenu.getId();
    }
    ///This is my own project
    //Bonus mission, edit cheese
    @RequestMapping(value = "edit/{menuId}", method = RequestMethod.GET)
    public String displayEditMenuForm(Model model, @PathVariable int menuId){
        System.out.println("mnucntrl displayEditMenu1 " + menuDao.findOne(menuId).getName());
        model.addAttribute("title", "Edit Menu: " + menuDao.findOne(menuId).getName());

        model.addAttribute("menuedit", menuDao.findOne(menuId));
//        Cheese editingCheese = cheeseDao.findOne(cheeseIdV);
        return "menu/edit";
    }
    @RequestMapping(value = "edit/{menuId}", method = RequestMethod.POST)
    //public String processEditForm(int cheeseId, String name, String description, String type){
    public String processEditMenuForm(@PathVariable int menuId, @RequestParam int[] cheeseIds, Errors errors, Model model){
        Menu editingMenu = menuDao.findOne(menuId);
        System.out.println("mnucntrl processEditMenu2 "  + menuDao.findOne(menuId).getName());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Menu: " + menuDao.findOne(menuId).getName());
            model.addAttribute("menuedit", menuDao.findOne(menuId));
            return "cheese/edit";
        }

        for (int cheeseId : cheeseIds) {
            Cheese removingMenuCheese = cheeseDao.findOne(cheeseId);
            editingMenu.getCheeses().remove(removingMenuCheese);
            System.out.println("remove menu cheese  " + cheeseDao.findOne(cheeseId).getName());
        }
        menuDao.save(editingMenu);

        return "redirect:/menu"; //place holder
    }




}
