package com.boostsolutions.jiraservice.dao;

import com.boostsolutions.jiraservice.model.IssueType;
import com.boostsolutions.jiraservice.model.Priority;

import java.util.List;

public interface PriorityDao {

    boolean add(Priority issueType);

    boolean delete(Priority issueType);

    boolean delete(int id);

//    boolean update(Point point);
//
    List<Priority> findAll();
//
//    Point findById(Long id);
//
//    Point findByName(String name);
//
//    boolean updateSymbol(Point point);
}
