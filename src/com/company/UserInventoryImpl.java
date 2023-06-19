package com.company;

import com.company.entities.Account;
import com.company.entities.UserInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserInventoryImpl implements UserInventoryDAO {

    private Connection connection;

    public UserInventoryImpl() {
    }

    @Override
    public UserInventory get(UserInventory userInventory) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM " +
                    "darkorbit_accounts.user_inventory WHERE id_user_inventory = ?");
            stmt.setInt(1, userInventory.getIdUserInventory());

            rs = stmt.executeQuery();
            if (rs.next()) {
                int lf1 = rs.getInt("lf1");
                int mp1 = rs.getInt("mp1");
                int lf2 = rs.getInt("lf2");
                int lf3 = rs.getInt("lf3");
                int sg3NA01 = rs.getInt("A01");
                int sg3NA02 = rs.getInt("A02");
                int sg3NA03 = rs.getInt("A03");
                int sg3NB01 = rs.getInt("B01");
                int sg3NB02 = rs.getInt("B02");
                userInventory.setLf1(lf1);
                userInventory.setMp1(mp1);
                userInventory.setLf2(lf2);
                userInventory.setLf3(lf3);
                userInventory.setSg3NA01(sg3NA01);
                userInventory.setSg3NA02(sg3NA02);
                userInventory.setSg3NA03(sg3NA03);
                userInventory.setSg3NB01(sg3NB01);
                userInventory.setSg3NB02(sg3NB02);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userInventory;
    }

    @Override
    public int get(Account account, String name) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int returned = 0;

        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement("SELECT " + name+ " FROM " +
                    "darkorbit_accounts.user_inventory WHERE id_user_inventory = " + account.getId());


            rs = stmt.executeQuery();
            if (rs.next()) {
                returned = rs.getInt(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return returned;
    }

    @Override
    public List<String> getAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(UserInventory userInventory){
        PreparedStatement stmt = null;

        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement("INSERT INTO user_inventory " +
                    "(lf1, mp1, lf2, lf3, A01, A02, A03, B01, B02) VALUES (1, 1, 0, 0, 1, 1, 0, 0, 0)");

            stmt.executeUpdate();
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
    }

    @Override
    public int updateLaser(int accountsId, String laserName) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = Database.getConnection();
            String sql = "UPDATE darkorbit_accounts.user_inventory SET " +
                    laserName + " = " + laserName + " + 1 WHERE id_user_inventory = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the laser from inventory: " + e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources: " + e.getMessage());

            }
        }

        return result;
    }

    @Override
    public int updateLaserEquip(int accountsId, String laserName) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = Database.getConnection();
            String sql = "UPDATE darkorbit_accounts.user_inventory SET " +
                    laserName + " = " + laserName + " - 1 WHERE id_user_inventory = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the laser from inventory: " + e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources: " + e.getMessage());

            }
        }

        return result;
    }

    @Override
    public int updateGenerator(int accountsId, String generatorName) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = Database.getConnection();
            String sql = "UPDATE darkorbit_accounts.user_inventory SET " +
                    generatorName + " = " + generatorName + " + 1 WHERE id_user_inventory = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the generator from inventory: " + e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources: " + e.getMessage());

            }
        }

        return result;
    }

    @Override
    public int updateGeneratorEquip(int accountsId, String generatorName) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = Database.getConnection();
            String sql = "UPDATE darkorbit_accounts.user_inventory SET " +
                    generatorName + " = " + generatorName + " - 1 WHERE id_user_inventory = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the generator from inventory: " + e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("An error occurred while closing the resources: " + e.getMessage());

            }
        }

        return result;
    }

    @Override
    public void delete(UserInventory userInventory) throws SQLException {

    }
}
