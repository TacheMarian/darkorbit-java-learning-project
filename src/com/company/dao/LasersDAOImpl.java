package com.company.dao;

import com.company.configs.DatabaseConfig;
import com.company.entities.Laser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LasersDAOImpl implements LasersDAO {

    public LasersDAOImpl() {
    }

    private Connection connection;


    @Override
    public Laser get(Laser laser){
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM lasers WHERE lasers.id_lasers = ?;");
            stmt.setInt(1, laser.getIdLasers());


            rs = stmt.executeQuery();
            if (rs.next()) {

                int idLasers = rs.getInt("id_lasers");
                String name = rs.getString("name");
                int damage = rs.getInt("damage");
                int priceCredits = rs.getInt("priceCredits");
                int priceUridium = rs.getInt("priceUridium");

                laser.setIdLasers(idLasers);
                laser.setName(name);
                laser.setDamage(damage);
                laser.setPriceCredits(priceCredits);
                laser.setPriceUridium(priceUridium);
            } else {
                System.out.println("Laser info problem");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the laser info: " + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources in laser: " + e.getMessage());

            }
        }

        return laser;
    }

    @Override
    public List<String> getAllInfoAboutAllLasers(Laser laser){
        List<String> listOfAllLasers = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM darkorbit_accounts.lasers;");

            rs = stmt.executeQuery();
            while (rs.next()) {
                String laserName = rs.getString("name");
                int damage = rs.getInt("damage");
                int priceCredits = rs.getInt("priceCredits");
                int priceUridium = rs.getInt("priceUridium");
                String resultSet = "";
                if(priceUridium==0){
                    resultSet = "Laser name: "+ laserName + "; Maximum damage output: " + damage + "; Price: " + priceCredits + " credits";
                }else if(priceCredits == 0){
                    resultSet = "Laser name: "+ laserName + "; Maximum damage output: " + damage + "; Price: " + priceUridium + " uridium";
                }
                listOfAllLasers.add(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the list of laser: " + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources: " + e.getMessage());

            }
        }

        return listOfAllLasers;
    }

    @Override
    public List<String> getAllLasers(Laser laser){
        List<String> listOfAllLasers = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM darkorbit_accounts.lasers;");

            rs = stmt.executeQuery();
            while (rs.next()) {
                String laserName = rs.getString("name");
                listOfAllLasers.add(laserName);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the list of laser: " + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources: " + e.getMessage());

            }
        }

        return listOfAllLasers;
    }

    @Override
    public int updateBuyLaserCredits(Laser laser, int priceInCredits) throws SQLException {
        return 0;
    }

    @Override
    public int updateBuyLaserUridium(Laser laser, int priceInUridium) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Laser laser) throws SQLException {

    }

    @Override
    public void delete(Laser laser) throws SQLException {

    }
}
