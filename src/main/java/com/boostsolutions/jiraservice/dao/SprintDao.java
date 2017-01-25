package com.boostsolutions.jiraservice.dao;

import com.boostsolutions.jiraservice.model.Sprint;

import java.util.List;

public interface SprintDao {

    boolean add(Sprint sprint);

    boolean delete(Sprint sprint);

    boolean delete(int id);

//    boolean update(Point point);
//
    List<Sprint> findAll();

    List<Sprint> findAllByProjectKey(String key);

//    Point findById(Long id);
//
//    Point findByName(String name);
//
//    boolean updateSymbol(Point point);
}
