package org.launchcode.repair_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="repair_shop")
public class HomeControllerRS {

    @RequestMapping(value = "home")
    public String index (Model model){
            return "repair_shop/home";
        }
    }

