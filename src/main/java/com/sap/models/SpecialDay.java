package com.sap.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SPECIAL_DAY")
public class SpecialDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPECIAL_DAY_ID")
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayDate;

    private SpecialType type;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "TEAM_CALENDAR_ID")
    private TeamCalendar calendar;

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
}
