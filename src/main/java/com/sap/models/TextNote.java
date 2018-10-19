package com.sap.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TEXT_NOTIFICATION")
public class TextNote extends Notification {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

