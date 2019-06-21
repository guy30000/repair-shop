package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.PeopleDao;
import org.launchcode.repair_shop.models.data.PeopleData;
import org.launchcode.repair_shop.models.forms.NewPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "repair_shop/people")
public class PeopleController {
    private PeopleData peopleData;

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
    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String displayViewCx (Model model, @RequestParam(required = false) String peoplesearch){
        model.addAttribute("title", "View Customers");
        model.addAttribute("cxs", peopleDao.findAll());
//        if (peoplesearch != null ){         //search function
//            ArrayList<NewPeople> searchResults = new ArrayList<>();
//            for (NewPeople singlePerson : peopleDao.findAll()){
//                if (singlePerson.getFirstName().toLowerCase().contains(peoplesearch.toLowerCase()) ||
//                        singlePerson.getLastName().toLowerCase().contains(peoplesearch.toLowerCase()) ||
//                        singlePerson.getEmail().toLowerCase().contains(peoplesearch.toLowerCase()) ||
//                        singlePerson.getPhoneNumber().contains(peoplesearch)){
//                    searchResults.add(singlePerson);
//                    model.addAttribute("cxs", searchResults);
//                }
//            }
//        }                               // End search function

//        if (peoplesearch != null ) {         //search function experiment
//            ArrayList<NewPeople> people;
//            people = peopleData.searchPeople(peoplesearch);
//            model.addAttribute("cxs", people);
//        }                                 // end search function experiment
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
