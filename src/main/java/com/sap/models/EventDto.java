package com.sap.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="EVENT_DTO")
public class EventDto {

    @Id
    private String eventDate;

    private String lateShift;

    private String dayShift;

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

