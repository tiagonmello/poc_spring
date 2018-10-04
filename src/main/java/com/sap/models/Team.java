package com.sap.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_USERNAME")
    private Owner owner;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> members;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TeamCalendar> calendars;

    public List<TeamCalendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<TeamCalendar> calendars) {
        this.calendars = calendars;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
