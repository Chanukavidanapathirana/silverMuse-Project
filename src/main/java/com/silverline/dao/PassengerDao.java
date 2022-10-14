package com.silverline.dao;

import com.silverline.model.Passenger;
import java.sql.SQLException;

public interface PassengerDao {

    void store(Passenger passenger, int fk) throws SQLException;
    void delete(int i) throws SQLException;
    int passengerCount(int i) throws SQLException;
    String[][] loadName(int i) throws SQLException;
    double[] loadCost(int i) throws SQLException;

}
