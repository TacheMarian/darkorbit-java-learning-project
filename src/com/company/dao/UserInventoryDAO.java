package com.company.dao;

import com.company.entities.Account;
import com.company.entities.UserInventory;

import java.sql.SQLException;
import java.util.List;

public interface UserInventoryDAO {
    UserInventory get(UserInventory userInventory) throws SQLException;

    int get(Account account, String name) throws SQLException;

    List<String> getAll() throws SQLException;

    void insert(UserInventory userInventory) throws SQLException ;

    int updateLaser(int accountsId, String laserName) throws SQLException ;

    int updateLaserEquip(int accountsId, String laserName) throws SQLException ;

    int updateGenerator(int accountsId, String generatorName) throws SQLException ;

    int updateGeneratorEquip(int accountsId, String generatorName) throws SQLException ;

    void delete(UserInventory userInventory) throws SQLException;
}
