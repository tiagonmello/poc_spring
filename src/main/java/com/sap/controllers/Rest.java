package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.models.User;
import com.sap.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
public class Rest {

    @Resource
    private UserService userService;

    @RequestMapping("/owner/addMember")
    public boolean ownerCreateUser(User user){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();

        try{
            userService.createMember(user, loggedUser);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

}






