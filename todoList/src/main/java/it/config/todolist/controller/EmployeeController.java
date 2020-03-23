package it.config.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.config.todolist.service.EmployeeServiceInterface;
import it.config.todolist.model.Employee;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired private EmployeeServiceInterface employeeServiceInterface;

    @GetMapping("/")
    public String employees(Model model){
        List<Employee>employees = employeeServiceInterface.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", "Employees");
        model.addAttribute("isAdd", true);
        return "view/employees";

    }

//    @PostMapping(value ="/save")
//    public String save(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes){
//        Employee dbEmployee = employeeServiceInterface.save(employee);
//        return null;
//    }

    @GetMapping("/test")
    public String test(Model model){
        return "view/test";
    }

    @PostMapping(value="/save")
    public String save (@ModelAttribute Employee employee, RedirectAttributes redirectAttributes, Model model) {
        Employee dbEmployee = employeeServiceInterface.save(employee);
        if(dbEmployee != null) {
            redirectAttributes.addFlashAttribute("successmessage", "Employee is saved successfully");
            return "redirect:/";
        }else {
            model.addAttribute("errormessage", "Employee is not save, Please try again");
            model.addAttribute("employee", employee);
            return "view/employees";
        }
    }

    @GetMapping(value = "/getEmployee/{id}")
    public String getEmployee(@PathVariable Long id, Model model){
        Employee employee = employeeServiceInterface.findByID(id);
        List<Employee>employees = employeeServiceInterface.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", employee);
        model.addAttribute("title", "Edit Employee");
        model.addAttribute("isAdd", false);
        return "view/employees";

    }

    @PostMapping(value="/update")
    public String update (@ModelAttribute Employee employee, RedirectAttributes redirectAttributes, Model model) {
        Employee dbEmployee = employeeServiceInterface.update(employee);
        if(dbEmployee != null) {
            redirectAttributes.addFlashAttribute("successmessage", "Employee is updated successfully");
            return "redirect:/";
        }else {
            model.addAttribute("errormessage", "Employee is not updated, Please try again");
            model.addAttribute("employee", employee);
            return "view/employees";
        }
    }
    @GetMapping(value ="/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeServiceInterface.delete(id);
        redirectAttributes.addFlashAttribute("successmessage", "Employee is Deleted successfully");

        return "redirect:/";
    }
}