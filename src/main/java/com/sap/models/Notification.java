package com.sap.models;

import javax.persistence.*;

@Entity
@Table(name="NOTIFICATION")
public abstract class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTIFICATION_ID")
    private Integer id;

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

}

