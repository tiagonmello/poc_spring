package com.sap.models;

import javax.persistence.*;

@Entity
@Table(name="DAY_DTO")
public class DayDto {

    @Id
    private String dayDate;

    private String dayType;

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }
}

