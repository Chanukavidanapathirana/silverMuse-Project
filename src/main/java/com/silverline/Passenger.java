package com.silverline;

import java.sql.SQLException;

public class Passenger {
    private String constant = "empty";
    private String firstName = constant;
    private String secondName = constant;
    private String fullName = "empty";
    //TODO full name.
    private double costPerCustomer;

    private int primaryKey = 1;

    public Passenger() {

        this.firstName = constant;
        this.secondName = constant;
        this.fullName = constant;
        this.costPerCustomer = 0;
    }

    public Passenger(String firstName, String secondName, double costPerCustomer, int cabinNo) throws SQLException {

        this.firstName = firstName;
        this.secondName = secondName;
        this.fullName = firstName + " " + secondName;
        this.costPerCustomer = costPerCustomer;

        Utils.storeInDatabase( firstName, secondName, costPerCustomer, cabinNo);
        this.primaryKey++;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getSecondName() {

        return secondName;
    }

    public String getFullName() {
        if("empty".equals(firstName)){
            return "empty";
        }

        return firstName+ " " + secondName;
    }

    public double getCostPerCustomer() {

        return costPerCustomer;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {

        this.secondName = secondName;
    }

    public void setFullName(String firstName, String secondName) {

        this.fullName = firstName + " "+ secondName;
    }

    public void setCostPerCustomer(double costPerCustomer) {

        this.costPerCustomer = costPerCustomer;
    }
}
