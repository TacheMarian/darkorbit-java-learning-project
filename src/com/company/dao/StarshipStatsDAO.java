package com.company.dao;

import com.company.entities.Ship;
import com.company.entities.StarshipStats;

import java.sql.SQLException;
import java.util.List;

public interface StarshipStatsDAO {
    StarshipStats get(StarshipStats starshipStats) throws SQLException;

    List<String> getAll(StarshipStats starshipStats)throws SQLException;

    int updateChosenShipName(StarshipStats starshipStats) throws SQLException ;

    int updateDamage(StarshipStats starshipStats) throws SQLException ;

    int updateShield(StarshipStats starshipStats) throws SQLException ;

    int updateHp(StarshipStats starshipStats) throws SQLException ;

    void insert(StarshipStats starshipStats) throws SQLException ;

    void delete(StarshipStats starshipStats) throws SQLException;
}
