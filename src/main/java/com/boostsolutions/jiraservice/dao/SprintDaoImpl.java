package com.boostsolutions.jiraservice.dao;

import com.boostsolutions.jiraservice.helpers.JdbcHelper;
import com.boostsolutions.jiraservice.model.Sprint;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SprintDaoImpl implements SprintDao {

    private static final String FIND_ALL_SQL = "select * from sprints";
    private static final String FIND_ALL_SQL_KEY = "SELECT * FROM sprints WHERE projectKey LIKE ?;";
    private static final String DELETE_BY_ID = "delete from sprints where id = ?";
    private static final String INSERT_TO_DATABASE = "INSERT INTO sprints (name, sprintId, projectName, originalBoard, state, projectKey, projectId) VALUES (?,?,?,?,?,?,?)";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_SPRINT_ID = "sprintId";
    private static final String FIELD_PROJECT_NAME = "projectName";
    private static final String FIELD_ORIGINAL_BOARD = "originalBoard";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_PROJECT_KEY = "projectKey";
    private static final String FIELD_PROJECT_ID = "projectId";

    /**
     * Додавання запису
     */
    @Override
    public boolean add(Sprint sprint) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(INSERT_TO_DATABASE);
            preparedStatement.setString(1, sprint.getName().toLowerCase());
            preparedStatement.setLong(2, sprint.getSprintId());
            preparedStatement.setString(3, sprint.getProjectName().toLowerCase());
            preparedStatement.setLong(4, sprint.getOriginalBoard());
            preparedStatement.setString(5, sprint.getState());
            preparedStatement.setString(6, sprint.getProjectKey());
            preparedStatement.setLong(7, sprint.getProjectId());
            id  = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (id > 0);
    }

    /**
     * Видалити об'єкт
     */
    @Override
    public boolean delete(Sprint sprint) {
        return delete(sprint.getId());
    }

    /**
     * Видалити по id
     */
    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

//    /**
//     * Обновити
//     */
//    @Override
//    public boolean update(Point point) {
//        int id = 0;
//        try {
//            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(UPDATE_TO_DATABASE);
//            preparedStatement.setString(1, point.getName());
//            preparedStatement.setString(2, point.getDescription());
//            preparedStatement.setString(3, point.getShortDescription());
//            preparedStatement.setFloat(4, (float) point.getPositionX());
//            preparedStatement.setFloat(5, (float) point.getPositionY());
//            preparedStatement.setLong(6, point.getId());
//            id  = preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return (id > 0);
//    }
//
    /**
     * Знайти всі записи
     */
    @Override
    public List<Sprint> findAll() {
        List<Sprint> points = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.execute(FIND_ALL_SQL);
            while (rs.next()) {
                Sprint point = new Sprint();
                point.setId(rs.getInt(FIELD_ID));
                point.setName(rs.getString(FIELD_NAME));
                point.setSprintId(rs.getInt(FIELD_SPRINT_ID));
                point.setProjectName(rs.getString(FIELD_PROJECT_NAME));
                point.setOriginalBoard(rs.getInt(FIELD_ORIGINAL_BOARD));
                point.setState(rs.getString(FIELD_STATE));
                point.setProjectKey(rs.getString(FIELD_PROJECT_KEY));
                point.setProjectId(rs.getInt(FIELD_PROJECT_ID));

                points.add(point);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JdbcHelper.closeContection();
        }

        return points;
    }

//    /**
//     * Знайти по id
//     */
//    @Override
//    public Point findById(Long id) {
//        Point point = new Point();
//        try {
//            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(FIND_BY_ID);
//            preparedStatement.setLong(1, id);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                point.setId(rs.getLong(FIELD_ID));
//                point.setName(rs.getString(FIELD_NAME));
//                point.setDescription(rs.getString(FIELD_DESCRIPTION) != null ? rs.getString(FIELD_DESCRIPTION) : "");
//                point.setShortDescription(rs.getString(FIELD_SHORT_DESCRIPTION) != null ? rs.getString(FIELD_SHORT_DESCRIPTION) : "");
//                point.setPositionX(rs.getDouble(FIELD_POSITION_X));
//                point.setPositionY(rs.getDouble(FIELD_POSITION_Y));
//                point.setSymbol(rs.getBytes(FIELD_SYMBOL));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return point;
//    }
//
    /**
     * Знайти по назві
     */
    @Override
    public List<Sprint> findAllByProjectKey(String key) {
        List<Sprint> points = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(FIND_ALL_SQL_KEY);
            preparedStatement.setString(1, key);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Sprint point = new Sprint();
                point.setId(rs.getInt(FIELD_ID));
                point.setName(rs.getString(FIELD_NAME));
                point.setSprintId(rs.getInt(FIELD_SPRINT_ID));
                point.setProjectName(rs.getString(FIELD_PROJECT_NAME));
                point.setOriginalBoard(rs.getInt(FIELD_ORIGINAL_BOARD));
                point.setState(rs.getString(FIELD_STATE));
                point.setProjectKey(rs.getString(FIELD_PROJECT_KEY));
                point.setProjectId(rs.getInt(FIELD_PROJECT_ID));

                points.add(point);
                HashSet<Sprint> set = new HashSet<>(points);
                return new ArrayList<>(set);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return points;
    }

//    /**
//     * Обновити зображення для точки
//     */
//    @Override
//    public boolean updateSymbol(Point point) {
//        int id = 0;
//        try {
//            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(UPDATE_TO_SYMBOL);
//            preparedStatement.setObject(1, point.getSymbol());
//            preparedStatement.setLong(2, point.getId());
//            id  = preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return (id > 0);
//    }
}
