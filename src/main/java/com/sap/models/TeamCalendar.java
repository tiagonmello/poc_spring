package com.sap.models;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TEAM_CALENDAR")
public class TeamCalendar {

    public TeamCalendar(Integer dayLimit, Integer lateLimit, Integer specialDayLimit, Integer specialLateLimit, Team team) {
        this.dayLimit = dayLimit;
        this.lateLimit = lateLimit;
        this.specialDayLimit = specialDayLimit;
        this.specialLateLimit = specialLateLimit;
        this.team = team;
    }

    public TeamCalendar(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_CALENDAR_ID")
    private Integer id;

    @OneToMany(mappedBy = "calendar", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Day> days;

    private Integer dayLimit;

    private Integer lateLimit;

    private Integer specialDayLimit;

    private Integer specialLateLimit;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Integer dayLimit) {
        this.dayLimit = dayLimit;
    }

    public Integer getLateLimit() {
        return lateLimit;
    }

    public void setLateLimit(Integer lateLimit) {
        this.lateLimit = lateLimit;
    }

    public Integer getSpecialDayLimit() {
        return specialDayLimit;
    }

    public void setSpecialDayLimit(Integer specialDayLimit) {
        this.specialDayLimit = specialDayLimit;
    }

    public Integer getSpecialLateLimit() {
        return specialLateLimit;
    }

    public void setSpecialLateLimit(Integer specialLateLimit) {
        this.specialLateLimit = specialLateLimit;
    }

    public Date getStartDate() {
        this.days.sort(Comparator.comparing(Day::getDayDate));
        return days.get(0).getDayDate();
    }

    public Date getEndDate() {
        this.days.sort(Comparator.comparing(Day::getDayDate));
        return days.get(days.size() - 1).getDayDate();
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
