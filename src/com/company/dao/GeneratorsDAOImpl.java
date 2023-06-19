package com.company.dao;

import com.company.configs.DatabaseConfig;
import com.company.entities.Generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneratorsDAOImpl implements GeneratorsDAO {

    public GeneratorsDAOImpl() {
    }

    private Connection connection;

    @Override
    public Generator get(Generator generator) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM generators WHERE generators.id_generators = ?;");
            stmt.setInt(1, generator.getIdGenerators());


            rs = stmt.executeQuery();
            if (rs.next()) {

                int idGen = rs.getInt("id_generators");
                String name = rs.getString("name");
                int shield = rs.getInt("shield");
                int priceCredits = rs.getInt("priceCredits");
                int priceUridium = rs.getInt("priceUridium");

                generator.setIdGenerators(idGen);
                generator.setName(name);
                generator.setShield(shield);
                generator.setPriceCredits(priceCredits);
                generator.setPriceUridium(priceUridium);
            } else {
                System.out.println("Generator info problem");
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

        return generator;
    }

    @Override
    public List<String> InfoAboutAllGenerators(Generator generator) throws SQLException {
        List<String> listOfAllGenerators = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM darkorbit_accounts.generators;");

            rs = stmt.executeQuery();
            while (rs.next()) {
                String genName = rs.getString("name");
                int shield = rs.getInt("shield");
                int priceCredits = rs.getInt("priceCredits");
                int priceUridium = rs.getInt("priceUridium");
                String resultSet = "";
                if(priceUridium==0){
                    resultSet = "Generator name: "+ genName + "; Shield: " + shield + "; Price: " + priceCredits + " credits";
                }else if(priceCredits == 0){
                    resultSet = "Generator name: "+ genName + "; Shield: " + shield + "; Price: " + priceUridium + " uridium";
                }
                listOfAllGenerators.add(resultSet);
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

        return listOfAllGenerators;
    }

    @Override
    public List<String> getAllGenerators(Generator generator) throws SQLException {
        List<String> listOfAllGen = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM darkorbit_accounts.generators;");

            rs = stmt.executeQuery();
            while (rs.next()) {
                String generatorName = rs.getString("name");
                listOfAllGen.add(generatorName);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the list of generator: " + e.getMessage());

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

        return listOfAllGen;
    }

    @Override
    public int updateBuyGeneratorsCredits(Generator generator, int priceInCredits) throws SQLException {
        return 0;
    }

    @Override
    public int updateBuyGeneratorsUridium(Generator generator, int priceInUridium) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Generator generator) throws SQLException {

    }

    @Override
    public void delete(Generator generator) throws SQLException {

    }
}
