package org.launchcode.controllers;
// All from p1 unless specified
//https://www.youtube.com/watch?v=6GnUuh4qNrM
import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller //overlookced this
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao; //field //Autowired does thw work of creating a class ti implement categoryDao and such// tis wil need to be added to each class you want to access categoryDao

    //think this is a handler v
    @RequestMapping(value = "")
        public String index(Model model) {
        //model.addAttribute("categories", categoryDao.findAll());  //blocked for testing
        System.out.println("category -- ");
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", categoryDao.findAll());
        System.out.println("CategoryController --");
        return "category/index";


    }
    //think this is a handler ^

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String DisplayAddCategory(Model model) {
        System.out.println("category -- DisplayAddCategory ");
        //model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");
//        Category testcat= categoryDao.findOne(10);   //this was for a test
//        testcat.setName("test");
//        categoryDao.save(testcat);

        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String ProcessAddCategory(Model model, @ModelAttribute @Valid Category category, Errors errors) {
        System.out.println("category -- ProcessAddCategory ");


        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";
    }

}
