package com.silverline.dao;

import com.silverline.model.Passenger;
import java.sql.SQLException;

public interface PassengerDao {

    public void store(Passenger passenger, int fk) throws SQLException;
    public void delete(int i) throws SQLException;
    public int passengerCount(int i) throws SQLException;
    public String[][] loadName(int i) throws SQLException;
    public double[] loadCost(int i) throws SQLException;

}
