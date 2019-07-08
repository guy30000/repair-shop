package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.PeopleDao;
import org.launchcode.repair_shop.models.data.TicketDao;
import org.launchcode.repair_shop.models.forms.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;


import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "repair_shop/ticket")
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private PeopleDao peopleDao;

    @RequestMapping(value = "")
    public String index (Model model){
        ArrayList<Ticket> tickets = new ArrayList<>();
        model.addAttribute("title", "Tickets");
        for ( Ticket ticket: ticketDao.findAll() )
            if (ticket.isOpen() == true) {
                tickets.add(ticket);
            }
        model.addAttribute("tickets", tickets);
        return "repair_shop/ticket/index";
    }

    @RequestMapping(value = "new/{cxId}", method = RequestMethod.GET)
    public String displayNewTicketForm (Model model, @PathVariable int cxId){
        model.addAttribute("title", "New Ticket");
        model.addAttribute("buttonName", "Create Ticket");
        model.addAttribute("cx", peopleDao.findOne(cxId));
        model.addAttribute(new Ticket());
        return "repair_shop/ticket/new";
    }


    @RequestMapping(value = "new/{cxId}", method = RequestMethod.POST)
    public String processNewTicketForm (Model model, @ModelAttribute @Valid Ticket ticket, Errors errors, @PathVariable int cxId){
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Ticket");
            model.addAttribute("buttonName", "Create Ticket");
            model.addAttribute("cx", peopleDao.findOne(cxId));
            return "repair_shop/ticket/new";
        }
        ticket.setOpen(true);
        ticketDao.save(ticket);
        //Ticket order = ticketDao.findOne(cxId);
        //model.addAttribute("title", "Ticket: #" + order.getId() + " - " + order.getCustomer().getLastName() + ", " + order.getCustomer().getLastName() + " - " + order.getItemName());
        //model.addAttribute("ticket", order);
        return "repair_shop/ticket/order";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String viewTickets (Model model){
        ArrayList<Ticket> openTickets = new ArrayList<>();
        ArrayList<Ticket> closedTickets = new ArrayList<>();
        model.addAttribute("title", "All Tickets");
        for ( Ticket ticket: ticketDao.findAll() )
            if (ticket.isOpen() == true) {
                openTickets.add(ticket);
            }
        for ( Ticket ticket: ticketDao.findAll() )
            if (ticket.isOpen() != true) {
                closedTickets.add(ticket);
            }
        model.addAttribute("tickets", openTickets);
        model.addAttribute("closedTickets", closedTickets);
        return "repair_shop/ticket/view";
    }

    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String viewTickets (Model model, @RequestParam(required = false) String ticketsearch) {
        model.addAttribute("title", "View Customers");
        if (ticketsearch != null) {
            ArrayList<Ticket> tickets = new ArrayList<>();
            for (Ticket ticket : ticketDao.findAll()){
                if (ticket.getCustomer().getFirstName().toLowerCase().contains(ticketsearch.toLowerCase()) ||
                        (ticket.getCustomer().getLastName().toLowerCase().contains(ticketsearch.toLowerCase())) ||
                        (ticket.getCustomer().getPhoneNumber().contains(ticketsearch))
//                         || (ticket.getId() == ticketsearch)   // ticket numbner search not quote working
                        ) {
                    tickets.add(ticket);
                    model.addAttribute("tickets", tickets);
               }
            }

        }
        return "repair_shop/ticket/view";
    }

    @RequestMapping(value = "view/{ticketId}", method = RequestMethod.GET)
    public String displaySingleTicket (Model model, @PathVariable int ticketId){
        Ticket order = ticketDao.findOne(ticketId);
        model.addAttribute("title", "Ticket: #" + order.getId() + " - " + order.getCustomer().getLastName() + ", " + order.getCustomer().getLastName() + " - " + order.getItemName());
        model.addAttribute("ticket", order);
        return "repair_shop/ticket/order";
    }

    @RequestMapping(value = "view/{ticketId}", method = RequestMethod.POST)
    public String processSingleTicketNewNote (Model model, @PathVariable int ticketId, @RequestParam(required = false) String newNote, @RequestParam(required = false) String closeticket, @RequestParam(required = false) String contactCx){
        Ticket order = ticketDao.findOne(ticketId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        model.addAttribute("title", "Ticket: #" + order.getId() + " - " + order.getCustomer().getLastName() + ", " + order.getCustomer().getLastName() + " - " + order.getItemName());
        model.addAttribute("ticket", order);
        if (newNote != null && newNote.length() > 0) {
            order.getItemNotes().add(newNote + " +" + timestamp);
        }
        if (closeticket != null) {
            order.setOpen(false);
            order.getItemNotes().add("Ticket Complete +"  + timestamp);
        }
        if (contactCx != null) {
            order.getItemNotes().add("Contacted Customer +"  + timestamp);
        }
        ticketDao.save(order);
        return "repair_shop/ticket/order";
    }


}
