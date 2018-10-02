package com.sap.controllers;

import com.sap.MyUserPrincipal;
import com.sap.models.TeamCalendar;
import com.sap.models.User;
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

    @RequestMapping(value = "/member/homepage")
    public String memberHomepage(Model model){
        MyUserPrincipal principal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = principal.getUser();

        Calendar c = Calendar.getInstance();
        List<TeamCalendar> calendarList = teamCalendarService.getTeamCalendarList(loggedUser.getTeam());
        List<Date> dateList = new ArrayList<>();

        // For every calendar registered
        for(TeamCalendar calendar : calendarList){

        // Initialize loop date
            Date iterationDate = calendar.getStartDate();
            dateList.add(iterationDate);

        // While the loop date doesn't reach the end date
            while(calendar.getEndDate().compareTo(iterationDate) != 0){

        // Increments loop date
                c.setTime(iterationDate);
                c.add(Calendar.DATE,1);
                iterationDate = c.getTime();

        // Adds loop date to the date list
                dateList.add(iterationDate);
            }
        }

        model.addAttribute("user",loggedUser);
        model.addAttribute("calendarList",calendarList);
        model.addAttribute("dateList",dateList);
        return "memberPage";
    }

}






