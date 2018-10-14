package com.sap.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="DAY")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAY_ID")
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayDate;

    private SpecialType type;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "TEAM_CALENDAR_ID")
    private TeamCalendar calendar;

    private Integer dayLimit;

    private Integer lateLimit;

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Event> events;

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

    public TeamCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(TeamCalendar calendar) {
        this.calendar = calendar;
    }

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }

    public SpecialType getType() {
        return type;
    }

    public void setType(SpecialType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
