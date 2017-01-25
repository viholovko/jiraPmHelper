package com.boostsolutions.jiraservice.model;

public class Priority {

    private int id;
    private Long priorityId;
    private String name;
    private String self;
    private String description;

    public Priority() {
    }

    public Priority(Long priorityId, String name, String self, String description) {
        this.priorityId = priorityId;
        this.name = name;
        this.self = self;
        this.description = description;
    }

    public Priority(int id, Long priorityId, String name, String self, String description) {
        this.id = id;
        this.priorityId = priorityId;
        this.name = name;
        this.self = self;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
