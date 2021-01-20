package com.javalabs.shared.dto.refdata;

/**
 * Person Status enum  
 * Constants that classify a Persons situation 
 * 
 * @author PATavares
 * @since Jan 2021
 */
public enum PersonStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String status;

    PersonStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }
}