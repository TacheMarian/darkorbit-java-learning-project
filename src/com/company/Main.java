package com.company;

import com.company.dao.*;
import com.company.entities.*;
import com.company.services.ShipService;
import com.company.services.ShipServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ShipService shipService;

    static Scanner s = new Scanner(System.in);
    static Account account;
    static UserInventory userInventory;
    static UserShips userShips;
    static List<String> listOfShips;
    static Ship currentShip;
    static Laser laser;
    static List<String> listOfAllInfoAboutLasers;
    static List<String> listOfAllLasers;
    static Generator generator;
    static List<String> listOfAllInfoAboutGenerators;
    static List<String> listOfAllGenerators;
    static Equipment equipment;
    static StarshipStats starshipStats;



    public static void loginOrRegister(AccountsDAO accountsDAO, UserInventoryDAO userInventoryDAO,
                                       UserShipsDAO userShipsDAO, StarshipStatsDAO starshipStatsDAO, EquipmentDAO equipmentDAO) {
        System.out.println("Press L for login or R for register");
        String m = s.nextLine().toLowerCase();
        if (m.equals("l")) {
            System.out.print("Username: ");
            String username = s.nextLine();
            System.out.print("Password: ");
            String password = s.nextLine();
            account = new Account(username, password);
            userInventory = new UserInventory();
            currentShip = new Ship();
            starshipStats = new StarshipStats();
            equipment = new Equipment();

            try{
                accountsDAO.get(account);
                if(account.getId()==0){
                    loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO, starshipStatsDAO, equipmentDAO);
                }else{
                    userInventory.setIdUserInventory(account.getId());
                    userInventoryDAO.get(userInventory);
                    starshipStats.setIdStarshipStats(account.getId());
                    starshipStatsDAO.get(starshipStats);
                    currentShip.setShipName(starshipStats.getStarshipName());
                    equipment.setIdEquipment(account.getId());
                    equipmentDAO.get(equipment);
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
            loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO, starshipStatsDAO, equipmentDAO);

        }else {
            System.out.println("Didn't get the right message. Try again");
            loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO, starshipStatsDAO, equipmentDAO);
        }
    }

    public static void chooseShip(UserShipsDAO userShipsDAO, int accountsId, ShipsDAO shipsDAO, StarshipStatsDAO starshipStatsDAO){
        System.out.println("Choose your ship from the available displayed ships");
        UserShips userShips = new UserShips(accountsId);
        int itterationVarForShipList = 1;
        try {
            listOfShips = userShipsDAO.getAll(userShips);
            for (String str : userShipsDAO.getAll(userShips)) {
                System.out.println(itterationVarForShipList +" "+str);
                itterationVarForShipList++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int userResponseFromShipList = s.nextInt();
        if(userResponseFromShipList>0 && userResponseFromShipList < (listOfShips.size()+1)){
            currentShip = new Ship(listOfShips.get(userResponseFromShipList-1));
        }else{
            System.out.println("Wrong value, try again.");
            chooseShip(userShipsDAO, accountsId, shipsDAO, starshipStatsDAO);
        }


        switch (currentShip.getShipName()){
            case "Pheonix":
                currentShip.setShipId(1);
                break;
            case "Yamato":
                currentShip.setShipId(2);
                break;
            case "Liberator":
                currentShip.setShipId(3);
                break;
            case "Leonov":
                currentShip.setShipId(4);
                break;
            case "Piranha":
                currentShip.setShipId(5);
                break;
            case "Nostromo":
                currentShip.setShipId(6);
                break;
            case "Big Boy":
                currentShip.setShipId(7);
                break;
            case "Vengeance":
                currentShip.setShipId(8);
                break;
            case "Goliath":
                currentShip.setShipId(9);
                break;
        }

        try {
            shipsDAO.get(currentShip);
            System.out.println("Ship name: " + currentShip.getShipName()
                                + "\nShip hp: " + currentShip.getShipHp()
                                + "\nShip Laser Slots: " + currentShip.getShipsLaserSlots()
                                + "\nShip Generator Slots: " + currentShip.getShipsGenSlots());
            starshipStats.setStarshipName(currentShip.getShipName());
            starshipStatsDAO.updateChosenShipName(starshipStats);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void mainMenu(UserShipsDAO userShipsDAO, ShipsDAO shipsDAO, AccountsDAO accountsDAO,
                                LasersDAO lasersDAO, UserInventoryDAO userInventoryDAO,
                                GeneratorsDAO generatorsDAO, EquipmentDAO equipmentDAO, StarshipStatsDAO starshipStatsDAO){
        boolean running = true;

        switch (currentShip.getShipName()){
            case "Pheonix":
                currentShip.setShipId(1);
                break;
            case "Yamato":
                currentShip.setShipId(2);
                break;
            case "Liberator":
                currentShip.setShipId(3);
                break;
            case "Leonov":
                currentShip.setShipId(4);
                break;
            case "Piranha":
                currentShip.setShipId(5);
                break;
            case "Nostromo":
                currentShip.setShipId(6);
                break;
            case "Big Boy":
                currentShip.setShipId(7);
                break;
            case "Vengeance":
                currentShip.setShipId(8);
                break;
            case "Goliath":
                currentShip.setShipId(9);
                break;
        }
        try {
            shipsDAO.get(currentShip);
            System.out.println("Ship name: " + currentShip.getShipName()
                    + "\nShip hp: " + currentShip.getShipHp()
                    + "\nShip Laser Slots: " + currentShip.getShipsLaserSlots()
                    + "\nShip Generator Slots: " + currentShip.getShipsGenSlots());
            starshipStats.setStarshipName(currentShip.getShipName());
            starshipStatsDAO.updateChosenShipName(starshipStats);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (running) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Shop");
            System.out.println("2. Equipment");
            System.out.println("3. Choose another ship");
            System.out.println("4. Start");
            System.out.println("0. Log out");

            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    shopMenu(shipsDAO, accountsDAO, userShipsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO, starshipStatsDAO);
                    break;
                case 2:
                    equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO, starshipStatsDAO);
                    break;
                case 3:
                    chooseShip(userShipsDAO, account.getId(), shipsDAO, starshipStatsDAO);
                    break;

                case 0:
                    running = false;

                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            try{
                starshipStats.setDamage(equipment.getLf1()*40 + equipment.getMp1()*60
                        + equipment.getLf2() * 120 + equipment.getLf3() * 150);
                starshipStatsDAO.updateDamage(starshipStats);
                starshipStats.setShield(equipment.getSg3NA01() * 1000 + equipment.getSg3NA02() * 2000
                        + equipment.getSg3NA03() * 5000 + equipment.getSg3NB01() * 7000 + equipment.getSg3NB02() * 10000);
                starshipStatsDAO.updateShield(starshipStats);
                starshipStats.setHp(currentShip.getShipHp());
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

        }
    }

    public static void shopMenu(ShipsDAO shipsDAO, AccountsDAO accountsDAO, UserShipsDAO userShipsDAO,
                                LasersDAO lasersDAO, UserInventoryDAO userInventoryDAO,
                                GeneratorsDAO generatorsDAO, EquipmentDAO equipmentDAO, StarshipStatsDAO starshipStatsDAO) {
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
                shipService.buyShip(account);
                break;
            case 2:
                buyLaser(lasersDAO, accountsDAO, userInventoryDAO);
                break;
            case 3:
                buyGenerator(generatorsDAO, accountsDAO, userInventoryDAO, shipsDAO, userShipsDAO);
                break;
            case 0:
                mainMenu(userShipsDAO, shipsDAO, accountsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO, starshipStatsDAO);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                shopMenu(shipsDAO, accountsDAO, userShipsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO, starshipStatsDAO);
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
                                 EquipmentDAO equipmentDAO, StarshipStatsDAO starshipStatsDAO) {
        System.out.println("=== Equipment Menu ===");
        System.out.println("1. Lasers");
        System.out.println("2. Generators");
        System.out.println("0. Back");
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
                equipLasers(userInventoryDAO, lasersDAO, equipmentDAO, generatorsDAO, starshipStatsDAO);
                break;
            case 2:
                equipGenerators(userInventoryDAO, generatorsDAO, equipmentDAO, lasersDAO, starshipStatsDAO);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO, starshipStatsDAO);
        }
    }

    public static void equipLasers(UserInventoryDAO userInventoryDAO, LasersDAO lasersDAO, EquipmentDAO equipmentDAO,
                                 GeneratorsDAO generatorsDAO, StarshipStatsDAO starshipStatsDAO ) {
        try{
            userInventoryDAO.get(userInventory);
        }catch (SQLException e){
            System.out.println("Exception in updating userInventory before displaying numbers of laser per type" + e.getMessage());
        }
        int freeLaserSlots = currentShip.getShipsLaserSlots()-equipment.getLf1()
                -equipment.getMp1()-equipment.getLf2()-equipment.getLf3();


        System.out.println("You have " + freeLaserSlots + " free laser slots and your laser from inventory are : "
                + userInventory.getLf1() + " LF1, "
                + userInventory.getMp1() + " MP1, "
                + userInventory.getLf2() + " LF2, "
                + userInventory.getLf3()+ " LF3.");
        System.out.println("\n1. Equip you ship with lasers " +
                "\n2. Unequip laser from your ship " +
                "\n0. Exit to equipment menu");
        System.out.println("Your answer : ");
        int answer1 = s.nextInt();


        if(answer1 == 1){
            int n =1;
            System.out.println("Choose one of the types of laser from bellow:");
            for(String str : listOfAllLasers){
                System.out.println(n + ". " + str);
                n++;
            }
            int idOfSelectedLaser = s.nextInt();//adaugat un if pt verificarea daca exista id in db
            laser.setIdLasers(idOfSelectedLaser);
            while(true){
                if(idOfSelectedLaser > 0 && idOfSelectedLaser<(listOfAllLasers.size()+1)){
                    System.out.println("You have " + freeLaserSlots + " / " + currentShip.getShipsLaserSlots() +
                            " slots available.");
                    System.out.println("Press 1 to equip one laser of this type or 0 to exit");
                    int answer2 = s.nextInt();
                    if(answer2 == 1 && freeLaserSlots >0 ){
                        try{
                            lasersDAO.get(laser);
                            int numberOfLasersOfThisType = userInventoryDAO.get(account, laser.getName());
                            if(numberOfLasersOfThisType>0){
                                freeLaserSlots--;
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

        }else if(answer1 == 2){
            unequipLaser(userInventoryDAO, lasersDAO, equipmentDAO, generatorsDAO, starshipStatsDAO);
        }
        equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO, starshipStatsDAO);


    }

    public static void equipGenerators(UserInventoryDAO userInventoryDAO, GeneratorsDAO generatorsDAO,
                                      EquipmentDAO equipmentDAO, LasersDAO lasersDAO, StarshipStatsDAO starshipStatsDAO ) {
        try{
            userInventoryDAO.get(userInventory);
        }catch (SQLException e){
            System.out.println("Exception in updating userInventory before displaying numbers of generator per type" + e.getMessage());
        }
        int freeGeneratorsSlots = currentShip.getShipsGenSlots()-equipment.getSg3NA01()
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
                if(idOfSelectedGen > 0 && idOfSelectedGen<(listOfAllGenerators.size()+1)){
                    System.out.println("You have " + freeGeneratorsSlots + " / " + currentShip.getShipsGenSlots() +
                            " slots available.");
                    System.out.println("Press 1 to equip one generator of this type or 0 to exit");
                    int answer2 = s.nextInt();
                    if(answer2 == 1 && freeGeneratorsSlots >0 ){
                        try{
                            generatorsDAO.get(generator);
                            int numberOfGenOfThisType = userInventoryDAO.get(account, generator.getName());
                            if(numberOfGenOfThisType>0){
                                freeGeneratorsSlots--;
                                userInventoryDAO.updateGenerator(account.getId(), generator.getName());
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

        }else if(answer1 == 2){
            unequipGenerators(userInventoryDAO, generatorsDAO, equipmentDAO, lasersDAO, starshipStatsDAO);
        }
        equipmentMenu(userInventoryDAO, lasersDAO,generatorsDAO, equipmentDAO, starshipStatsDAO);


    }

    public static void unequipLaser(UserInventoryDAO userInventoryDAO, LasersDAO lasersDAO, EquipmentDAO equipmentDAO,
                                    GeneratorsDAO generatorsDAO, StarshipStatsDAO starshipStatsDAO) {

        int occupiedLaserSlots = equipment.getLf1() +equipment.getMp1()
                +equipment.getLf2()+equipment.getLf3();


        System.out.println("You have " + occupiedLaserSlots + " free laser slots and your lasers from equipment are : "
                + equipment.getLf1() + " LF1, "
                + equipment.getMp1() + " MP1, "
                + equipment.getLf2() + " LF2, "
                + equipment.getLf3()+ " LF3.");
        boolean unEquipLaser = true;
        if(unEquipLaser){
            int n =1;
            System.out.println("Choose one of the types of laser from bellow to unequip :");
            for(String str : listOfAllLasers){
                System.out.println(n + ". " + str);
                n++;
            }
            int idOfSelectedLaser = s.nextInt();
            laser.setIdLasers(idOfSelectedLaser);
            int numberOfLasersOfThisType=0;
            if(idOfSelectedLaser == 1){
                numberOfLasersOfThisType = equipment.getLf1();
            }else if(idOfSelectedLaser == 2){
                numberOfLasersOfThisType = equipment.getMp1();
            }else if(idOfSelectedLaser == 3){
                numberOfLasersOfThisType = equipment.getLf2();
            }else if (idOfSelectedLaser == 4){
                numberOfLasersOfThisType = equipment.getLf3();
            }
            while(unEquipLaser && occupiedLaserSlots>0){
                if(idOfSelectedLaser > 0 && idOfSelectedLaser<(listOfAllLasers.size()+1)){
                    System.out.println("You have " + occupiedLaserSlots + " / " + currentShip.getShipsLaserSlots() +
                            " slots available.");
                    System.out.println("Press 1 to unequip one laser of this type or 0 to exit");
                    int answer2 = s.nextInt();
                    if(answer2 == 1 && occupiedLaserSlots >0 ){
                        try{
                            lasersDAO.get(laser);
                            if(numberOfLasersOfThisType>0) {
                                numberOfLasersOfThisType--;
                                userInventoryDAO.updateLaserUnEquip(account.getId(), laser.getName());
                                equipmentDAO.updateLaserUnEquip(account.getId(), laser.getName());
                                if (numberOfLasersOfThisType == 0) {
                                    System.out.println("You have 0 laser of type: " + laser.getName());
                                    unEquipLaser = false;
                                }
                            }
                        }catch (SQLException e){
                            System.out.println("Equipping laser exception "+ e.getMessage());
                        }
                    }else  if(answer2 == 1 && occupiedLaserSlots == currentShip.getShipsLaserSlots()){
                        System.out.println("All your laser slots are free");
                        unEquipLaser = false;
                    }else  if (answer2 == 0){
                        unEquipLaser = false;
                    }
                }
            }
        }
    }

    public static void unequipGenerators(UserInventoryDAO userInventoryDAO, GeneratorsDAO generatorsDAO,
                                       EquipmentDAO equipmentDAO, LasersDAO lasersDAO, StarshipStatsDAO starshipStatsDAO ) {
        int occupiedGeneratorsSlots = equipment.getSg3NA01() + equipment.getSg3NA02() +
                equipment.getSg3NA03() + equipment.getSg3NB01() + equipment.getSg3NB02();


        System.out.println("You have " + occupiedGeneratorsSlots + " used generator slots and your generators from equipment are : "
                + equipment.getSg3NA01() + " A01, "
                + equipment.getSg3NA02() + " A02, "
                + equipment.getSg3NA03() + " A03, "
                + equipment.getSg3NB01() + " B01, "
                + equipment.getSg3NB02() + " B02.");
        boolean unEquipGen = true;
        if(unEquipGen){
            int n =1;
            System.out.println("Choose one of the types of generator from bellow:");
            for(String str : listOfAllGenerators){
                System.out.println(n + ". " + str);
                n++;
            }
            int idOfSelectedGen = s.nextInt();
            int numberOfGenOfThisType = 0;
            if(idOfSelectedGen == 1){
                numberOfGenOfThisType = equipment.getSg3NA01();
            }else if(idOfSelectedGen == 2){
                numberOfGenOfThisType = equipment.getSg3NA02();
            }else if(idOfSelectedGen == 3){
                numberOfGenOfThisType = equipment.getSg3NA03();
            }else if(idOfSelectedGen == 4){
                numberOfGenOfThisType = equipment.getSg3NB01();
            }else if(idOfSelectedGen == 5){
                numberOfGenOfThisType = equipment.getSg3NB02();
            }
            generator.setIdGenerators(idOfSelectedGen);
            while(unEquipGen && occupiedGeneratorsSlots >0){
                if(idOfSelectedGen > 0 && idOfSelectedGen<(listOfAllGenerators.size()+1)){
                    System.out.println("You have " + occupiedGeneratorsSlots + " / " + currentShip.getShipsGenSlots() +
                            " occupied slots.");
                    System.out.println("Press 1 to unequip one generator of this type or 0 to exit");
                    int answer2 = s.nextInt();
                    if(answer2 == 1){
                        try{
                            generatorsDAO.get(generator);
                            if(numberOfGenOfThisType>0){
                                numberOfGenOfThisType--;
                                userInventoryDAO.updateGeneratorUnEquip(account.getId(), generator.getName());
                                equipmentDAO.updateGeneratorUnEquip(account.getId(), generator.getName());
                                if(numberOfGenOfThisType == 0){
                                    System.out.println("You have 0 generators of type: " + generator.getName());
                                    unEquipGen = false;
                                }
                            }
                        }catch (SQLException e){
                            System.out.println("Equipping generator exception "+ e.getMessage());
                        }
                    }else  if(answer2 == 1 && occupiedGeneratorsSlots == currentShip.getShipsGenSlots()){
                        System.out.println("All your generator slots are free");
                        unEquipGen = false;
                    }else  if (answer2 == 0){
                        unEquipGen = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        AccountsDAO accountsDAO = new AccountsDAOImpl();
        UserShipsDAO userShipsDAO = new UserShipsImpl();
        ShipsDAO shipsDAO = new ShipsDAOImpl();
        UserInventoryDAO userInventoryDAO = new UserInventoryImpl();
        LasersDAO lasersDAO = new LasersDAOImpl();
        GeneratorsDAO generatorsDAO = new GeneratorsDAOImpl();
        EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
        StarshipStatsDAO starshipStatsDAO = new StarshipStatsImpl();

        shipService = new ShipServiceImpl(shipsDAO, accountsDAO, userShipsDAO, s);


        loginOrRegister(accountsDAO, userInventoryDAO, userShipsDAO, starshipStatsDAO, equipmentDAO);
        mainMenu(userShipsDAO, shipsDAO, accountsDAO, lasersDAO, userInventoryDAO, generatorsDAO, equipmentDAO, starshipStatsDAO);
        System.out.println("Ship name : " + starshipStats.getStarshipName());
        System.out.println("Ship hitpoints : " + starshipStats.getHp());
        System.out.println("Max damage : " + starshipStats.getDamage());
        System.out.println("Ship shield : " + starshipStats.getShield());
        System.out.println("Log out...");

    }
}

