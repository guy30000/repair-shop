package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.EmployeeDao;
import org.launchcode.repair_shop.models.data.PeopleDao;
import org.launchcode.repair_shop.models.data.TicketDao;
import org.launchcode.repair_shop.models.forms.Employee;
import org.launchcode.repair_shop.models.forms.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping(value = "repair_shop/ticket")
public class TicketController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    private EmployeeDao employeeDao;

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
////////////////New ticket
    @RequestMapping(value = "new/{cxId}", method = RequestMethod.GET)
    public String displayNewTicketForm (Model model, @PathVariable int cxId){
        model.addAttribute("title", "New Ticket");
        model.addAttribute("buttonName", "Create Ticket");
        model.addAttribute("cx", peopleDao.findOne(cxId));
        model.addAttribute("agent", employeeDao.findAll());  //added this to get employee info that I may need
        model.addAttribute(new Ticket());
        return "repair_shop/ticket/new";
    }

    @RequestMapping(value = "new/{cxId}", method = RequestMethod.POST)  //-create ticket
    public String processNewTicketForm (Model model, @ModelAttribute @Valid Ticket ticket, Errors errors, @PathVariable int cxId,
                                        @ModelAttribute Employee employee, @RequestParam(required = false) int agentId,
                                        @RequestParam(required = false) String agentPin){
        Employee agent;
        try {

             agent = employeeDao.findOne(agentId);
            if (agent == null){
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
             agent = employeeDao.findOne(1);
             agentPin = "failedpin999998888877777";  //Resets pin input to be outside of pin possibilites.
        }
        if (errors.hasErrors() || !agent.getPin().equals(agentPin)) {
            if (!agent.getPin().equals(agentPin)){
                model.addAttribute("agentError", "Agent ID or PIN code in valid");
            }
            model.addAttribute("title", "New Ticket");
            model.addAttribute("buttonName", "Create Ticket");
            model.addAttribute("cx", peopleDao.findOne(cxId));
            return "repair_shop/ticket/new";
        }
        Date date = new Date();
        String timestamp = dateFormat.format(date);
        ticket.setTime(timestamp);
        ticket.setOpen(true);
        ticket.setUpdated("No Updates");
        ticket.getItemNotes().add(" +Ticket created+ "  + timestamp + "      Created By: " + agent.getAgentLastName() + ", " + agent.getAgentFirstName() + " ID: " + agent.getId());
        ticket.setTime(timestamp);

        ticketDao.save(ticket);

        return "redirect:/repair_shop/ticket/view/" + ticket.getId();
    }
////////////////View ticket
    @RequestMapping(value = "view", method = RequestMethod.GET)   //view all tickets
    public String viewTickets (Model model){
        ArrayList<Ticket> openTickets = new ArrayList<>();
        ArrayList<Ticket> closedTickets = new ArrayList<>();
        model.addAttribute("title", "All Tickets");
        for ( Ticket ticket: ticketDao.findAll() ) {
            if (ticket.isOpen() == true) {
                openTickets.add(ticket);
            }
            if (ticket.isOpen() != true) {
                closedTickets.add(ticket);
            }
        }
        model.addAttribute("tickets", openTickets);
        model.addAttribute("closedTickets", closedTickets);
        return "repair_shop/ticket/view";
    }

    @RequestMapping(value = "view", method = RequestMethod.POST)  //view all tickets
    public String viewTickets (Model model, @RequestParam(required = false) String ticketsearch, @RequestParam(required = false) String tickOpen) {
        model.addAttribute("title", "View Customers");
        ArrayList<Ticket> openTickets = new ArrayList<>();
        ArrayList<Ticket> closedTickets = new ArrayList<>();
        if (ticketsearch != null) {
            ArrayList<Ticket> tickets = new ArrayList<>();
            double tickSrchAsId = -1;
            try {
            tickSrchAsId = Integer.parseInt(ticketsearch); }
            catch (Exception whateva) {
            }
            for (Ticket ticket : ticketDao.findAll()){
                if (ticket.getCustomer().getFirstName().toLowerCase().contains(ticketsearch.toLowerCase())
                        || (ticket.getCustomer().getLastName().toLowerCase().contains(ticketsearch.toLowerCase()))
                        || (ticket.getCustomer().getPhoneNumber().contains(ticketsearch))
                        || (tickSrchAsId == (ticket.getId()))  //still working on getting lookup id working Trying to make it less than an if statement
                        ) {
                        if (ticket.isOpen() == true) {
                            openTickets.add(ticket);
                        }
                        if (ticket.isOpen() != true) {
                            closedTickets.add(ticket);
                        }


               }
            }
            if (!"false".equals(tickOpen)) { //passes open tickets
                model.addAttribute("tickets", openTickets);
            }
            if (!"true".equals(tickOpen)) { //passes closed tickets
                model.addAttribute("closedTickets", closedTickets);
            }

        }
        return "repair_shop/ticket/view";
    }

    @RequestMapping(value = "view/{ticketId}", method = RequestMethod.GET) //-display/add note to single ticket
    public String displaySingleTicket (Model model, @PathVariable int ticketId){
        Ticket order = ticketDao.findOne(ticketId);
        model.addAttribute("title", "Ticket: #" + order.getId() + " - " + order.getCustomer().getLastName() + ", " + order.getCustomer().getFirstName() + " - " + order.getItemName());
        model.addAttribute("ticket", order);
        return "repair_shop/ticket/order";
    }

    @RequestMapping(value = "view/{ticketId}", method = RequestMethod.POST)  //-display/process ticket note
    public String processSingleTicketNewNote (Model model, @PathVariable int ticketId, @RequestParam(required = false) String newNote,
                                              @RequestParam(required = false) String closeticket, @RequestParam(required = false) String contactCx,
                                              @RequestParam(required = false) String deleteTicket, @RequestParam Integer agentId, @RequestParam String agentPin){
        Employee agent;  //verify agent & pin//
        try {
            agent = employeeDao.findOne(agentId);
            if (agent == null || agentId == null){
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            agent = employeeDao.findOne(1);
            agentPin = "failedpin999998888877777";  //Resets pin input to be outside of pin possibilities.
        }
        if (!agent.getPin().equals(agentPin)) {
            if (!agent.getPin().equals(agentPin)){
                model.addAttribute("agentError", "Agent ID or PIN code in valid");
            }
            Ticket order = ticketDao.findOne(ticketId);
            model.addAttribute("title", "Ticket: #" + order.getId() + " - " + order.getCustomer().getLastName() + ", " + order.getCustomer().getLastName() + " - " + order.getItemName());
            model.addAttribute("ticket", order);
            return "repair_shop/ticket/order";
        }               //end verify agent & pin//

        Ticket order = ticketDao.findOne(ticketId);
        model.addAttribute("title", "Ticket: #" + order.getId() + " - " + order.getCustomer().getLastName() + ", " + order.getCustomer().getLastName() + " - " + order.getItemName());
        model.addAttribute("ticket", order);
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //TimeDateStamp timestamp = new TimeDateStamp();

        Date date = new Date();
        String timestamp = dateFormat.format(date);
        String signature = (timestamp + "      Updated By: " + agent.getAgentLastName() + ", " + agent.getAgentFirstName() + " ID: " + agent.getId());
        if (newNote != null && newNote.length() > 0) {
            order.getItemNotes().add("NOTE: " + newNote + " :: " + "\n" + signature);
            order.setUpdated(timestamp);
        }
        if (closeticket != null) {
            order.setOpen(false);
            order.getItemNotes().add(" +Ticket Complete+ :: " + "\n" + signature);
            order.setUpdated(timestamp);
        }
        if (contactCx != null) {
            order.getItemNotes().add(" +Contacted Customer+ :: " + "\n" + signature);
            order.setUpdated(timestamp);
        }
        ticketDao.save(order);
        if (deleteTicket != null) {       ///Delet ticket
            ticketDao.delete(ticketId);
            return "redirect:/repair_shop/ticket";
        }   ///Delete ticket to this test.

        return "repair_shop/ticket/order";
    }

    @RequestMapping(value = "view/cx/{cxId}", method = RequestMethod.GET) //vew all tickets of a cx
    public String viewCxTickets (Model model, @PathVariable int cxId){
        model.addAttribute("title", "Tickets: " + peopleDao.findOne(cxId).getLastName() + ", " + peopleDao.findOne(cxId).getFirstName() );
        ArrayList<Ticket> openTickets = new ArrayList<>();
        ArrayList<Ticket> closedTickets = new ArrayList<>();
        for (Ticket ticket : ticketDao.findAll()){
            if ( ticket.getCustomer().getId() == cxId ) {
                if (ticket.isOpen() == true) {
                    openTickets.add(ticket);
                }
                if (ticket.isOpen() != true) {
                    closedTickets.add(ticket);
                }
            }
            model.addAttribute("tickets", openTickets);
            model.addAttribute("closedTickets", closedTickets);
            model.addAttribute("void", "void");
        }
        return "/repair_shop/ticket/view";
    }

}
