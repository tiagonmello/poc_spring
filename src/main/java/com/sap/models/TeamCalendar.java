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

    private Integer dayLimit;

    private Integer lateLimit;

    private Integer specialDayLimit;

    private Integer specialLateLimit;

    @OneToMany(mappedBy = "calendar", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SpecialDay> specialDays;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public List<SpecialDay> getSpecialDays() {
        return specialDays;
    }

    public void setSpecialDays(List<SpecialDay> specialDays) {
        this.specialDays = specialDays;
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

}
