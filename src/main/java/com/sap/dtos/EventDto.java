package com.sap.dtos;

import javax.persistence.*;

@Entity
@Table(name="EVENT_DTO")
public class EventDto {

    @Id
    private String eventDate;

    private String lateShift;

    private String dayShift;

    private String dayAvailability;

    public String getDayAvailability() {
        return dayAvailability;
    }

    public void setDayAvailability(String dayAvailability) {
        this.dayAvailability = dayAvailability;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLateShift() {
        return lateShift;
    }

    public void setLateShift(String lateShift) {
        this.lateShift = lateShift;
    }

    public String getDayShift() {
        return dayShift;
    }

    public void setDayShift(String dayShift) {
        this.dayShift = dayShift;
    }
}

