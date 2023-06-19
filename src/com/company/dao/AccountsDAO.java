package com.company.dao;

import com.company.entities.Account;

import java.sql.SQLException;
        import java.util.List;


public interface AccountsDAO {

    Account get(Account account) throws SQLException;

    List<String> getAll(Account account)throws SQLException;

    int updateBuyCredits(Account account, int priceInCredits) throws SQLException ;

    int updateBuyUridium(Account account, int priceInUridium) throws SQLException ;

    void insert(Account account) throws SQLException ;

    void delete(Account account) throws SQLException;
}