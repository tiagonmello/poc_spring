package com.sap.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TEAM_CALENDAR")
public class UserCalendar extends TeamCalendar {

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "USER_USERNAME")
    private User user;

    @ElementCollection
    @CollectionTable(name ="LATE_SHIFT_DATES")
    private List<Date> lateShiftDates;

    @ElementCollection
    @CollectionTable(name ="DAY_SHIFT_DATES")
    private List<Date> dayShiftDates;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Date> getLateShiftDates() {
        return lateShiftDates;
    }

    public void setLateShiftDates(List<Date> lateShiftDates) {
        this.lateShiftDates = lateShiftDates;
    }

    public List<Date> getDayShiftDates() {
        return dayShiftDates;
    }

    public void setDayShiftDates(List<Date> dayShiftDates) {
        this.dayShiftDates = dayShiftDates;
    }
}
