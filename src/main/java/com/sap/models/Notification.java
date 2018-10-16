package com.sap.models;

import javax.persistence.*;

@Entity
@Table(name="NOTIFICATION")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTIFICATION_ID")
    private Integer id;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    private String text;
}

