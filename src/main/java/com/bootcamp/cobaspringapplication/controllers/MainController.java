/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Department;
import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import com.bootcamp.cobaspringapplication.services.impl.DepartmentRest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author arman
 */
@Controller
public class MainController implements WebMvcConfigurer {

    @Autowired
    IEmployeeService es;
    @Autowired
    DepartmentRest ds;
    String status1 = "", status2 = "", status3 = "";

    @RequestMapping("/")
    public String getAll(Model model, Employee employee) {
        model.addAttribute("status1", status1);
        model.addAttribute("status2", status2);
        model.addAttribute("status3", status3);
        status1 = "a";
        status2 = "a";
        status3 = "a";
        model.addAttribute("employees", es.getAll());
        return "getall";
    }

    @RequestMapping("/testing")
    public String test(Model model) {
        model.addAttribute("departments", ds.getAll());
        return "department";
    }

    @PostMapping("/asd")
    public String checkEmployee(Model model, @Valid Employee employee, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                status1 = "Oops!";
                status2 = "Save data gagal!";
                status3 = "error";
                return "redirect:/";
            }
            status1 = "Nice!";
            status2 = "Save data berhasil!";
            status3 = "success";
            es.save(employee);
        } catch (Exception e) {
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(Model model, String id) {
        try {
            es.delete(id);
            status1 = "Nice!";
            status2 = "Delete data berhasil!";
            status3 = "success";

        } catch (Exception e) {
            status1 = "Oops!";
            status2 = "Delete data gagal!";
            status3 = "error";
        }
        return "redirect:/";
    }
}
