package com.company.dao;

import com.company.configs.DatabaseConfig;
import com.company.entities.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO{

    private Connection connection;

    public EquipmentDAOImpl() {
    }

    @Override
    public Equipment get(Equipment equipment) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM " +
                    "darkorbit_accounts.equipment WHERE id_user_equipment = ?");
            stmt.setInt(1, equipment.getIdEquipment());

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
                equipment.setLf1(lf1);
                equipment.setMp1(mp1);
                equipment.setLf2(lf2);
                equipment.setLf3(lf3);
                equipment.setSg3NA01(sg3NA01);
                equipment.setSg3NA02(sg3NA02);
                equipment.setSg3NA03(sg3NA03);
                equipment.setSg3NB01(sg3NB01);
                equipment.setSg3NB02(sg3NB02);
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

        return equipment;
    }


    @Override
    public List<String> getAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(Equipment equipment){
        PreparedStatement stmt = null;

        try {
            connection = DatabaseConfig.getConnection();
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
            connection = DatabaseConfig.getConnection();
            String sql = "UPDATE darkorbit_accounts.equipment SET " +
                    laserName + " = " + laserName + " + 1 WHERE id_user_equipment = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the laser from equipment: " + e.getMessage());

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
            connection = DatabaseConfig.getConnection();
            String sql = "UPDATE darkorbit_accounts.equipment SET " +
                    laserName + " = " + laserName + " - 1 WHERE id_user_equipment = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the laser from equipment: " + e.getMessage());

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
            connection = DatabaseConfig.getConnection();
            String sql = "UPDATE darkorbit_accounts.equipment SET " +
                    generatorName + " = " + generatorName + " + 1 WHERE id_user_equipment = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the generator from equipment: " + e.getMessage());

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
            connection = DatabaseConfig.getConnection();
            String sql = "UPDATE darkorbit_accounts.equipment SET " +
                    generatorName + " = " + generatorName + " - 1 WHERE id_user_equipment = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, accountsId);
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the generator from equipment: " + e.getMessage());

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
    public void delete(Equipment equipment) throws SQLException {

    }
}
