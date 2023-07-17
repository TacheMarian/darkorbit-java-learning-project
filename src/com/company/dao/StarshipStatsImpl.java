package com.company.dao;

import com.company.configs.DatabaseConfig;
import com.company.entities.Account;
import com.company.entities.StarshipStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StarshipStatsImpl implements StarshipStatsDAO{
    private Connection connection;



    @Override
    public StarshipStats get(StarshipStats starshipStats) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM starship_stats WHERE id_starship_stats = ?");
            stmt.setInt(1, starshipStats.getIdStarshipStats());


            rs = stmt.executeQuery();
            if (rs.next()) {
                String starShipName = rs.getString("starship_name");
                int damage = rs.getInt("damage");
                int hp = rs.getInt("hp");
                starshipStats.setStarshipName(starShipName);
                starshipStats.setDamage(damage);
                starshipStats.setHp(hp);
            } else {
                System.out.println("Invalid info for starship stats.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the starship stats: " + e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
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

        return starshipStats;
    }

    public List<String> getAll(StarshipStats starshipStats){
        return null;
    }

    @Override
    public void insert(StarshipStats starshipStats) {
        PreparedStatement stmt = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("INSERT INTO starship_stats " +
                    "(starship_name, damage, shield, hp) VALUES (Pheonix, 0, 0, 0, 0)");
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
    public int updateChosenShipName(StarshipStats starshipStats) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("UPDATE `darkorbit_accounts`.`starship_stats` SET `starship_name` = ? WHERE (`id_starship_stats` = ?);");
            stmt.setString(1, starshipStats.getStarshipName());
            stmt.setInt(2, starshipStats.getIdStarshipStats());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the account: " + e.getMessage());

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
    public int updateDamage(StarshipStats starshipStats) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("UPDATE `darkorbit_accounts`.`starship_stats` SET `damage` = ? WHERE (`id_starship_stats` = ?);");
            stmt.setInt(1, starshipStats.getDamage());
            stmt.setInt(2, starshipStats.getIdStarshipStats());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the account: " + e.getMessage());

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
    public int updateShield(StarshipStats starshipStats) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("UPDATE `darkorbit_accounts`.`starship_stats` SET `shield` = ? WHERE (`id_starship_stats` = ?);");
            stmt.setInt(1, starshipStats.getShield());
            stmt.setInt(2, starshipStats.getIdStarshipStats());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the account: " + e.getMessage());

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
    public int updateHp(StarshipStats starshipStats) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("UPDATE `darkorbit_accounts`.`starship_stats` SET `hp` = ? WHERE (`id_starship_stats` = ?);");
            stmt.setInt(1, starshipStats.getHp());
            stmt.setInt(2, starshipStats.getIdStarshipStats());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("An error occurred while updating the account: " + e.getMessage());

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
    public void delete(StarshipStats starshipStats) {

    }
}
