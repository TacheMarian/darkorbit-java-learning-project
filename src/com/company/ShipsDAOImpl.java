package com.company;

import com.company.entities.Ship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipsDAOImpl implements ShipsDAO {
    public ShipsDAOImpl() {
    }

    private Connection connection;

    @Override
    public Ship get(Ship ship){
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM ships WHERE ships.id_ships = ?;");
            stmt.setInt(1, ship.getShipId());


            rs = stmt.executeQuery();
            if (rs.next()) {
                String shipName = rs.getString("ShipName");
                int shipHp = rs.getInt("ShipHp");
                int shipLaserSlots = rs.getInt("ShipsLaserSlots");
                int shipGenSlots = rs.getInt("ShipsGenSlots");
                int shipPriceCredits = rs.getInt("ShipPriceCredits");
                int shipUridiumPrice = rs.getInt("ShipPriceUridium");

                ship.setShipName(shipName);
                ship.setShipHp(shipHp);
                ship.setShipsLaserSlots(shipLaserSlots);
                ship.setShipsGenSlots(shipGenSlots);
                ship.setShipPriceCredits(shipPriceCredits);
                ship.setShipPriceUridium(shipUridiumPrice);
            } else {
                System.out.println("Ship info problem");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the ship info: " + e.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources in ship: " + e.getMessage());

            }
        }

        return ship;
    }

    @Override
    public List<String> getAll(Ship ship) {
        List<String> listOfAllShips = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement("SELECT ships.ShipName from ships;");

            rs = stmt.executeQuery();
            while (rs.next()) {
                String shipName = rs.getString("ShipName");
                listOfAllShips.add(shipName);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the list of ships: " + e.getMessage());

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

        return listOfAllShips;
    }

    @Override
    public void update(Ship ship) throws SQLException {

    }

    @Override
    public void insert(Ship ship) throws SQLException {

    }

    @Override
    public void delete(Ship ship) throws SQLException {

    }
}
