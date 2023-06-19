package com.company.dao;

import com.company.entities.Ship;

import java.sql.SQLException;
import java.util.List;

public interface ShipsDAO {
    Ship get(Ship ship) throws SQLException;

    List<String> getAll(Ship ship)throws SQLException;

    void update(Ship ship) throws SQLException ;

    void insert(Ship ship) throws SQLException ;

    void delete(Ship ship) throws SQLException;
}
