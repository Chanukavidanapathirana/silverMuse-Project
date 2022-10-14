package com.silverline.util;

import com.silverline.Cabin;
import com.silverline.Passenger;
import com.silverline.constant.Constants;
import com.silverline.dao.PassengerDaoImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Utils {

    public static String getUserInput(String banner) {

        System.out.println(banner);
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    public static double getUserDoubleInput(String banner) {

        System.out.println(banner);
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }

    public static int getUserIntInput(String banner){

        System.out.println(banner);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    /***
     *
     * @param cabinRooms - Cabin room array.
     * @return true if one of the cabin is empty. otherwise, return false.
     */
    public static boolean isCabinsFull(Cabin[] cabinRooms) {

        for (int i = 0; i < Constants.CABINS_COUNT; i++) {
            if (cabinRooms[i].isCabinEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static String enterFirstName(int i) {

        if (i > 0) {
            return getUserInput(String.format("Passenger %s first name:- ", i + 1));
        } else {
            return getUserInput("Passenger first name:- ");
        }
    }

    public static String enterSurName(int i) {

        if (i > 0) {
            return getUserInput(String.format("Passenger %s surname:- ", i + 1));
        } else {
            return getUserInput("Passenger surname:- ");
        }
    }

    public static double enterCostForCabin(int i) {

        if (i > 0) {
            return getUserDoubleInput(String.format("Passenger %s cost:- ", i + 1));
        } else {
            return getUserDoubleInput("Passenger cost:- ");
        }
    }

    public static int getCabinNo() {

        return getUserIntInput(String.format("Enter cabin number (1-%s) or %s to stop:", Constants.CABINS_COUNT, Constants.CABINS_COUNT + 1));
    }

    public static String  askingForAdd(int i) {

        return getUserInput(String.format("If you want to add another passenger, press 'y'. If not, press any key(Passenger %s) :- ", i).toUpperCase());
    }

    /***
     * Programme main menu.
     * @param cabinRooms - cabin array.
     */
    public static void menu(Cabin[] cabinRooms) throws SQLException {

        while (true) {
            System.out.println();
            System.out.println(" = = = = = Menu = = = = = ");
            System.out.println("Enter 'A' to add customer to the cabin");
            System.out.println("Enter 'V' to view all cabins");
            System.out.println("Enter 'E' to view empty cabins");
            System.out.println("Enter 'D' to delete a customer from cabin");
            System.out.println("Enter 'F' to find cabin from customer name");
            System.out.println("Enter 'L' to load data from database");
            System.out.println("Enter 'T' to get expenses for a passenger and all the passengers total cost");
            System.out.println("Enter 'Q' to quite from the program");
            String input = Utils.getUserInput("Enter  your input :- ");
            switch (input) {
                case "A":
                    addPassengers(cabinRooms);
                    break;
                case "V":
                    for (int i = 0; i < Constants.CABINS_COUNT; i++) {
                        if (cabinRooms[i].isCabinEmpty()) {
                            System.out.println("cabin " + (i + 1) + " is empty");
                        } else {
                            System.out.println(String.format("Cabin %s occupied by %s , %s , %s",(i + 1),
                                    cabinRooms[i].getPassengers()[0].getFullName(),
                                    cabinRooms[i].getPassengers()[1].getFullName(),
                                    cabinRooms[i].getPassengers()[2].getFullName()));
                        }
                    }
                    break;
                case "E":
                    for (int i = 0; i < Constants.CABINS_COUNT; i++) {
                        if(cabinRooms[i].isCabinEmpty()) {
                            System.out.println(String.format("Cabin no %s is empty",(i + 1)));
                        }
                    }
                    break;
                case "D":
                    int i = Utils.getUserIntInput("Enter the cabin number to delete the passengers :- ");
                    cabinRooms[i - 1].deletePassengers();
                    PassengerDaoImpl passengerDa = new PassengerDaoImpl();
                    passengerDa.delete(i);
                    break;
                case "F":
                    String name = Utils.getUserInput("Enter passenger first name to find the cabin :- ");
                    for (int l = 0; l < Constants.CABINS_COUNT; l++) {
                        cabinRooms[l].findPassenger(name, l);
                    }
                    break;
                case "L":
                    loadCabinDetails(cabinRooms);
                    break;
                case "T":
                    cost(cabinRooms);
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("***Please enter a valid input***");
            }
            if ("Q".equals(input)) {
                break;
            }
        }
    }

    public static void addPassengers(Cabin[] cabinRooms) throws SQLException {

        while (true) {
            if(!(Utils.isCabinsFull(cabinRooms))) {
                System.out.println("Sorry!!! All cabins are full.");
                break;
            } else {
                int roomNum = Utils.getCabinNo();
                if (roomNum == Constants.CABINS_COUNT + 1) {
                    break;
                } else if (roomNum >= 1 && roomNum < Constants.CABINS_COUNT + 1) {
                 checkCabin(roomNum, cabinRooms);
                } else {
                    System.out.println("* * * * * Please enter a valid input * * * * * ");
                }
            }
        }
    }

    /***
     * Loading cabin data from Database.
     */
    public static void loadCabinDetails(Cabin cabinRooms[]) throws SQLException {

        for(int i = 1; i < Constants.CABINS_COUNT + 1 ; i++) {
            if(DbUtil.getPassengerCount(i) > 0) {
                for(int j = 0; j < DbUtil.getPassengerCount(i); j++ ) {
                    cabinRooms[i - 1].getPassengers()[j].setFirstName(DbUtil.getName(i)[j][0]);
                    cabinRooms[i - 1].getPassengers()[j].setSecondName(DbUtil.getName(i)[j][1]);
                    cabinRooms[i - 1].getPassengers()[j].setCostPerCustomer(DbUtil.getCost(i)[0]);
                }
            }
        }
        System.out.println("Loaded successfully!");
    }

    /***
     * This method gives cost for one passenger and full cost for all passengers.
     * @param cabinRooms array cabin.
     */
    public static void cost(Cabin cabinRooms[]) {

        String name = Utils.getUserInput("Enter the customer's first name to find the bill amount :-");
        for (int i = 0; i < Constants.CABINS_COUNT; i++) {
            for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                if (cabinRooms[i].getPassengers()[j].getFirstName().equals(name)) {
                    System.out.println(String.format("%s (room no %s ) cost is %s",
                            cabinRooms[i].getPassengers()[j].getFirstName(), (i + 1),
                            cabinRooms[i].getPassengers()[j].getCostPerCustomer()));
                }
            }
        }
        System.out.println("* if you didn't get the cost according to the customer first name," +
                " please reenter the correct first name * ");
        double fullTotal = 0;
        for (Cabin cabinRoom : cabinRooms) {
            for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                fullTotal = fullTotal + cabinRoom.getPassengers()[j].getCostPerCustomer();
            }
        }
        System.out.println(String.format("Total bill of all passengers:- %s", fullTotal));
    }

    public static void checkCabin(int roomNum, Cabin cabinRooms[]) throws SQLException {

        if(DbUtil.getPassengerCount(roomNum) > 0) {
            String input = getUserInput("That cabin owned by a passenger.If you want to remove that passenger " +
                    "press 'Y'");
            if ("Y".equals(input)) {
                PassengerDaoImpl passengerDa = new PassengerDaoImpl();
                passengerDa.delete(roomNum);
                setPassengers(roomNum, cabinRooms );
            }
        } else {
            setPassengers(roomNum, cabinRooms );
        }
    }
     public static void setPassengers(int roomNum, Cabin cabinRooms[]) throws SQLException {
         System.out.println(String.format("****** Enter passengers names for room %s ****** ", roomNum));
         //set customer1
         cabinRooms[roomNum - 1].getPassengers()[0] = new Passenger(Utils.enterFirstName(0),
                 Utils.enterSurName(0), Utils.enterCostForCabin(0), roomNum );
         DbUtil.setPassenger(cabinRooms, roomNum, 0);
         for(int i = 1; i < Constants.PASSENGERS_IN_A_CABIN; i++) {
             if ("Y".equals(Utils.askingForAdd(i + 1))) {
                 cabinRooms[roomNum - 1].getPassengers()[i] = new Passenger(Utils.enterFirstName(i),
                         Utils.enterSurName(i), Utils.enterCostForCabin(i), roomNum);
                 DbUtil.setPassenger(cabinRooms, roomNum, i);
             }
         }
     }
}
