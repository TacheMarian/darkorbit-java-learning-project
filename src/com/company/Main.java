package com.company;

import com.company.entities.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    static Account account;
    static UserInventory userInventory;
    static UserShips userShips;
    static List<String> listOfShips;
    static List<String> listOfAllShips;
    static Ship chosenShip;
    static Laser laser;
    static List<String> listOfAllInfoAboutLasers;
    static List<String> listOfAllLasers;
    static Generator generator;
    static List<String> listOfAllInfoAboutGenerators;
    static List<String> listOfAllGenerators;
    static Equipment equipment;
    static int maxDamage;
    static int shield;


    public static void loginOrRegister(AccountsDAO accountsDAO, UserInventoryDAO userInventoryDAO, UserShipsDAO userShipsDAO) {
        System.out.println("Press L for login or R for register");
        String m = s.nextLine().toLowerCase();
        if (m.equals("l")) {
            System.out.print("Username: ");
            String username = s.nextLine();
            System.out.print("Password: ");
            String password = s.nextLine();
            account = new Account(username, password);
            userInventory = new UserInventory();

            try{
                accountsDAO.get(account);
                if(account.getId()==0){
                    loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO);
                }else{
                    userInventory.setIdUserInventory(account.getId());
                    userInventoryDAO.get(userInventory);
                    System.out.println("Login successful!");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else if(m.equals("r")){
            System.out.print("New Username: ");
            String username = s.nextLine();
            System.out.print("New Password: ");
            String password = s.nextLine();
            Account account = new Account(username, password);
            try{
                List<String> accountsNames = accountsDAO.getAll(account);
                boolean accountExistingAlready = false;
                for(int e = 0; e < accountsNames.size(); e++){
                    if(username.equals(accountsNames.get(e))){
                        accountExistingAlready = true;
                        System.out.println("The name already exists, choose another one");
                        break;
                    }
                }
                if(accountExistingAlready==false){
                    accountsDAO.insert(account);
                    userInventoryDAO.insert(userInventory);
                    accountsDAO.get(account);
                    userShips = new UserShips(account.getId(), 1);
                    userShipsDAO.insert(userShips);
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
            loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO);

        }else {
            System.out.println("Didn't get the right message. Try again");
            loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO);
        }
    }

    public static void chooseShip(UserShipsDAO userShipsDAO, int accountsId, ShipsDAO shipsDAO){
        System.out.println("Choose your ship from the available displayed ships");
        UserShips userShips = new UserShips(accountsId);
        int n = 1;
        try {
            listOfShips = userShipsDAO.getAll(userShips);
            for (String str : userShipsDAO.getAll(userShips)) {
                System.out.println(n +" "+str);
                n++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int m = s.nextInt();
        if(m>0 && m < (listOfShips.size()+1)){
            chosenShip = new Ship(listOfShips.get(m-1));
        }else{
            System.out.println("Wrong value, try again.");
            chooseShip(userShipsDAO, accountsId, shipsDAO);
        }


        switch (chosenShip.getShipName()){
            case "Pheonix":
                chosenShip.setShipId(1);
                break;
            case "Yamato":
                chosenShip.setShipId(2);
                break;
            case "Liberator":
                chosenShip.setShipId(3);
                break;
            case "Leonov":
                chosenShip.setShipId(4);
                break;
            case "Piranha":
                chosenShip.setShipId(5);
                break;
            case "Nostromo":
                chosenShip.setShipId(6);
                break;
            case "Big Boy":
                chosenShip.setShipId(7);
                break;
            case "Vengeance":
                chosenShip.setShipId(8);
                break;
            case "Goliath":
                chosenShip.setShipId(9);
                break;
        }

        try {
            shipsDAO.get(chosenShip);
            System.out.println("Ship name: " + chosenShip.getShipName()
                                + "\nShip hp: " + chosenShip.getShipHp()
                                + "\nShip Laser Slots: " + chosenShip.getShipsLaserSlots()
                                + "\nShip Generator Slots: " + chosenShip.getShipsGenSlots());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void mainMenu(UserShipsDAO userShipsDAO, ShipsDAO shipsDAO, AccountsDAO accountsDAO,
                                LasersDAO lasersDAO, UserInventoryDAO userInventoryDAO,
                                GeneratorsDAO generatorsDAO, EquipmentDAO equipmentDAO){
        boolean running = true;
        while (running) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Shop");
            System.out.println("2. Equipment");
            System.out.println("3. Back to choosing your ship");
            System.out.println("4. Start");
            System.out.println("0. Log out");

            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    shopMenu(shipsDAO, accountsDAO, userShipsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO);
                    break;
                case 2:
                    equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO);
                    break;
                case 3:
                    chooseShip(userShipsDAO, account.getId(), shipsDAO);
                    break;
                case 4:
                    start(shipsDAO, maxDamage, shield);
                    break;
                case 0:
                    running = false;
                    System.out.println("Log out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void shopMenu(ShipsDAO shipsDAO, AccountsDAO accountsDAO, UserShipsDAO userShipsDAO,
                                LasersDAO lasersDAO, UserInventoryDAO userInventoryDAO,
                                GeneratorsDAO generatorsDAO, EquipmentDAO equipmentDAO) {
        System.out.println("Credits: " + account.getCredits() + "\nUridium: " + account.getUridium()+"\n");

        System.out.println("=== Shop Menu ===");
        System.out.println("1. Buy ship");
        System.out.println("2. Buy laser");
        System.out.println("3. Buy generator");
        System.out.println("0. Back");

        int choice = s.nextInt();
        s.nextLine();

        switch (choice) {
            case 1:
                buyShip(shipsDAO, accountsDAO, userShipsDAO);
                break;
            case 2:
                buyLaser(lasersDAO, accountsDAO, userInventoryDAO);
                break;
            case 3:
                buyGenerator(generatorsDAO, accountsDAO, userInventoryDAO, shipsDAO, userShipsDAO);
                break;
            case 0:
                mainMenu(userShipsDAO, shipsDAO, accountsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                shopMenu(shipsDAO, accountsDAO, userShipsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO);
        }
    }

    public static void buyShip(ShipsDAO shipsDAO, AccountsDAO accountsDAO, UserShipsDAO userShipsDAO) {
        int n=1;
        Ship ship = new Ship();
        try{
            listOfAllShips = shipsDAO.getAll(ship);
            List<String> listOfNotOwenedShips = new ArrayList<>();
            for(String str : listOfShips){
                listOfAllShips.remove(str);
            }
            System.out.println("Choose from the available ship: ");
            for(String str1 : listOfAllShips){
                System.out.println(n + " " +str1);
                listOfNotOwenedShips.add(str1);
                n++;
            }
            int m = s.nextInt();
            s.nextLine();
            if(m > 0 && m < (listOfNotOwenedShips.size()+1)){
                ship.setShipName(listOfNotOwenedShips.get(m-1));
            }else{
                System.out.println("No viable value, try again");
                buyShip(shipsDAO,accountsDAO, userShipsDAO);
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
                    return;
                }else if(ship.getShipPriceUridium()!=0 && (ship.getShipPriceUridium()< account.getUridium())){
                    accountsDAO.updateBuyUridium(account, ship.getShipPriceUridium());
                    userShipsDAO.insert(userShips);
                    return;
                }else{
                    System.out.println("You don't have enough currency to afford the selected ship.");
                    return;
                }
            }else if(userAnswer.equals("n")){
                buyShip(shipsDAO, accountsDAO, userShipsDAO);
                return;
            }else {
                System.out.println("Wrong answer, try again");
                buyShip(shipsDAO, accountsDAO, userShipsDAO);
                return;
            }


        }catch (SQLException e){
            System.out.println("Something went wrong in buy ship ");
            e.printStackTrace();
            return;
        }
    }

    public static void buyLaser(LasersDAO lasersDAO, AccountsDAO accountsDAO, UserInventoryDAO userInventoryDAO) {
        int n=1;
        laser = new Laser();
        listOfAllInfoAboutLasers = new ArrayList<>();
        try{
            listOfAllInfoAboutLasers = lasersDAO.getAllInfoAboutAllLasers(laser);
            System.out.println("You have " + account.getCredits() + " credits and " + account.getUridium() + " uridium.");
            System.out.println("Choose from the available laser: ");
            for(String str1 : listOfAllInfoAboutLasers){
                System.out.println(n + " " +str1);
                n++;
            }
            System.out.println("Or press 0 to go back to Shop menu");
            int m = s.nextInt();
            s.nextLine();
            if(m==0){
               return;
            }
            if(m > 0 && m < (listOfAllInfoAboutLasers.size()+1)){
                laser.setIdLasers(m);
            }else {
                System.out.println("No viable value, try again");
                buyLaser(lasersDAO, accountsDAO, userInventoryDAO);
                return;
            }
            lasersDAO.get(laser);
            if(laser.getPriceCredits()==0){
                System.out.println("The price of the laser: " + laser.getName() + " is: " + laser.getPriceUridium() + " uridium.");
            }else if(laser.getPriceUridium()==0){
                System.out.println("The price of the laser: " + laser.getName() + " is: " + laser.getPriceCredits()+ " credits");
            }
            System.out.println("Do you want to buy this laser? (Y/N)");
            String userAnswer = s.nextLine().toLowerCase();

            if(userAnswer.equals("y")){
                if(laser.getPriceCredits()!=0 && (laser.getPriceCredits()< account.getCredits())){
                    accountsDAO.updateBuyCredits(account, laser.getPriceCredits());
                    userInventoryDAO.updateLaser(account.getId(), laser.getName());
                }else if(laser.getPriceUridium()!=0 && (laser.getPriceUridium()< account.getUridium())){
                    accountsDAO.updateBuyUridium(account, laser.getPriceUridium());
                    userInventoryDAO.updateLaser(account.getId(), laser.getName());
                }else{
                    System.out.println("You don't have enough currency to afford the selected laser.");
                }
            }else if(userAnswer.equals("n")){
                buyLaser(lasersDAO, accountsDAO, userInventoryDAO);
                return;
            }else {
                System.out.println("Wrong answer, try again");
                buyLaser(lasersDAO, accountsDAO, userInventoryDAO);
                return;
            }


        }catch (SQLException e){
            System.out.println("Something went wrong in buy ship ");
            e.printStackTrace();
            return;
        }
    }

    public static void buyGenerator(GeneratorsDAO generatorsDAO, AccountsDAO accountsDAO,
                                 UserInventoryDAO userInventoryDAO, ShipsDAO shipsDAO, UserShipsDAO userShipsDAO) {
        int n=1;
        generator = new Generator();
        listOfAllInfoAboutGenerators = new ArrayList<>();
        try{
            listOfAllInfoAboutGenerators = generatorsDAO.InfoAboutAllGenerators(generator);
            System.out.println("You have " + account.getCredits() + " credits and " + account.getUridium() + " uridium.");
            System.out.println("Choose from the available laser: ");
            for(String str1 : listOfAllInfoAboutGenerators){
                System.out.println(n + " " +str1);
                n++;
            }
            System.out.println("Or press 0 to go back to Shop menu");
            int m = s.nextInt();
            s.nextLine();
            if(m==0){
                return;
            }
            if(m > 0 && m < (listOfAllInfoAboutGenerators.size()+1)){
                generator.setIdGenerators(m);
            }else {
                System.out.println("No viable value, try again");
                buyGenerator(generatorsDAO, accountsDAO, userInventoryDAO, shipsDAO, userShipsDAO);
                return;
            }
            generatorsDAO.get(generator);
            if(generator.getPriceCredits()==0){
                System.out.println("The price of the laser: " + generator.getName() + " is: " + generator.getPriceUridium() + " uridium.");
            }else if(laser.getPriceUridium()==0){
                System.out.println("The price of the laser: " + generator.getName() + " is: " + generator.getPriceCredits()+ " credits");
            }
            System.out.println("Do you want to buy this generator? (Y/N)");
            String userAnswer = s.nextLine().toLowerCase();

            if(userAnswer.equals("y")){
                if(generator.getPriceCredits()!=0 && (generator.getPriceCredits()< account.getCredits())){
                    accountsDAO.updateBuyCredits(account, generator.getPriceCredits());
                    userInventoryDAO.updateGenerator(account.getId(), generator.getName());
                }else if(generator.getPriceUridium()!=0 && (generator.getPriceUridium()< account.getUridium())){
                    accountsDAO.updateBuyUridium(account, generator.getPriceUridium());
                    userInventoryDAO.updateGenerator(account.getId(), generator.getName());
                }else{
                    System.out.println("You don't have enough currency to afford the selected laser.");
                }
            }else if(userAnswer.equals("n")){
                buyGenerator(generatorsDAO, accountsDAO, userInventoryDAO, shipsDAO, userShipsDAO);
                return;
            }else {
                System.out.println("Wrong answer, try again");
                buyGenerator(generatorsDAO, accountsDAO, userInventoryDAO, shipsDAO, userShipsDAO);
                return;
            }


        }catch (SQLException e){
            System.out.println("Something went wrong in buy ship ");
            e.printStackTrace();
            return;
        }
    }

    public static void equipmentMenu(UserInventoryDAO userInventoryDAO, LasersDAO lasersDAO,GeneratorsDAO generatorsDAO,
                                 EquipmentDAO equipmentDAO) {
        System.out.println("=== Equipment Menu ===");
        System.out.println("1. Equip laser");
        System.out.println("2. Equip generator");
        System.out.println("0. Back");
        equipment = new Equipment();
        equipment.setIdEquipment(account.getId());
        listOfAllLasers = new ArrayList<>();
        listOfAllGenerators = new ArrayList<>();
        try{
            equipmentDAO.get(equipment);
            listOfAllLasers = lasersDAO.getAllLasers(laser);
            laser = new Laser();
            listOfAllGenerators = generatorsDAO.getAllGenerators(generator);
            generator = new Generator();
        }catch (SQLException e){
            System.out.println("User inventory exception in equipment menu found " + e.getMessage());
        }


        int choice = s.nextInt();
        s.nextLine();

        switch (choice) {
            case 1:
                equipLasers(userInventoryDAO, lasersDAO, equipmentDAO, generatorsDAO);
                break;
            case 2:
                equipGenerators(userInventoryDAO, generatorsDAO, equipmentDAO, lasersDAO);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO);
        }
    }

    public static void equipLasers(UserInventoryDAO userInventoryDAO, LasersDAO lasersDAO, EquipmentDAO equipmentDAO,
                                 GeneratorsDAO generatorsDAO ) {
        try{
            userInventoryDAO.get(userInventory);
        }catch (SQLException e){
            System.out.println("Exception in updating userInventory befor displaying numbers of laser per type" + e.getMessage());
        }
        int damage = 0;
        int freeLaserSlots = chosenShip.getShipsLaserSlots()-equipment.getLf1()
                -equipment.getMp1()-equipment.getLf2()-equipment.getLf3();


        System.out.println("You have " + freeLaserSlots + " free laser slots and your laser from inventory are : "
                + userInventory.getLf1() + " LF1, "
                + userInventory.getMp1() + " MP1, "
                + userInventory.getLf2() + " LF2, "
                + userInventory.getLf3()+ " LF3.");
        System.out.println("\n1. Equip you ship with laserss \n2. Unequip laser from your ship \n0. Exit to equipment menu");
        System.out.println("Your answer : ");
        int answer1 = s.nextInt();


        if(answer1 == 1){
            int n =1;
            System.out.println("Choose one of the types of laser from bellow:");
            for(String str : listOfAllLasers){
                System.out.println(n + ". " + str);
                n++;
            }
            int idOfSelectedLaser = s.nextInt();
            laser.setIdLasers(idOfSelectedLaser);
            while(true){
                if(idOfSelectedLaser > 0 && idOfSelectedLaser<(listOfAllLasers.size()+1)){
                    System.out.println("You have " + freeLaserSlots + " / " + chosenShip.getShipsLaserSlots() +
                            " slots available.");
                    System.out.println("Press 1 to equip one laser of this type or 0 to exit");
                    int answer2 = s.nextInt();
                    if(answer2 == 1 && freeLaserSlots >0 ){
                        try{
                            lasersDAO.get(laser);
                            int numberOfLasersOfThisType = userInventoryDAO.get(account, laser.getName());
                            if(numberOfLasersOfThisType>0){
                                freeLaserSlots--;
                                damage = damage + laser.getDamage();
                                userInventoryDAO.updateLaserEquip(account.getId(), laser.getName());
                                equipmentDAO.updateLaser(account.getId(), laser.getName());
                            }else{
                                System.out.println("You have 0 laser of type: " + laser.getName());
                                break;
                            }
                        }catch (SQLException e){
                            System.out.println("Equipping laser exception "+ e.getMessage());
                        }
                    }else  if(answer2 == 1 && freeLaserSlots ==0){
                        System.out.println("All your laser slots are full, you can't equip any more laser");
                        break;
                    }else  if (answer2 == 0){
                        break;
                    }
                }
            }

        }
        equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO);
        maxDamage = damage;

    }

    public static void equipGenerators(UserInventoryDAO userInventoryDAO, GeneratorsDAO generatorsDAO,
                                      EquipmentDAO equipmentDAO, LasersDAO lasersDAO ) {
        try{
            userInventoryDAO.get(userInventory);
        }catch (SQLException e){
            System.out.println("Exception in updating userInventory befor displaying numbers of generator per type" + e.getMessage());
        }
        int shield1 = 0;
        int freeGeneratorsSlots = chosenShip.getShipsGenSlots()-equipment.getSg3NA01()
                -equipment.getSg3NA02()-equipment.getSg3NA03()-equipment.getSg3NB01()-equipment.getSg3NB02();


        System.out.println("You have " + freeGeneratorsSlots + " free generator slots and your generator from inventory are : "
                + userInventory.getSg3NA01() + " A01, "
                + userInventory.getSg3NA02() + " A02, "
                + userInventory.getSg3NA03() + " A03, "
                + userInventory.getSg3NB01() + " B01."
                + userInventory.getSg3NB02() + " B02, ");
        System.out.println("\n1. Equip you ship with generator \n2. Unequip generator from your ship \n0. Exit to equipment menu");
        System.out.println("Your answer : ");
        int answer1 = s.nextInt();

        if(answer1 == 1){
            int n =1;
            System.out.println("Choose one of the types of generatorsrs from bellow:");
            for(String str : listOfAllGenerators){
                System.out.println(n + ". " + str);
                n++;
            }
            int idOfSelectedGen = s.nextInt();
            generator.setIdGenerators(idOfSelectedGen);
            while(true){
                if(idOfSelectedGen > 0 && idOfSelectedGen<(listOfAllLasers.size()+1)){
                    System.out.println("You have " + freeGeneratorsSlots + " / " + chosenShip.getShipsGenSlots() +
                            " slots available.");
                    System.out.println("Press 1 to equip one laser of this type or 0 to exit");
                    int answer2 = s.nextInt();
                    if(answer2 == 1 && freeGeneratorsSlots >0 ){
                        try{
                            generatorsDAO.get(generator);
                            int numberOfGenOfThisType = userInventoryDAO.get(account, generator.getName());
                            if(numberOfGenOfThisType>0){
                                freeGeneratorsSlots--;
                                shield1 += generator.getShield();
                                userInventoryDAO.updateGeneratorEquip(account.getId(), generator.getName());
                                equipmentDAO.updateGenerator(account.getId(), generator.getName());
                            }else{
                                System.out.println("You have 0 laser of type: " + generator.getName());
                                break;
                            }
                        }catch (SQLException e){
                            System.out.println("Equipping generator exception "+ e.getMessage());
                        }
                    }else  if(answer2 == 1 && freeGeneratorsSlots == 0){
                        System.out.println("All your generator slots are full, you can't equip any more generator");
                        break;
                    }else  if (answer2 == 0){
                        break;
                    }
                }
            }

        }
        equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO);
        shield = shield1;

    }

    public static void unequipLaser() {

    }

    public static void unequipGenerator() {
        System.out.println("Unequipping item...");
    }

    public static void start(ShipsDAO shipsDAO, int damage, int shield){

    }


    public static void main(String[] args) {

        AccountsDAO accountsDAO = new AccountsDAOImpl();
        UserShipsDAO userShipsDAO = new UserShipsImpl();
        ShipsDAO shipsDAO = new ShipsDAOImpl();
        UserInventoryDAO userInventoryDAO = new UserInventoryImpl();
        LasersDAO lasersDAO = new LasersDAOImpl();
        GeneratorsDAO generatorsDAO = new GeneratorsDAOImpl();
        EquipmentDAO equipmentDAO = new EquipmentDAOImpl();

        loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO);
        chooseShip(userShipsDAO, account.getId(), shipsDAO);
        mainMenu(userShipsDAO, shipsDAO, accountsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO);
        System.out.println("Max damage = " + maxDamage);
        System.out.println("Ship shield = " + shield);

    }
}

