package com.boostsolutions.jiraservice.model;

public class Sprint {

    private int id;
    private String name;
    private int sprintId;
    private int originalBoard;
    private String state;
    private String projectName;
    private String projectKey;
    private int projectId;

    public Sprint() {
    }

    public Sprint(String name, int sprintId, int originalBoard, String state, String projectName, String projectKey, int projectId) {
        this.name = name;
        this.sprintId = sprintId;
        this.originalBoard = originalBoard;
        this.state = state;
        this.projectName = projectName;
        this.projectKey = projectKey;
        this.projectId = projectId;
    }

    public Sprint(int id, String name, int sprintId, int originalBoard, String state, String projectName, String projectKey, int projectId) {
        this.id = id;
        this.name = name;
        this.sprintId = sprintId;
        this.originalBoard = originalBoard;
        this.state = state;
        this.projectName = projectName;
        this.projectKey = projectKey;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public int getOriginalBoard() {
        return originalBoard;
    }

    public void setOriginalBoard(int originalBoard) {
        this.originalBoard = originalBoard;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprint sprint = (Sprint) o;

        if (sprintId != sprint.sprintId) return false;
        if (originalBoard != sprint.originalBoard) return false;
        if (!name.equals(sprint.name)) return false;
        if (state != null ? !state.equals(sprint.state) : sprint.state != null) return false;
        return projectKey.equals(sprint.projectKey);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + sprintId;
        result = 31 * result + originalBoard;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + projectKey.hashCode();
        return result;
    }
}
