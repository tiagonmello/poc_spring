package com.sap.models;

import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;

    private Shift shift;

    private boolean dayAvailability;

    public boolean isDayAvailability() {
        return dayAvailability;
    }

    public void setDayAvailability(boolean dayAvailability) {
        this.dayAvailability = dayAvailability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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

