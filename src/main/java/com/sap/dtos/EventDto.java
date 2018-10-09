package com.sap.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EVENT_DTO")
public class EventDto {

    @Id
    private String eventDate;

    private String shift;

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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}

