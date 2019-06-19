package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.PeopleDao;
import org.launchcode.repair_shop.models.forms.NewPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "repair_shop/people")
public class PeopleController {

    @Autowired
    private PeopleDao peopleDao;

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "People");
        return "repair_shop/People/index";
    }

    @RequestMapping(value = "newCX")
    public String displayNewCXForm (Model model) {
        model.addAttribute("title", "Add New Customer");
        model.addAttribute("buttonName", "Add Customer");
        model.addAttribute(new NewPeople());
        return "repair_shop/people/newCX";
    }

}
