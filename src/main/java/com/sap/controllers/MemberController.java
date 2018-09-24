package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @RequestMapping(value = "/member/homepage")
    public String ownerHomepage(Model model){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();
        model.addAttribute("user",loggedUser);
        return "memberPage";
    }

}






