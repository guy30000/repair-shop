package org.launchcode.repair_shop.controllers;

import org.launchcode.repair_shop.models.data.EmployeeDao;
import org.launchcode.repair_shop.models.forms.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

///////////////////Create new
    @RequestMapping(value = "ect", method = RequestMethod.GET) //Create employee (view)
    private String displayECT (Model model) {
        model.addAttribute("title", "Employee Creation Tool");
        model.addAttribute(new Employee());
        model.addAttribute("buttonName", "Create new employee");
        return "repair_shop/staff/ect";
    }
    @RequestMapping(value = "ect", method = RequestMethod.POST) //Create employee (process)
    private String processECT (Model model, @Valid Employee employee, Errors errors, @RequestParam String verifyPin) {

        if (errors.hasErrors() || !verifyPin.equals(employee.getPin())){
            if (!verifyPin.equals(employee.getPin())) {
                System.out.println("Passwords no match" + verifyPin +"-VpwsP-"+ employee.getPin() );
                model.addAttribute("error", "PINs do not match");
            }
            model.addAttribute("title", "Employee Creation Tool");
            model.addAttribute("buttonName", "Create new employee");
            return "repair_shop/staff/ect";
        }
        employeeDao.save(employee);
        return "redirect:/repair_shop/staff/view/" + employee.getId();
    }

///////////////////view edit
    @RequestMapping(value = "view", method = RequestMethod.GET)
    private String displayViewEmployee (Model model){
        model.addAttribute("title", "View Employees");
        ArrayList<Employee> activeEmp = new ArrayList<>();
        ArrayList<Employee> inActiveEmp = new ArrayList<>();
        for (Employee singleEmp : employeeDao.findAll()) {
            if (singleEmp.isActive() == true){
                activeEmp.add(singleEmp);
            } else { inActiveEmp.add(singleEmp);
            }
        }
        model.addAttribute("staff", activeEmp);
        model.addAttribute("inactiveStaff", inActiveEmp);
        return"repair_shop/staff/view";
}
    @RequestMapping(value = "view/{agentId}", method = RequestMethod.GET)
    private String displayViewEmployeeById (Model model, @PathVariable int agentId){
        model.addAttribute("title", "View/Edit " + employeeDao.findOne(agentId).getAgentLastName() + ", " + employeeDao.findOne(agentId).getAgentFirstName());
        model.addAttribute("employee", employeeDao.findOne(agentId));
        model.addAttribute("buttonName", "Save Changes");
        return "repair_shop/staff/ect";
    }
    @RequestMapping(value = "view/{agentId}", method = RequestMethod.POST)
    private String processViewEmployeeById (Model model, @PathVariable int agentId, @Valid @ModelAttribute Employee employee, Errors errors, @RequestParam String verifyPin){
        if (errors.hasErrors() || !verifyPin.equals(employee.getPin())){
            if (!verifyPin.equals(employee.getPin())) {
                System.out.println("Passwords no match" + verifyPin +"-VpwsP-"+ employee.getPin() );
                model.addAttribute("error", "PINs do not match");
                System.out.println("  PIns issue ");
            }
            model.addAttribute("employee", employeeDao.findOne(agentId));
            model.addAttribute("title", "View " + employeeDao.findOne(agentId).getAgentLastName() + ", " + employeeDao.findOne(agentId).getAgentFirstName());
            model.addAttribute("buttonName", "Save Changes");
            return "repair_shop/staff/ect";
        }
        employeeDao.save(employee);
        return "redirect:/repair_shop/staff/view";
    }
}
