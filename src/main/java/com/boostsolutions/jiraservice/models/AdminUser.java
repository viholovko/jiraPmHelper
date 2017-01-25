package com.boostsolutions.jiraservice.models;

public class AdminUser {

    private Long id;
    private String login;
    private String password;

    public AdminUser() {
    }

    public AdminUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AdminUser(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
