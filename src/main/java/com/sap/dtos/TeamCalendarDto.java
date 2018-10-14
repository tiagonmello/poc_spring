package com.sap.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="TEAM_CALENDAR_DTO")
public class TeamCalendarDto {

    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer dayLimit;

    private Integer lateLimit;

    private Integer specialDayLimit;

    private Integer specialLateLimit;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Integer dayLimit) {
        this.dayLimit = dayLimit;
    }

    public Integer getLateLimit() {
        return lateLimit;
    }

    public void setLateLimit(Integer lateLimit) {
        this.lateLimit = lateLimit;
    }

    public Integer getSpecialDayLimit() {
        return specialDayLimit;
    }

    public void setSpecialDayLimit(Integer specialDayLimit) {
        this.specialDayLimit = specialDayLimit;
    }

    public Integer getSpecialLateLimit() {
        return specialLateLimit;
    }

    public void setSpecialLateLimit(Integer specialLateLimit) {
        this.specialLateLimit = specialLateLimit;
    }
}

