package com.boostsolutions.jiraservice.model;

/**
 * @author viholovko on 07.11.16.
 */
public class DataModel {
    private String email;
    private String[] data;

    public DataModel() {
    }

    public DataModel(String email, String[] data) {
        this.email = email;
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
