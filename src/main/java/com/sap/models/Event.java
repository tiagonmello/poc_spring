package com.sap.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EVENT")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_USERNAME")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DAY_ID")
    private Day day;

    private Shift shift;

    private Boolean dayAvailability;

    public Boolean getDayAvailability() {
        return dayAvailability;
    }

    public void setDayAvailability(Boolean dayAvailability) {
        this.dayAvailability = dayAvailability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public Date getEventDate() {
        return this.day.getDayDate();
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}

