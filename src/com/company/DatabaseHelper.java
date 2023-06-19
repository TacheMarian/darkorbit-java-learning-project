package com.company;
import java.sql.*;
import java.util.Scanner;

public class DatabaseHelper {
    private Connection connection;
    public int playerId;

    public DatabaseHelper() {
        // Initialize the database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/darkorbit_accounts",
                    "darkorbitonly", "darkbot");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void login(String username, String password){
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM account WHERE account_name = ? " +
                    "AND account_password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                    System.out.println("Login successful!");
                    playerId = rs.getInt("id_accounts");
                    String name = rs.getString("account_name");
                    String pass = rs.getString("account_password");
                    System.out.println("ID: " + playerId + ", Name: " + name + ", Password: " + pass);

                } else {
                    System.out.println("Invalid username or password.");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void registerUser(String username, String password) {
        // Insert a new user into the database
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO account " +
                    "(account_name, account_password) VALUES (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAvailableShips(int playerId){
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT ships.ShipName FROM account"+
                    " JOIN user_ships ON account.id_accounts = user_ships.id_accounts " +
                    "JOIN ships ON user_ships.id_ships = ships.id_ships " +
                    "WHERE account.id_accounts = ?");
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            int number = 1;
            while (rs.next()){
                String result =rs.getString("ShipName");
                System.out.println(number + " " + result);
                number++;
            }
            System.out.println("Enter the name of the ship you want to choose: ");
            Scanner s = new Scanner(System.in);
            String chosenShipName = s.nextLine();
            stmt = connection.prepareStatement("SELECT ships.ShipHp, ships.ShipsLaserSlots, ships.ShipsGenSlots FROM ships "+
                    "WHERE ships.ShipName = ?");
            stmt.setString(1, chosenShipName);
            rs = stmt.executeQuery();
            if(rs.next()){
                int numberOfLasers = rs.getInt("ShipsLaserSlots");
                int numberOfGen = rs.getInt("ShipsGenSLots");
                int shipHp = rs.getInt("ShipHp");
                System.out.println("Ship Hp : "+ shipHp + ", laser slots : " + numberOfLasers + ", generator slots " + numberOfGen);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void buyShip(int userId, String shipName) {

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT uridium, credits FROM account WHERE id_accounts = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) { // Move the cursor to the first row
                int userUridium = rs.getInt("uridium");
                int userCredits = rs.getInt("credits");
                int shipCostCredits = 0;
                int shipCostUridium = 0;
                if(shipName.equals("Leonov") || shipName.equals("Vengeance") || shipName.equals("Goliath")){
                    stmt = connection.prepareStatement("SELECT ShipPriceUridium FROM ships WHERE ShipName = ?");
                    stmt.setString(1, shipName);
                    rs = stmt.executeQuery();
                    if(rs.next()) { // Move the cursor to the first row
                        shipCostUridium = rs.getInt("ShipPriceUridium");
                    }
                }else {
                    stmt = connection.prepareStatement("SELECT ShipPriceCredits FROM ships WHERE ShipName = ?");
                    stmt.setString(1, shipName);
                    rs = stmt.executeQuery();
                    if(rs.next()) { // Move the cursor to the first row
                        shipCostCredits = rs.getInt("ShipPriceCredits");
                    }
                }
                System.out.println(shipCostCredits);
                System.out.println(shipCostUridium);
                if(shipName.equals("Leonov") || shipName.equals("Vengeance") || shipName.equals("Goliath")){
                    if (userUridium > shipCostUridium){
                        System.out.println("Buyable by uridium");
                    } else{
                        System.out.println("You don't have enough uridium");
                    }
                } else {
                    if(userCredits > shipCostCredits){
                        System.out.println("Buyable by credits");
                    } else{
                        System.out.println("You don't have enough credits");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public void updateScore(int userId, int newScore) {
        // Update the score for the specified user in the database
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET " +
                    "score = ? WHERE id = ?");
            stmt.setInt(1, newScore);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        // Close the database connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
