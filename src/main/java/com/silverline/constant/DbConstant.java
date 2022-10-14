package com.silverline.constant;

public class DbConstant {

    public static final String ADD_PASSENGER_SQL = "INSERT INTO passenger(FIRST_NAME, LAST_NAME, COST, CABIN_ID) " +
                                                   "VALUES(?, ?, ?, ?)";
    public static final String DELETE_PASSENGER_SQL = "DELETE FROM passenger WHERE CABIN_ID =";
    public static final String COUNT_PASSENGERS_SQL = "SELECT COUNT(*) FROM passenger WHERE CABIN_ID = ";
    public static final String GET_NAME_SQL = "SELECT FIRST_NAME, LAST_NAME FROM passenger WHERE CABIN_ID =";
    public static final String GET_COST_SQL = "SELECT COST FROM passenger WHERE CABIN_ID = ";
}
