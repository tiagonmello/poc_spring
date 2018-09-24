package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.models.User;
import com.sap.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import java.util.List;

@Controller
public class OwnerController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/owner/homepage")
    public String ownerHomepage(Model model, User user){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();

        model.addAttribute("teamId",loggedUser.getTeam().getId());
        model.addAttribute("user",user);
        model.addAttribute("userList",userService.getUsersByTeam(loggedUser.getTeam()));
        return "ownerPage";
    }

    @RequestMapping(value = "/owner/addMember")
    public String ownerAddMember(Model model, User user){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User teamOwner = principal.getUser();

        try{
            userService.createMember(user, teamOwner);
            model.addAttribute("registrationMessage","Registration Successful!");
        }catch (IllegalArgumentException e){
            model.addAttribute("registrationMessage",e.getMessage());
        }
        return "redirect:/owner/homepage";
    }

    @RequestMapping(value = "/owner/deleteMember")
    public String ownerDeleteMember(User user){
        userService.deleteMember(user);
        return "redirect:/owner/homepage";
    }
}






