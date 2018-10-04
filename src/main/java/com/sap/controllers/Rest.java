package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.models.DayDto;
import com.sap.models.Team;
import com.sap.models.TeamCalendar;
import com.sap.models.User;
import com.sap.service.SpecialDayService;
import com.sap.service.TeamCalendarService;
import com.sap.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
public class Rest {

    @Resource
    private UserService userService;

    @Resource
    private TeamCalendarService teamCalendarService;

    @Resource
    private SpecialDayService specialDayService;

    @RequestMapping("/owner/addMember")
    public boolean ownerCreateUser(User user){
        // Retrieves logged user
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();

        // Returns true if succeeded
        try{
            userService.createMember(user, loggedUser);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    @RequestMapping("/owner/addCalendar")
    public boolean ownerCreateCalendar(TeamCalendar teamCalendar){
        // Retrieves logged user team
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Team loggedUserTeam = principal.getUser().getTeam();

        try{
            teamCalendarService.createTeamCalendar(teamCalendar, loggedUserTeam);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    @RequestMapping("/owner/addDay")
    public boolean ownerCreateDay(DayDto day){
        // Retrieves logged user team
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Team loggedUserTeam = principal.getUser().getTeam();

        try{
            specialDayService.addSpecialDay(day, loggedUserTeam);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}






