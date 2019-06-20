package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.PeopleDao;
import org.launchcode.repair_shop.models.forms.NewPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    //                                                           ---------------------ADD
    @RequestMapping(value = "newCX", method = RequestMethod.GET)
    public String displayNewCXForm (Model model) {
        model.addAttribute("title", "Add New Customer");
        model.addAttribute("buttonName", "Add Customer");
        model.addAttribute(new NewPeople());
        return "repair_shop/people/newCX";
    }

    @RequestMapping(value = "newCX", method = RequestMethod.POST)
    public String processNewCXForm (Model model, @ModelAttribute @Valid NewPeople newpeople, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add New Customer");
            model.addAttribute("buttonName", "Add Customer");
            model.addAttribute(new NewPeople());
            return "repair_shop/people/newCX";
        }
        peopleDao.save(newpeople);
        return ("redirect:/repair_shop/people/view/" + newpeople.getId());
    }
    // End  ADD
    //                                                         --------- -----------VIEW
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String displayViewCx (Model model){
        model.addAttribute("title", "View Customers");
        model.addAttribute("cxs", peopleDao.findAll());
        return "repair_shop/people/view";
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String displaySingeViewCx (Model model, @PathVariable int id){
        model.addAttribute("title", "View Customers");
        model.addAttribute("cxs", peopleDao.findOne(id));
        System.out.println(id + "              ddddddddd");
        return "repair_shop/people/view";
    }
}
