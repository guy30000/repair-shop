package org.launchcode.repair_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="home")
public class HomeControllerRS {
    public String index (Model model){
        model.addAttribute( "title", "Fix Tt Up");
        return "repair_shop/home";
    }
}
