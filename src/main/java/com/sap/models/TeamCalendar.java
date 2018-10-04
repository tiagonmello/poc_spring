package com.sap.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TEAM_CALENDAR")
public class TeamCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_CALENDAR_ID")
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ElementCollection(fetch=FetchType.EAGER)
    private List<Date> holidays;

    @ElementCollection
    private List<Date> weekends;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public List<Date> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Date> holidays) {
        this.holidays = holidays;
    }

    public List<Date> getWeekends() {
        return weekends;
    }

    public void setWeekends(List<Date> weekends) {
        this.weekends = weekends;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
