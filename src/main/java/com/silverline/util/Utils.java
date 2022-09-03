package com.silverline.util;
import com.silverline.Cabin;
import com.silverline.constant.Constants;

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
     * @return true if on of the cabin is empty. otherwise, return false.
     */
    //if this method name valid?
    public static boolean isCabinsFull(Cabin[] cabinRooms) {

        for (int i = 0; i < Constants.CABINS_COUNT; i++) {
            if (cabinRooms[i].isCabinEmpty()){
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
//todo change Method names
    public static int getCabinNo() {

        return getUserIntInput(String.format("Enter cabin number (1-%s) or %s to stop:", Constants.CABINS_COUNT, Constants.CABINS_COUNT + 1));
    }

    public static String  askingForAdd(int i) {

        return getUserInput(String.format("If you want to add another passenger, press 'y'. If not, press any key(Passenger %s) :- ", i).toUpperCase());
    }

}
