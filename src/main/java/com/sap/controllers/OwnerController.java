package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.dtos.DayDto;
import com.sap.models.TeamCalendar;
import com.sap.models.User;
import com.sap.service.EventService;
import com.sap.service.TeamCalendarService;
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

    @Resource
    private TeamCalendarService teamCalendarService;

    @Resource
    private EventService eventService;

    @RequestMapping(value = "/owner/homepage")
    public String ownerHomepage(Model model, User user){
        // Retrieves logged user
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();

        // Add data that will be displayed on the web page
        model.addAttribute("teamId",loggedUser.getTeam().getId());
        model.addAttribute("userList",userService.getUsersByTeam(loggedUser.getTeam()));
        model.addAttribute("calendarList",teamCalendarService.getTeamCalendarList(loggedUser.getTeam()));
        model.addAttribute("user",user);
        model.addAttribute("dayDto", new DayDto());
        model.addAttribute("teamCalendar", new TeamCalendar());
        return "ownerPage";
    }

    @RequestMapping(value = "/owner/editMember")
    public String ownerEditMember(Model model, User user){
        model.addAttribute("user",user);
        return "editMember";
    }

    @RequestMapping(value = "/owner/updateMember")
    public String ownerUpdateMember(User user, Model model){
        // Retrieves the user that will be updated by the username
        User updatedUser = userService.getUserByUsername(user.getUserName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        // Redirects back to homepage after updating only if succeeded
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

    @RequestMapping(value = "/owner/calendar/{username}")
    public String showUserCalendar(@PathVariable String username, Model model){
        User user = userService.getUserByUsername(username);

        model.addAttribute("user",user);
        model.addAttribute("dateList",teamCalendarService.getDateList(user.getTeam()));
        model.addAttribute("eventList",eventService.getEventsByUser(username));
        return "showUserCalendar";
    }
}