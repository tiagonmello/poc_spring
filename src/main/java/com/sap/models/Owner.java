package com.sap.models;

import javax.persistence.*;

@Entity
@Table(name="OWNER")
public class Owner extends User {

    @OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, mappedBy = "owner")
    private Team teamOwned;

    public Team getTeamOwned() {
        return teamOwned;
    }

    public void setTeamOwned(Team teamOwned) {
        this.teamOwned = teamOwned;
    }

}
