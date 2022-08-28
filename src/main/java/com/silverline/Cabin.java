package com.silverline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cabin {
    //todo change this
    private Passenger [] passengers = new Passenger[Constants.PASSENGERS_IN_A_CABIN];

    public Passenger[] getPassengers() {

        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {

        this.passengers = passengers;
    }

    /***
     *
     * @return true if all cabin is empty. Otherwise, return false
     */
    public boolean isCabinEmpty() {

        for(int i  = 0; i < Constants.PASSENGERS_IN_A_CABIN; i++) {
            if (!"empty".equals(getPassengers()[0].getFirstName())) {
                return false;
            }
        }
        return true;
    }

    public void findPassenger(String name, int i) {

        for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN ; j++) {
            if (getPassengers()[j].getFirstName().equals(name)) {
                System.out.println(String.format("%s is in cabin number %s",
                        getPassengers()[j].getFirstName(), (i + 1)));
            }
        }
    }

    public void deletePassengers() {

        for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
            getPassengers()[j] = new Passenger();
        }

    }



}
