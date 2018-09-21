package com.sap.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="USER")
public class User {

    @Id
    @Column(name = "USER_USERNAME")
    private String userName;
    private String password;
    private String matchingPassword;
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, mappedBy = "owner")
    private Team team;

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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
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
