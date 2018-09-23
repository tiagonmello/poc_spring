package com.sap.controllers;

import com.sap.models.User;
import com.sap.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/homepage")
    public String homepage(HttpServletRequest request){
        if(request.isUserInRole("ROLE_OWNER")){
            return "redirect:/owner/homepage";
        }
        if(request.isUserInRole("ROLE_MEMBER")){
            return "redirect:/member/homepage";
        }
        return "homepage";
    }

    @RequestMapping(value = {"/login", "/"})
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/registrationForm")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registrationForm";
    }

    @RequestMapping(value = "/register")
    public String processRegistrationForm(Model model, User user) {
        try{
            userService.createOwner(user);
            model.addAttribute("registrationMessage","Registration Successful!");
        }catch (IllegalArgumentException e){
            model.addAttribute("registrationMessage",e.getMessage());
        }
        return "registrationForm";
    }

    @RequestMapping(value = "/error")
    public String error(Model model){
        model.addAttribute("errorMessage","Invalid username or password");
        return "login";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
