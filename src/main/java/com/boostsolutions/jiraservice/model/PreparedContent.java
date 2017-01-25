package com.boostsolutions.jiraservice.model;

import java.util.List;

/**
 * @author viholovko on 11/9/2016.
 */
public class PreparedContent {
    private String[] keys;
    private List<DataModel> dataModelList;

    public PreparedContent() {
    }

    public PreparedContent(String[] keys, List<DataModel> dataModelList) {
        this.keys = keys;
        this.dataModelList = dataModelList;
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public List<DataModel> getDataModelList() {
        return dataModelList;
    }

    public void setDataModelList(List<DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }
}
