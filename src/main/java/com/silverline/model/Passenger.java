package com.silverline.model;

public class Passenger {

    private String firstName;
    private String lastName;
    private double cost;
    private int cabinId;

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public double getCost() {

        return cost;
    }

    public void setCost(double cost) {

        this.cost = cost;
    }

    public int getCabinId() {

        return cabinId;
    }

    public void setCabinId(int cabinId) {

        this.cabinId = cabinId;
    }
}
