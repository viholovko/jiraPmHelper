package com.boostsolutions.jiraservice.dao;

import com.boostsolutions.jiraservice.model.Project;

import java.util.List;

public interface ProjectDao {

    boolean add(Project project);

    boolean delete(Project project);

    boolean delete(int id);

//    boolean update(Point point);
//
    List<Project> findAll();
//
//    Point findById(Long id);
//
//    Point findByName(String name);
//
//    boolean updateSymbol(Point point);
}
