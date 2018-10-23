package org.launchcode.general_Store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "general_Store")
public class HomeController {

    @RequestMapping(value = "home")
    public String index(Model model) {
        model.addAttribute("title", "Bill's General Store");
        System.out.println("HomeController");
        return "general_Store/home";
    }

}
