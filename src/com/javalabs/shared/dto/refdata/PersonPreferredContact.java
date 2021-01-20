package com.javalabs.shared.dto.refdata;

/**
 * Person Preferred Contact  
 * 
 * @author PATavares
 * @since Jan 2021
 */
public enum PersonPreferredContact {
    PHONE("Phone"),
    EMAIL("Email");

    private String contact;

    PersonPreferredContact(String contact) {
        this.contact = contact;
    }

    public String status() {
        return contact;
    }
}