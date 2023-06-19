package com.company;

import com.company.entities.UserShips;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserShipsImpl implements UserShipsDAO {
    private Connection connection;

    public UserShipsImpl() {
    }


    @Override
    public UserShips get(UserShips userShips) throws SQLException {
        return null;
    }

    @Override
    public List<String> getAll(UserShips userShips) throws SQLException {
        List<String> listOfUsersShips = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement("SELECT ships.ShipName from ships join user_ships on " +
                    "ships.id_ships = user_ships.id_ships join accounts on accounts.id_accounts = user_ships.id_accounts " +
                    "where accounts.id_accounts = ?;");
            stmt.setInt(1, userShips.getIdAccounts());

            rs = stmt.executeQuery();
            while (rs.next()) {
                String shipName = rs.getString("ShipName");
                listOfUsersShips.add(shipName);
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

        return listOfUsersShips;
    }


    @Override
    public void update(UserShips userShips) throws SQLException {

    }

    @Override
    public int insert(UserShips userShips) throws SQLException {
        PreparedStatement stmt = null;
        int result=0;
        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement("INSERT INTO user_ships " +
                    "(id_accounts, id_ships) VALUES (?, ?)");

            stmt.setInt(1,userShips.getIdAccounts() );
            stmt.setInt(2,userShips.getIdShips() );

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void delete(UserShips userShips) throws SQLException {

    }
}
