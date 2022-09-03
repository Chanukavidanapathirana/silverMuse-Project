package com.silverline.dao;

import com.silverline.constant.DbConstant;
import com.silverline.model.Passenger;
import com.silverline.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassengerDaoImpl implements PassengerDao {

    @Override
    public void store(Passenger passenger, int fk) throws SQLException {

        String que1 = DbConstant.ADD_PASSENGER_SQL;
        Connection con = DbUtil.connection();
        PreparedStatement st = con.prepareStatement(que1);// preparedStatement
        st.setString(1, passenger.getFirstName());
        st.setString(2, passenger.getLastName());
        st.setDouble(3, passenger.getCost());
        st.setInt(4, fk);
        int count = st.executeUpdate();
        System.out.println(count+ "row/s affected");
        st.close();
        con.close();
    }

    @Override
    public void delete(int i) throws SQLException {

        Connection con = DbUtil.connection();
        String query = DbConstant.DELETE_PASSENGER_SQL+ i ;
        PreparedStatement st = con.prepareStatement(query);
        int count = st.executeUpdate();
        System.out.println(count + "row/s affected");
        st.close();
        con.close();
    }

    @Override
    public int passengerCount(int i) throws SQLException {

        Connection con = DbUtil.connection();
        String query = DbConstant.COUNT_PASSENGERS_SQL+ i ;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return  rs.getInt("COUNT(*)");
    }

    @Override
    public String[][] loadName(int i) throws SQLException {


        String[][] array = new String[passengerCount(i)][2];
        Connection con = DbUtil.connection();
        String query = DbConstant.GET_NAME_SQL+ i ;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        for (int j = 0; j < passengerCount(i); j++) {
            rs.next();
            array[j][0] = rs.getString("First_Name");
            array[j][1] = rs.getString("Last_name");
        }
        return array;
    }

    @Override
    public double[] loadCost(int i) throws SQLException {
        double[] darray = new double[passengerCount(i)];
        Connection con = DbUtil.connection();
        String query = DbConstant.GET_COST_SQL+ i ;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        for(int k = 0; k < passengerCount(i); k++){
            rs.next();
            darray[k] = rs.getDouble("Cost");
        }
        return darray;
    }
}
