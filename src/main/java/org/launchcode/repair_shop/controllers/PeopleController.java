package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.PeopleDao;
import org.launchcode.repair_shop.models.data.PeopleData;
import org.launchcode.repair_shop.models.data.TicketDao;
import org.launchcode.repair_shop.models.forms.NewPeople;
import org.launchcode.repair_shop.models.forms.Ticket;
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

    private TicketDao ticketDao;

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
        //model.addAttribute("newpeople", new NewPeople());  //remomber this = above
        return "repair_shop/people/newCX";
    }

    @RequestMapping(value = "newCX", method = RequestMethod.POST)
    public String processNewCXForm (Model model, @ModelAttribute @Valid NewPeople newpeople, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add New Customer");
            model.addAttribute("buttonName", "Add Customer");
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

    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String processViewCx (Model model, @RequestParam(required = false) String peoplesearch){
        model.addAttribute("title", "View Customers");
        if (peoplesearch != null ){         //search function
            ArrayList<NewPeople> searchResults = new ArrayList<>();
            for (NewPeople singlePerson : peopleDao.findAll()){
                if (singlePerson.getFirstName().toLowerCase().contains(peoplesearch.toLowerCase()) ||
                        singlePerson.getLastName().toLowerCase().contains(peoplesearch.toLowerCase()) ||
                        singlePerson.getEmail().toLowerCase().contains(peoplesearch.toLowerCase()) ||
                        singlePerson.getPhoneNumber().contains(peoplesearch)){
                    searchResults.add(singlePerson);
                    model.addAttribute("cxs", searchResults);
                }
            }
        }                               // End search function

//        if (peoplesearch != null ) {         //search function experiment
//            System.out.println("search function experiment             - ----    " + peoplesearch);
//            ArrayList<NewPeople> people = peopleData.searchPeople(peoplesearch);
//            people = peopleData.searchPeople(peoplesearch);
//            //model.addAttribute("cxs", people);
//            model.addAttribute("cxs", peopleDao.findAll());
//        }                                 // end search function experiment
        return "repair_shop/people/view";
    }
////Edit-view
    @RequestMapping(value = "view/{cxId}", method = RequestMethod.GET)
    public String displaySingeViewCx (Model model, @PathVariable int cxId){
        model.addAttribute("title", "View/Edit " + peopleDao.findOne(cxId).getLastName() +"," + peopleDao.findOne(cxId).getFirstName());
        model.addAttribute("newPeople", peopleDao.findOne(cxId));
        model.addAttribute("buttonName", "Update");
        return "repair_shop/people/newCX";
    }

    @RequestMapping(value = "view/{cxId}", method = RequestMethod.POST)
    public String processViewEdit(Model model, @ModelAttribute @Valid NewPeople newpeople, Errors errors, @PathVariable int cxId, @RequestParam(required = false) String peoplesearch) {
       if (errors.hasErrors()) {
           model.addAttribute("title", "View/Edit " + peopleDao.findOne(cxId).getLastName() +"," + peopleDao.findOne(cxId).getFirstName());
           //model.addAttribute("newPeople", peopleDao.findOne(cxId));
           model.addAttribute("buttonName", "Update");

           return "repair_shop/people/newCX";
       }
//Added the arraylist and for statements as editing customer info seemed to remove the customer id from tickets.
        //...but currently not fucntioning
       ArrayList<Ticket> reSetCustomerAssignedTicket = new ArrayList<>();
       for (Ticket ticket : ticketDao.findAll())
           if (cxId == ticket.getId()) {
               reSetCustomerAssignedTicket.add(ticket);
           }
        peopleDao.save(newpeople);
       for (Ticket ticket : reSetCustomerAssignedTicket){
           ticket.setCustomer(peopleDao.findOne(cxId));
       }
//        model.addAttribute("title", "View Customers");
//        model.addAttribute("cxs", peopleDao.findAll());
        model.addAttribute("newPeople", peopleDao.findOne(cxId));
        model.addAttribute("buttonName", "Update");
        return "redirect:repair_shop/people/view";
    }




}
