package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.models.User;
import com.sap.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

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

    @RequestMapping(value = "/owner/editMember")
    public String ownerEditMember(Model model, User user){
        model.addAttribute("user",user);
        return "editMember";
    }

    @RequestMapping(value = "/owner/updateMember")
    public String ownerUpdateMember(User user, Model model){
        User updatedUser = userService.getUserByUsername(user.getUserName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        try{
            userService.updateMember(updatedUser);
            return "redirect:/owner/homepage";
        }catch (IllegalArgumentException e){
            model.addAttribute("updateMessage",e.getMessage());
            return "editMember";
        }
    }

    @RequestMapping(value = "/owner/deleteMember")
    public String ownerDeleteMember(User user){
        userService.deleteMember(user);
        return "redirect:/owner/homepage";
    }

}