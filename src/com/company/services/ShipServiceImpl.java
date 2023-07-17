package com.company.services;

import com.company.dao.AccountsDAO;
import com.company.dao.ShipsDAO;
import com.company.dao.UserShipsDAO;
import com.company.entities.Account;
import com.company.entities.Ship;
import com.company.entities.UserShips;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShipServiceImpl implements ShipService {
    private ShipsDAO shipsDAO;
    private AccountsDAO accountsDAO;
    private UserShipsDAO userShipsDAO;

    private Scanner s;

    public ShipServiceImpl(ShipsDAO shipsDAO, AccountsDAO accountsDAO, UserShipsDAO userShipsDAO, Scanner scanner) {
        this.shipsDAO = shipsDAO;
        this.accountsDAO = accountsDAO;
        this.userShipsDAO = userShipsDAO;

        this.s = scanner;
    }

    @Override
    public void buyShip(Account account) {
        int shipOptionNumber=1;
        Ship ship = new Ship();
        try{
            List<String> listOfAllShips = shipsDAO.getAll(ship);
            List<String> listOfShips = userShipsDAO.getAllByAccountId(account.getId());
            List<String> listOfNotOwenedShips = new ArrayList<>();

            for(String str : listOfShips){
                listOfAllShips.remove(str);
            }
            System.out.println("Choose from the available ship: ");
            for(String str1 : listOfAllShips){
                System.out.println(shipOptionNumber + ". " +str1);
                listOfNotOwenedShips.add(str1);
                shipOptionNumber++;
            }
            System.out.println("0. Back");
            int userSelectedOption = s.nextInt();
            s.nextLine();
            if(userSelectedOption > 0 && userSelectedOption < (listOfNotOwenedShips.size()+1)){
                ship.setShipName(listOfNotOwenedShips.get(userSelectedOption-1));
            }else if(userSelectedOption == 0){return;}
            else{
                System.out.println("No viable value, try again");
                buyShip(account);
                return;
            }
            switch (ship.getShipName()){
                case "Pheonix":
                    ship.setShipId(1);
                    break;
                case "Yamato":
                    ship.setShipId(2);
                    break;
                case "Liberator":
                    ship.setShipId(3);
                    break;
                case "Leonov":
                    ship.setShipId(4);
                    break;
                case "Piranha":
                    ship.setShipId(5);
                    break;
                case "Nostromo":
                    ship.setShipId(6);
                    break;
                case "Big Boy":
                    ship.setShipId(7);
                    break;
                case "Vengeance":
                    ship.setShipId(8);
                    break;
                case "Goliath":
                    ship.setShipId(9);
                    break;
            }
            System.out.println("You have " + account.getCredits() + " credits and " + account.getUridium() + " uridium.");
            shipsDAO.get(ship);
            if(ship.getShipPriceCredits()==0){
                System.out.println("The price of the ship " + ship.getShipName() + " is " + ship.getShipPriceUridium()+ " uridium.");
            }else if(ship.getShipPriceUridium()==0){
                System.out.println("The price of the ship " + ship.getShipName() + " is " + ship.getShipPriceCredits()+ " credits");
            }else if(ship.getShipPriceCredits()==0 && ship.getShipPriceUridium()==0){
                System.out.println("The price of the ship " + ship.getShipName() + " is " + ship.getShipPriceUridium()
                        + " uridium or " + ship.getShipPriceCredits() + " credits");
            }
            UserShips userShips = new UserShips(account.getId(), ship.getShipId());
            System.out.println("Do you want to buy this ship? (Y/N)");
            String userAnswer = s.nextLine().toLowerCase();

            if(userAnswer.equals("y")){
                if(ship.getShipPriceCredits()!=0 && (ship.getShipPriceCredits()< account.getCredits())){
                    accountsDAO.updateBuyCredits(account, ship.getShipPriceCredits());
                    userShipsDAO.insert(userShips);

                }else if(ship.getShipPriceUridium()!=0 && (ship.getShipPriceUridium()< account.getUridium())){
                    accountsDAO.updateBuyUridium(account, ship.getShipPriceUridium());
                    userShipsDAO.insert(userShips);
                    return;
                }else{
                    System.out.println("You don't have enough currency to afford the selected ship.");
                    return;
                }
            }else if(userAnswer.equals("n")){
                buyShip(account);
                return;
            }else {
                System.out.println("Wrong answer, try again");
                buyShip(account);
                return;
            }


        }catch (SQLException e){
            System.out.println("Something went wrong in buy ship ");
            e.printStackTrace();
            return;
        }
    }
}
