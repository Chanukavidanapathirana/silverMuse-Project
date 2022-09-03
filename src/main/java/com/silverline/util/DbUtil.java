package com.silverline.util;

import com.silverline.Cabin;
import com.silverline.dao.PassengerDaoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static Connection connection() throws SQLException {

        String url = PropertyUtil.getPropertyObject().getProperty("DB.URL");
        String uname = PropertyUtil.getPropertyObject().getProperty("DB.USER");
        String pass = PropertyUtil.getPropertyObject().getProperty("DB.PASSWORD");
        return DriverManager.getConnection(url,uname,pass);
    }

    public static void setPassenger(Cabin[] cabinRooms, int roomNum, int i) throws SQLException {

        com.silverline.model.Passenger passenger = new com.silverline.model.Passenger();
        passenger.setFirstName(cabinRooms[roomNum - 1].getPassengers()[0].getFirstName());
        passenger.setLastName(cabinRooms[roomNum - 1].getPassengers()[0].getSecondName());
        passenger.setCost(cabinRooms[roomNum - 1].getPassengers()[0].getCostPerCustomer());
        PassengerDaoImpl passengerDao = new PassengerDaoImpl();
        passengerDao.store(passenger, roomNum);
    }

    public static int getPassengerCount(int i) throws SQLException {

        PassengerDaoImpl passengerDao = new PassengerDaoImpl();
        return  passengerDao.passengerCount(i);
    }

    public static String[][] getName(int i) throws SQLException {

        PassengerDaoImpl passengerDao = new PassengerDaoImpl();
        return passengerDao.loadName(i);
    }

    public static double[] getCost(int i) throws SQLException {

        PassengerDaoImpl passengerDao = new PassengerDaoImpl();
        return passengerDao.loadCost(i);
    }

}
