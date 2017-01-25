package com.boostsolutions.jiraservice.dao;

import com.boostsolutions.jiraservice.helpers.JdbcHelper;
import com.boostsolutions.jiraservice.model.IssueType;
import com.boostsolutions.jiraservice.model.Priority;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriorityDaoImpl implements PriorityDao {

    private static final String FIND_ALL_SQL = "select * from priority";
    private static final String FIND_BY_NAME = "select * from priority where name = ?";
    private static final String DELETE_BY_ID = "delete from priority where id = ?";
    private static final String INSERT_TO_DATABASE = "INSERT INTO priority (name, self, description, priorityId) VALUES (?,?,?,?)";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_SELF = "self";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_PRIORITY_ID = "priorityId";


    /**
     * Додавання запису
     */
    @Override
    public boolean add(Priority project) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(INSERT_TO_DATABASE);
            preparedStatement.setString(1, project.getName().toLowerCase());
            preparedStatement.setString(2, project.getSelf());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setLong(4, project.getPriorityId());
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
    public boolean delete(Priority project) {
        return delete(project.getId());
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
    public List<Priority> findAll() {
        List<Priority> points = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.execute(FIND_ALL_SQL);
            while (rs.next()) {
                Priority point = new Priority();
                point.setId(rs.getInt(FIELD_ID));
                point.setName(rs.getString(FIELD_NAME));
                point.setSelf(rs.getString(FIELD_SELF));
                point.setDescription(rs.getString(FIELD_DESCRIPTION));
                point.setPriorityId(rs.getLong(FIELD_PRIORITY_ID));

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
//    /**
//     * Знайти по назві
//     */
//    @Override
//    public Point findByName(String name) {
//        Point point = new Point();
//        try {
//            PreparedStatement preparedStatement = JdbcHelper.getPreparedStatement(FIND_BY_NAME);
//            preparedStatement.setString(1, name);
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
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return point;
//    }
//
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
