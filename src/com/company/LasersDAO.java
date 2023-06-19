package com.company;

import com.company.entities.Laser;

import java.sql.SQLException;
import java.util.List;

public interface LasersDAO {
    Laser get(Laser laser) throws SQLException;

    List<String> getAllInfoAboutAllLasers(Laser laser)throws SQLException;

    List<String> getAllLasers(Laser laser)throws SQLException;

    int updateBuyLaserCredits(Laser laser, int priceInCredits) throws SQLException ;

    int updateBuyLaserUridium(Laser laser, int priceInUridium) throws SQLException ;

    void insert(Laser laser) throws SQLException ;

    void delete(Laser laser) throws SQLException;

}
