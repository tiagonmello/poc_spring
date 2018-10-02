package com.sap.models;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User {

    @Id
    @Column(name = "USER_USERNAME")
    private String userName;
    private String password;
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, mappedBy = "user")
    private UserCalendar userCalendar;

    public UserCalendar getUserCalendar() {
        return userCalendar;
    }

    public void setUserCalendar(UserCalendar userCalendar) {
        this.userCalendar = userCalendar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
