package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.dtos.DayDto;
import com.sap.dtos.EventDto;
import com.sap.dtos.TeamCalendarDto;
import com.sap.models.Day;
import com.sap.models.ShiftNote;
import com.sap.models.TextNote;
import com.sap.models.User;
import com.sap.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class OwnerController {

    @Resource
    private UserService userService;

    @Resource
    private TeamCalendarService teamCalendarService;

    @Resource
    private EventService eventService;

    @Resource
    private DayService dayService;

    @Resource
    private NotificationService notificationService;

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
        model.addAttribute("teamCalendarDto", new TeamCalendarDto());
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
        model.addAttribute("eventList",eventService.getEventsByUser(username));
        return "showUserCalendar";
    }

    @RequestMapping(value = "/owner/teamCalendar/{calendarId}")
    public String showUserCalendar(@PathVariable Integer calendarId, Model model){

        model.addAttribute("day", new Day());
        model.addAttribute("teamCalendar",teamCalendarService.getCalendarById(calendarId));
        return "editTeamCalendar";
    }

    @RequestMapping(value = "/owner/editDay")
    public String editDay(Day day, HttpServletRequest request){
        dayService.updateDayLimits(day.getId(), day.getDayLimit(), day.getLateLimit());

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @RequestMapping(value = "/owner/notifications")
    public String manageNotifications(Model model){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("textNotifications", notificationService.getTextNotificationsByTeam(principal.getUser().getTeam()));
        model.addAttribute("shiftNotifications", notificationService.getShiftNotificationsByTeam(principal.getUser().getTeam()));
        model.addAttribute("team", principal.getUser().getTeam());
        model.addAttribute("textNote", new TextNote());
        model.addAttribute("shiftNote", new EventDto());
        return "notificationManagement";
    }

    @RequestMapping(value = "/owner/addTextNote")
    public String addTextNote(TextNote notification){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notification.setTeam(principal.getUser().getTeam());

        notificationService.createTextNote(notification);

        return "redirect:/owner/notifications";
    }

    @RequestMapping(value = "/owner/addShiftNote")
    public String addShiftNote(EventDto notification){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try{
            notificationService.createShiftNote(notification, principal.getUser().getTeam());
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }

        return "redirect:/owner/notifications";
    }

    @RequestMapping(value = "/owner/deleteNotification")
    public String deleteNotification(ShiftNote notification){

        notificationService.deleteNotification(notification);

        return "redirect:/owner/notifications";
    }

    @RequestMapping(value = "/owner/calendarOverview")
    public String calendarOverview(Model model){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("eventList", eventService.getEventsByTeam(principal.getUser().getTeam()));
        return "overview";
    }
}