package com.boostsolutions.jiraservice;

import com.boostsolutions.jiraservice.dao.*;
import com.boostsolutions.jiraservice.model.*;
import com.boostsolutions.jiraservice.parser.ExcelParser;
import com.boostsolutions.jiraservice.parser.ExcelXLSXParser;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {

    String account = "vitaliy.holovko";

    String password = "jrcfyjxrf1";

//    FileChooser fileChooser = new FileChooser();

  public JSONArray jiraRequest(String url) {
        final String plainCreds = account + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        JSONArray ja;

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("Content-Type", "application/json");
            getRequest.addHeader("Authorization", "Basic " + base64Creds);

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Error in connection.");
                alert.setContentText("Some params are wrong!");

                alert.showAndWait();
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }


            HttpEntity date = response.getEntity();

            ja = new JSONArray(EntityUtils.toString(date));

            httpClient.getConnectionManager().shutdown();

            return ja;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray jiraRequestBoard(String url) {
        final String plainCreds = account + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        JSONArray ja;

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", "Basic " + base64Creds);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = httpClient.execute(httpGet, responseHandler);

            JSONObject jaObject = new JSONObject(response);
//
            ja = new JSONArray();
            ja=jaObject.getJSONArray("values");
//            httpClient.getConnectionManager().shutdown();
//
            return ja;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    void jiraRequest(String url, String content) {
        final String plainCreds = account + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost getRequest = new HttpPost(url);
            getRequest.addHeader("Content-Type", "application/json");
            getRequest.addHeader("Authorization", "Basic " + base64Creds);

            StringEntity input = new StringEntity(content);
            input.setContentType("application/json");
            getRequest.setEntity(input);

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Operation not completed");
                alert.setContentText("Some issues not added to Jira!");

                alert.showAndWait();
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }


            httpClient.getConnectionManager().shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearProjects() {
        ProjectDao projectDao = new ProjectDaoImpl();
        List<Project> projects = projectDao.findAll();
        for (Project project : projects) {
            projectDao.delete(project);
        }
    }

    private void clearSprints() {
        SprintDao sprintDao = new SprintDaoImpl();
        List<Sprint> sprints = sprintDao.findAll();
        for (Sprint sprint : sprints) {
            sprintDao.delete(sprint);
        }
    }

    private void clearPriority() {
        PriorityDao projectDao = new PriorityDaoImpl();
        List<Priority> projects = projectDao.findAll();
        for (Priority project : projects) {
            projectDao.delete(project);
        }
    }

    public boolean allProjects() throws JSONException {
        JSONArray jsonArray = jiraRequest("http://boosting.atlassian.net/rest/api/2/project");
        List<Project> projectList = new ArrayList<>();

        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            projectList.add(new Project(objectInArray.getLong("id"), objectInArray.getString("name"), objectInArray.getString("self"), objectInArray.getString("projectTypeKey"), objectInArray.getString("key")));
        }

        clearProjects();

        ProjectDao projectDao = new ProjectDaoImpl();
        for (Project project : projectList) {
            projectDao.add(project);
        }

        System.out.println(projectList.size());
        return true;
    }

    public boolean allProjectsBoards() throws JSONException {
        JSONArray jsonArray = jiraRequestBoard("http://boosting.atlassian.net/rest/agile/1.0/board?maxResults=100");
        List<Project> projectList = new ArrayList<>();

        List<Sprint> sprints = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);

            JSONArray jsonArrayBoard = jiraRequestBoard("http://boosting.atlassian.net/rest/agile/1.0/board/"+objectInArray.getInt("id")+"/project");

            for (int iBoard = 0, sizeBoard = jsonArrayBoard.length(); iBoard < sizeBoard; iBoard++) {
                JSONObject boardJObj = jsonArrayBoard.getJSONObject(iBoard);

                JSONArray jsonArraySprint = jiraRequestBoard("http://boosting.atlassian.net/rest/agile/1.0/board/"+objectInArray.getInt("id")+"/sprint?maxResults=500");
                if (jsonArraySprint.length()>0) {
                    for (int iSprint = 0, sizeSprint = jsonArrayBoard.length(); iSprint < sizeSprint; iSprint++) {
                        JSONObject sprintJObj = jsonArraySprint.getJSONObject(iSprint);
                        sprints.add(new Sprint(sprintJObj.getString("name"), sprintJObj.getInt("id"), sprintJObj.getInt("originBoardId"), sprintJObj.getString("state"),
                                boardJObj.getString("name"),boardJObj.getString("key"),boardJObj.getInt("id")));
                    }
                }
            }
        }

        clearSprints();

        SprintDao sprintDao = new SprintDaoImpl();
        for (Sprint sprint : sprints) {
            sprintDao.add(sprint);
        }

        System.out.println(projectList.size());
        System.out.println(sprints.size());
        return true;
    }

    @FXML
    void getIssue() throws JSONException {
        JSONArray jsonArray = jiraRequest("http://boosting.atlassian.net/rest/api/2/issue/SBA-322");
        List<Project> projectList = new ArrayList<>();

        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            projectList.add(new Project(objectInArray.getLong("id"), objectInArray.getString("name"), objectInArray.getString("self"), objectInArray.getString("projectTypeKey"), objectInArray.getString("key")));
        }

        clearProjects();

        ProjectDao projectDao = new ProjectDaoImpl();
        for (Project project : projectList) {
            projectDao.add(project);
        }

        System.out.println(projectList.size());
    }

    @FXML
    public void allIssueTypes() throws JSONException {
        JSONArray jsonArray = jiraRequest("http://boosting.atlassian.net/rest/api/2/issuetype");
        List<IssueType> projectList = new ArrayList<>();

        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            projectList.add(new IssueType(objectInArray.getLong("id"), objectInArray.getString("name"), objectInArray.getString("self"), objectInArray.getString("description")));
        }

        clearIssueTypes();

        IssueTypeDao projectDao = new IssueTypeDaoImpl();
        for (IssueType project : projectList) {
            projectDao.add(project);
        }

        System.out.println(projectList.size());
    }

    private void clearIssueTypes() {
        IssueTypeDao projectDao = new IssueTypeDaoImpl();
        List<IssueType> projects = projectDao.findAll();
        for (IssueType project : projects) {
            projectDao.delete(project);
        }
    }

    @FXML
    public void allPriority() throws JSONException {
        JSONArray jsonArray = jiraRequest("http://boosting.atlassian.net/rest/api/2/priority");
        List<Priority> projectList = new ArrayList<>();

        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            projectList.add(new Priority(objectInArray.getLong("id"), objectInArray.getString("name"), objectInArray.getString("self"), objectInArray.getString("description")));
        }

        clearPriority();

        PriorityDao projectDao = new PriorityDaoImpl();
        projectList.forEach(projectDao::add);
    }

//    void analize() throws JSONException, UnsupportedEncodingException {
//        fileChooser.setTitle("Open Resource File");
//        File file = fileChooser.showOpenDialog(stage);
//
//        fileChooser = new FileChooser();
//
//        if (file != null) {
//            openFile(file);
//        }
//    }

    public void openFile(File file) {
        try {
            PreparedContent users;

            if (file.getAbsoluteFile().getName().contains("xlsx")){
                users = ExcelXLSXParser.parse(file.getAbsolutePath());
            } else {
                users = ExcelParser.parse(file.getAbsolutePath());
            }

            List<IssueContent> issueContents = convertDate(users);

            generateAndSend(issueContents);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<IssueContent> convertDate(PreparedContent users) {
        List<IssueContent> list = new ArrayList<>();

        for (DataModel dataModel : users.getDataModelList()) {
            IssueContent issueContent = new IssueContent();
            for (int j = 0; j < users.getKeys().length; j++) {
                String key = users.getKeys()[j];
                if (key.equals("id")) {
                    issueContent.setId(dataModel.getData()[j]);
                }
                if (key.equals("name")) {
                    issueContent.setName(dataModel.getData()[j]);
                }
                if (key.equals("description")) {
                    issueContent.setDescription(dataModel.getData()[j]);
                }
                if (key.equals("project")) {
                    issueContent.setProject(dataModel.getData()[j]);
                }
                if (key.equals("priority")) {
                    issueContent.setPriority(dataModel.getData()[j]);
                }
                if (key.equals("type")) {
                    issueContent.setType(dataModel.getData()[j]);
                }
                if (key.equals("assigned")) {
                    issueContent.setAssigned(dataModel.getData()[j]);
                }
                if (key.equals("duedate")) {
                    issueContent.setDuedate(dataModel.getData()[j]);
                }
            }
            list.add(issueContent);
        }

        return list;
    }

    public void generateAndSend(List<IssueContent> issueContents) throws JSONException {
        ProjectDao projectDao = new ProjectDaoImpl();
        IssueTypeDao issueTypeDao = new IssueTypeDaoImpl();
        PriorityDao priorityDao = new PriorityDaoImpl();

        List<Project> projects = projectDao.findAll();
        List<IssueType> issueTypes = issueTypeDao.findAll();
        List<Priority> priorities = priorityDao.findAll();

        if (projects.size()==0){
            allProjects();
        }

        if (issueTypes.size()==0){
            allIssueTypes();
        }

        if (priorities.size()==0){
            allPriority();
        }

        List<JSONObject> objects = new ArrayList<>();

        for (IssueContent issueContent : issueContents) {
            JSONObject issueJson = new JSONObject();
            JSONObject fielsd = new JSONObject();

            JSONObject project = new JSONObject();
            project.put("id", findProjectIdByName(projects, issueContent.getProject()));

            JSONObject issueType = new JSONObject();
            issueType.put("id", findIssueTypeByName(issueTypes, issueContent.getType()));

            JSONObject assignee = new JSONObject();
            assignee.put("name", issueContent.getAssigned());

            JSONObject priority = new JSONObject();
            priority.put("id", findPriorityByName(priorities, issueContent.getPriority()));

            fielsd.put("project", project);
            fielsd.put("issuetype", issueType);
            fielsd.put("summary", issueContent.getName());
            fielsd.put("description", issueContent.getDescription());
            fielsd.put("duedate", issueContent.getDuedate());
            fielsd.put("assignee", assignee);
//            fielsd.put("priority", priority);

            issueJson.put("fields", fielsd);

            objects.add(issueJson);

        }

        for (JSONObject jsonObject : objects) {
            jiraRequest("https://boosting.atlassian.net/rest/api/2/issue", jsonObject.toString());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Operation successfully completed");
        alert.setContentText("All issues added to Jira!");

        alert.showAndWait();

    }

    private Long findPriorityByName(List<Priority> priorities, String priorityName) {
        for (Priority priority : priorities) {
            if (priority.getName().equalsIgnoreCase(priorityName)) {
                return priority.getPriorityId();
            }
        }
        return 0L;
    }

    private Long findIssueTypeByName(List<IssueType> issueTypes, String type) {
        for (IssueType issueType : issueTypes) {
            if (issueType.getName().equalsIgnoreCase(type)) {
                return issueType.getIssueTypeId();
            }
        }
        return 0L;
    }

    private Long findProjectIdByName(List<Project> projects, String projectName) {
        for (Project project1 : projects) {
            if (project1.getName().equalsIgnoreCase(projectName)) {
                return project1.getProjectId();
            }
        }
        return 0L;
    }
}
