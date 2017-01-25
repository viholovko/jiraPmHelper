package com.boostsolutions.jiraservice.model;

public class Project {

    private int id;
    private Long projectId;
    private String name;
    private String self;
    private String projectTypeKey;
    private String key;


    public Project() {
    }

    public Project(Long projectId, String name, String self, String projectTypeKey, String key) {
        this.projectId = projectId;
        this.name = name;
        this.self = self;
        this.projectTypeKey = projectTypeKey;
        this.key = key;
    }

    public Project(int id, Long projectId, String name, String self, String projectTypeKey, String key) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.self = self;
        this.projectTypeKey = projectTypeKey;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getProjectTypeKey() {
        return projectTypeKey;
    }

    public void setProjectTypeKey(String projectTypeKey) {
        this.projectTypeKey = projectTypeKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
