package com.company;

import com.company.entities.UserShips;

import java.sql.SQLException;
import java.util.List;

public interface UserShipsDAO {
    UserShips get(UserShips userShips) throws SQLException;

    List<String> getAll(UserShips userShips) throws SQLException;

    void update(UserShips userShips) throws SQLException ;

    int insert(UserShips userShips) throws SQLException ;

    void delete(UserShips userShips) throws SQLException;
}
