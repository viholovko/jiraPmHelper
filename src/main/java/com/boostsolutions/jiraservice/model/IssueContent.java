package com.boostsolutions.jiraservice.model;

/**
 * @author viholovko on 07.11.16.
 */
public class IssueContent {
    private String id;
    private String name;
    private String description;
    private String project;
    private String priority;
    private String type;
    private String assigned;
    private String duedate;

    public IssueContent() {
    }

    public IssueContent(String id, String name, String description, String project, String priority, String type, String assigned, String duedate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.project = project;
        this.priority = priority;
        this.type = type;
        this.assigned = assigned;
        this.duedate = duedate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
}
