package com.company.dao;

import com.company.entities.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDAO {
    Equipment get(Equipment equipment) throws SQLException;

    List<String> getAll() throws SQLException;

    void insert(Equipment equipment) throws SQLException ;

    int updateLaser(int accountsId, String laserName) throws SQLException ;

    int updateLaserUnEquip(int accountsId, String laserName) throws SQLException ;

    int updateGenerator(int accountsId, String generatorName) throws SQLException ;

    int updateGeneratorUnEquip(int accountsId, String generatorName) throws SQLException ;

    void delete(Equipment equipment) throws SQLException;
}
