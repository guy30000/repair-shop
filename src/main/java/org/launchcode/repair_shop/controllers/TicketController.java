package org.launchcode.repair_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "repair_shop/ticket")
public class TicketController {

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "Tickets");
        return "repair_shop/tickets/index";
    }
}
