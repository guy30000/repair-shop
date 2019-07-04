package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.TicketDao;
import org.launchcode.repair_shop.models.forms.Ticket;
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
@RequestMapping(value = "repair_shop/ticket")
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "Tickets");
        return "repair_shop/ticket/index";
    }

    @RequestMapping(value = "new/{cxID}", method = RequestMethod.GET)
    public String displayNewTicketForm (Model model, @PathVariable int cxId){
        model.addAttribute("title", "Tickets");
        model.addAttribute("buttonName", "Create Ticket");
        model.addAttribute(new Ticket());
        return "repair_shop/ticket/new";
    }


    @RequestMapping(value = "new/{cxID}", method = RequestMethod.POST)
    public String processNewTicketForm (Model model, @ModelAttribute @Valid Ticket ticket, Errors errors, @PathVariable int cxId){

        ticketDao.save(ticket);
        return "repair_shop/ticket";
    }
}
