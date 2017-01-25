package com.boostsolutions.jiraservice.dao;

import com.boostsolutions.jiraservice.model.IssueType;
import com.boostsolutions.jiraservice.model.Project;

import java.util.List;

public interface IssueTypeDao {

    boolean add(IssueType issueType);

    boolean delete(IssueType issueType);

    boolean delete(int id);

//    boolean update(Point point);
//
    List<IssueType> findAll();
//
//    Point findById(Long id);
//
//    Point findByName(String name);
//
//    boolean updateSymbol(Point point);
}
