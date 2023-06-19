package com.company.dao;

import com.company.configs.DatabaseConfig;
import com.company.entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsDAOImpl implements AccountsDAO {
    private Connection connection;

    public AccountsDAOImpl() {
    }

    @Override
    public Account get(Account account) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM accounts WHERE account_name = ? " +
                    "AND account_password = ?");
            stmt.setString(1, account.getAccountName());
            stmt.setString(2, account.getAccountPassword());

            rs = stmt.executeQuery();
            if (rs.next()) {
                int idAccount = rs.getInt("id_accounts");
                String accountName = rs.getString("account_name");
                String accountPass = rs.getString("account_password");
                int exp = rs.getInt("experience");
                int uridium = rs.getInt("uridium");
                int credits = rs.getInt("credits");
                account.setId(idAccount);
                account.setAccountName(accountName);
                account.setAccountPassword(accountPass);
                account.setExperience(exp);
                account.setUridium(uridium);
                account.setCredits(credits);
            } else {
                System.out.println("Invalid username and/or password.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving the account: " + e.getMessage());

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

        return account;
    }

    public List<String> getAll(Account account){
        List<String> listOfAllUserNames = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("SELECT accounts.account_name from accounts;");

            rs = stmt.executeQuery();
            while (rs.next()) {
                String shipName = rs.getString("account_name");
                listOfAllUserNames.add(shipName);
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

        return listOfAllUserNames;
    }

    @Override
    public void insert(Account account) {
        PreparedStatement stmt = null;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("INSERT INTO accounts " +
                    "(account_name, account_password, experience, uridium, credits) VALUES (?, ?, ?, ?, ?)");

            stmt.setString(1, account.getAccountName());
            stmt.setString(2, account.getAccountPassword());
            stmt.setInt(3, 0);
            stmt.setInt(4, 1000);
            stmt.setInt(5, 15000);
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
    public int updateBuyCredits(Account account, int priceInCredits) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("UPDATE darkorbit_accounts.accounts SET " +
                    " credits = credits - ? WHERE id_accounts = ?");
            stmt.setInt(1, priceInCredits);
            stmt.setInt(2, account.getId());
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
    public int updateBuyUridium(Account account, int priceInUridium) {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            connection = DatabaseConfig.getConnection();
            stmt = connection.prepareStatement("UPDATE darkorbit_accounts.accounts SET " +
                    " uridium = uridium - ? WHERE id_accounts = ?");
            stmt.setInt(1, priceInUridium);
            stmt.setInt(2, account.getId());
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
    public void delete(Account account) {

    }
}
