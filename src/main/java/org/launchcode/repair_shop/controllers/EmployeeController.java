package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.EmployeeDao;
import org.launchcode.repair_shop.models.forms.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "repair_shop/staff")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "")
    private String index (Model model){
        model.addAttribute("title", "Staff Tools");
        return "repair_shop/staff/index";
    }
///////////////////Creat new
    @RequestMapping(value = "ect", method = RequestMethod.GET)
    private String displayECT (Model model) {
        model.addAttribute("title", "Employee Creation Tool");
        model.addAttribute(new Employee());
        model.addAttribute("buttonName", "Create new employee");
        return "repair_shop/staff/ect";
    }

    @RequestMapping(value = "ect", method = RequestMethod.POST)
    private String processECT (Model model, @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Employee Creation Tool");
            model.addAttribute("buttonName", "Create new employee");
            return "repair_shop/staff/ect";
        }
        employee.setActive(true);
        employeeDao.save(employee);
        return "repair_shop/staff/index";
    }
///////////////////view edit
    @RequestMapping(value = "view", method = RequestMethod.GET)
    private String displayViewEmployee (Model model){
        model.addAttribute("title", "View Employees");
        ArrayList<Employee> activeEmp = new ArrayList<>();
        for (Employee singleEmp : employeeDao.findAll()) {
            if (singleEmp.isActive() == true){
                activeEmp.add(singleEmp);
            }
        }
        model.addAttribute("staff", activeEmp);
        return"repair_shop/staff/view";
}



//    Emplaye creation page
//      Emplayee edit
//    emplayee time clock
//    Emplayy pin to see who is had done what on tickets
    //Empleyee time card reports


}
