package com.boostsolutions.jiraservice.controller;

import com.boostsolutions.jiraservice.MainApp;
import com.boostsolutions.jiraservice.dao.ProjectDao;
import com.boostsolutions.jiraservice.dao.ProjectDaoImpl;
import com.boostsolutions.jiraservice.dao.SprintDao;
import com.boostsolutions.jiraservice.dao.SprintDaoImpl;
import com.boostsolutions.jiraservice.helpers.ChooseStage;
import com.boostsolutions.jiraservice.manager.Manager;
import com.boostsolutions.jiraservice.model.Project;
import com.boostsolutions.jiraservice.model.Sprint;
import com.boostsolutions.jiraservice.models.FlorsModel;
import com.boostsolutions.jiraservice.tasks.SynckTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controls the main application screen
 */
public class MainPageController {

    private SynckTask synckTask;

    @FXML
    private TableView projectTable;

    @FXML
    private TableView sprintTable;

    @FXML
    private TableColumn projectNameColumn;
    @FXML
    private TableColumn projectIdColumn;

    @FXML
    private TableColumn sprintNameColumn;
    @FXML
    private TableColumn sprintStatusColumn;

    @FXML
    private ProgressIndicator progressBar;

    private ObservableList<Project> projectData = null;
    private ObservableList<Sprint> sprintData = null;

    Task copyWorker;
    FileChooser fileChooser = new FileChooser();
    private Stage stage;


    /**
     * Ініціалізація основних параметрів вікна
     *
     * @param loginManager
     * @param sessionID
     */
    public void initSessionID(final Manager loginManager, String sessionID) {
        initProjectTabPanel();
    }

    /**
     * Задання всіх значенбь для компрнентів б/у апартаментів
     */
    private void initProjectTabPanel() {
        ProjectDao projectDao = new ProjectDaoImpl();
        List<Project> projectList = projectDao.findAll();

        if (projectData != null) {
            projectData.clear();
        }
        projectData = FXCollections.observableArrayList(projectList);


        projectTable.setEditable(true);

        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
//        projectNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, Float>, ObservableValue<Project>>() {
//            @Override
//            public ObservableValue call(TableColumn.CellDataFeatures<Project, Float> param) {
//                return new ReadOnlyObjectWrapper(df.format(param.getValue().getProjectId()));
//            }
//        });

        projectTable.setItems(projectData);
    }

    @FXML
    void openFile(){
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);

        fileChooser = new FileChooser();

        if (file != null) {
            MainApp mainApp = new MainApp();
            mainApp.openFile(file);
        }
    }

    /**
     * Видалити поверх для  б/у апартаментів
     */
    @FXML
    void deleteSecondFlor() {
//        int index = floursSecondTableView.getSelectionModel().getSelectedIndex();
//        String[] buttonsNames;
//        String[] message;
//
//        if (index > -1) {
//            new FlorsModelDaoImpl().delete(dataSecond.get(index).getId());
//            new ApartamentModelDaoImpl().deleteByFlorNumber(dataSecond.get(index).getFlourNuber(), dataSecond.get(index).isFirstMarket());
//            buttonsNames = new String[]{"Гаразд"};
//            message = new String[]{"Інформація", "Запис успішно видалено."};
//        } else {
//            buttonsNames = new String[]{"Гаразд"};
//            message = new String[]{"Інформація", "Не вибрано жодного запису."};
//        }
//
//        new ChooseStage(buttonsNames, message[0], message[1], 350, 150).showAndWait();
//        updateSecondTableViewContent();
    }

    /**
     * Видалити поверх для  нових апартаментів
     */
    @FXML
    void deleteFirstFlor() {
        int index = projectTable.getSelectionModel().getSelectedIndex();
        String[] buttonsNames;
        String[] message;

        if (index > -1) {
//            new FlorsModelDaoImpl().delete(projectData.get(index).getId());
//            new ApartamentModelDaoImpl().deleteByFlorNumber(projectData.get(index).getFlourNuber(), projectData.get(index).isFirstMarket());
            buttonsNames = new String[]{"Гаразд"};
            message = new String[]{"Інформація", "Запис успішно видалено."};
        } else {
            buttonsNames = new String[]{"Гаразд"};
            message = new String[]{"Інформація", "Не вибрано жодного запису."};
        }

        new ChooseStage(buttonsNames, message[0], message[1], 350, 150).showAndWait();
    }

    /**
     * Діалог додавання поверху
     */
    public boolean showFlorEditDialog(FlorsModel florsModel) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader florDialogLoader = new FXMLLoader();
            florDialogLoader.setLocation(FlorDialogController.class.getResource("/views/FlorDialogLayout.fxml"));
            AnchorPane page = florDialogLoader.load();

            // Create the dialog Stage.
            Stage florDialogStage = new Stage();
            florDialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
            florDialogStage.setScene(new Scene(page));

            // Set the person into the controller.
            FlorDialogController florDialogController = florDialogLoader.getController();
            florDialogController.setDialogStage(florDialogStage);
            florDialogController.setFlorsModel(florsModel);

            // Show the dialog and wait until the user closes it
            florDialogStage.showAndWait();

            return florDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void synchronizeDataBase() {
        // Create a Task.
        synckTask = new SynckTask();

        progressBar.setVisible(true);
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(synckTask.progressProperty());

        // When completed tasks
        synckTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent>() {

                    @Override
                    public void handle(WorkerStateEvent t) {
                        String[] buttonsNames;
                        String[] message;

                        Boolean copied = synckTask.getValue();
                        if (copied.equals(true)) {
                            synckTask.cancel(true);
                            progressBar.progressProperty().unbind();
                            progressBar.setProgress(0);
                            progressBar.setVisible(false);

                            buttonsNames = new String[]{"Гаразд"};
                            message = new String[]{"Інформація", "Даны успішно оновлено."};
                        } else {
                            buttonsNames = new String[]{"Гаразд"};
                            message = new String[]{"Помилка", "Дані не оновлено."};
                        }

                        new ChooseStage(buttonsNames, message[0], message[1], 350, 150).showAndWait();
                    }
                });

        // Start the Task.
        new Thread(synckTask).start();
    }

    /**
     * Редагувати дяні поверху для нових апартаментів
     */
    @FXML
    private void showSprints() {
        int index = projectTable.getSelectionModel().getSelectedIndex();

        String[] buttonsNames = new String[0];
        String[] message = new String[0];

        if (index > -1) {
            Project project = projectData.get(index);
            initSprintTable(project.getKey());

        } else {
            buttonsNames = new String[]{"Гаразд"};
            message = new String[]{"Інформація", "Не вибрано жодного запису."};

            new ChooseStage(buttonsNames, message[0], message[1], 350, 150).showAndWait();
            }


//        updateFirstTableViewContent();
    }

    private void initSprintTable(String key) {
        SprintDao sprintDao = new SprintDaoImpl();
        List<Sprint> sprints = sprintDao.findAllByProjectKey(key);

        if (sprintData != null) {
            sprintData.clear();
        }
        sprintData = FXCollections.observableArrayList(sprints);


        sprintTable.setEditable(false);

        sprintNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sprintStatusColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
//        projectNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, Float>, ObservableValue<Project>>() {
//            @Override
//            public ObservableValue call(TableColumn.CellDataFeatures<Project, Float> param) {
//                return new ReadOnlyObjectWrapper(df.format(param.getValue().getProjectId()));
//            }
//        });

        sprintTable.setItems(sprintData);
    }

    /**
     * Додати поверх для нових апартаментів
     */
    @FXML
    private void addNewSprint() {
        FlorsModel florsModel = new FlorsModel();

        boolean okClicked = showFlorEditDialog(florsModel);
        if (okClicked) {
            florsModel.setFirstMarket(true);

//            FlorsModelDao florsModelDao = new FlorsModelDaoImpl();
//            florsModelDao.add(florsModel);

//            updateFirstTableViewContent();
        }
    }

    /**
     * закрити додаток
     */
    @FXML
    void closeApplication() {
        int option = 0;
        ChooseStage chooseStage = new ChooseStage(new String[]{"Так", "Ні"},
                "Інформація", "Ви справді бажаєте завершити роботу?", 350, 150);
        chooseStage.showAndWait();
        option = chooseStage.getOption();

        if (option == 1) {
            System.exit(0);
        }
    }

}