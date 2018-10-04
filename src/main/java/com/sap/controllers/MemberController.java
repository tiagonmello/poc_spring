package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.models.Event;
import com.sap.models.EventDto;
import com.sap.models.TeamCalendar;
import com.sap.models.User;
import com.sap.service.EventService;
import com.sap.service.TeamCalendarService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MemberController {

    @Resource
    private TeamCalendarService teamCalendarService;

    @Resource
    private EventService eventService;

    @RequestMapping(value = "/member/homepage")
    public String memberHomepage(Model model){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();

        model.addAttribute("user",loggedUser);
        model.addAttribute("event",new Event());
        model.addAttribute("calendarList",teamCalendarService.getTeamCalendarList(loggedUser.getTeam()));
        model.addAttribute("dateList",teamCalendarService.getDateList(loggedUser.getTeam()));
        model.addAttribute("eventList",eventService.getEventsByUser(loggedUser.getUserName()));
        return "memberPage";
    }

    @RequestMapping(value = "/member/addEvent")
    public String memberAddEvent(EventDto event){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();

        eventService.createEvent(event, loggedUser);

        return "redirect:/member/homepage";
    }

}






