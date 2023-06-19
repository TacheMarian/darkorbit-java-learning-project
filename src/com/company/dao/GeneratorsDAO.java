package com.company.dao;

import com.company.entities.Generator;

import java.sql.SQLException;
import java.util.List;

public interface GeneratorsDAO {

    Generator get(Generator generator) throws SQLException;

    List<String> InfoAboutAllGenerators(Generator generator)throws SQLException;

    List<String> getAllGenerators(Generator generator)throws SQLException;

    int updateBuyGeneratorsCredits(Generator generator, int priceInCredits) throws SQLException ;

    int updateBuyGeneratorsUridium(Generator generator, int priceInUridium) throws SQLException ;

    void insert(Generator generator) throws SQLException ;

    void delete(Generator generator) throws SQLException;
}
