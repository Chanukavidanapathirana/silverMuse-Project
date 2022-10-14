package com.silverline;

import com.silverline.constant.Constants;
import com.silverline.util.Utils;

public class Main {

    public static void main( String[] args) throws Exception {

        Cabin[] cabinRooms = new Cabin[Constants.CABINS_COUNT];//Cabin array objects
        initialise(cabinRooms);
        Utils.menu(cabinRooms);
    }

    private static void initialise(Cabin cabinRooms[]) {

        for(int i = 0; i < Constants.CABINS_COUNT; i++) {
            cabinRooms[i] = new Cabin();
            for(int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                cabinRooms[i].getPassengers()[j] = new Passenger();
            }
        }
        System.out.println("initialised");
    }
}
