package com.boostsolutions.jiraservice.model;

public class IssueType {

    private int id;
    private Long issueTypeId;
    private String name;
    private String self;
    private String description;

    public IssueType() {
    }

    public IssueType(Long issueTypeId, String name, String self, String description) {
        this.issueTypeId = issueTypeId;
        this.name = name;
        this.self = self;
        this.description = description;
    }

    public IssueType(int id, Long issueTypeId, String name, String self, String description) {
        this.id = id;
        this.issueTypeId = issueTypeId;
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

    public Long getIssueTypeId() {
        return issueTypeId;
    }

    public void setIssueTypeId(Long issueTypeId) {
        this.issueTypeId = issueTypeId;
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
