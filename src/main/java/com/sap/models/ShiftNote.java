package com.sap.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="SHIFT_NOTIFICATION")
public class ShiftNote extends Notification {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayDate;

    private Shift shift;

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}

