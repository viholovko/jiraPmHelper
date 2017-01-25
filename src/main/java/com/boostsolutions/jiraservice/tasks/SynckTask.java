package com.boostsolutions.jiraservice.tasks;

import com.boostsolutions.jiraservice.MainApp;
import javafx.concurrent.Task;
import org.json.JSONException;

/**
 * @author viholovko on 1/25/2017.
 */
public class SynckTask extends Task<Boolean> {

    @Override
    protected Boolean call() throws Exception {

        boolean key = false;
        try {
            MainApp mainApp = new MainApp();

            mainApp.allIssueTypes();
            mainApp.allPriority();


            key = mainApp.allProjects();
            key = mainApp.allProjectsBoards();

            return key;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
