package com.silverline;

import java.sql.*;
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

    public static int askingCabinNo() {

        return getUserIntInput(String.format("Enter cabin number (1-%s) or %s to stop:", Constants.CABINS_COUNT, Constants.CABINS_COUNT + 1));
    }
    public static String  askingForAdd(int i) {

        return getUserInput(String.format("If you want to add another passenger, press 'y'. If not, press any key(Passenger %s) :- ", i).toUpperCase());
    }
    public static Connection connectionForDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cruiseshiplog";
        String uname = "root";
        String pass = "45Abc#12";

        return DriverManager.getConnection(url,uname,pass);
    }

    public static void storeInDatabase( String fname, String surname, Double cost, int fk) throws SQLException {

        String que1 = "INSERT INTO passenger(First_Name, Last_Name, Cost, C_ID) VALUES(?,?,?,?)";
        Connection con = connectionForDB();
        PreparedStatement st = con.prepareStatement(que1);// preparedStatement
        st.setString(1,fname);
        st.setString(2,surname);
        st.setDouble(3,cost);
        st.setInt(4,fk);
        int count = st.executeUpdate();
        System.out.println(count+ "row/s affected");

        st.close();
        con.close();
    }

    public static int cabinFulLCapacity(int i) throws SQLException {

        Connection con = connectionForDB();
        String query = "SELECT Capacity FROM cabin WHERE Cabin_ID = "+ i;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return  rs.getInt("Capacity");
    }

    public static void deletePassengersFromDB(int i) throws SQLException {

        Connection con = connectionForDB();
        String query = " DELETE FROM passenger WHERE C_ID = "+ i ;
        PreparedStatement st = con.prepareStatement(query);
        int count =st.executeUpdate();
        System.out.println(count + "row/s affected");
        st.close();
        con.close();
    }

    public static int passengerCountInCabin(int i) throws SQLException {

        Connection con = connectionForDB();
        String query = "SELECT COUNT(*) FROM passenger WHERE C_ID = "+ i ;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return  rs.getInt("COUNT(*)");

    }
    public static String[][] loadName(int i) throws SQLException {
        String[][] array = new String[passengerCountInCabin(i)][2];
        Connection con = connectionForDB();
        String query = "SELECT First_Name,Last_Name FROM passenger WHERE C_ID = "+ i ;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
            for (int j = 0; j < passengerCountInCabin(i); j++) {
                rs.next();
                array[j][0] = rs.getString("First_Name");
                array[j][1] = rs.getString("Last_name");
            }

        return array;
    }

    public static double[] loadCost(int i) throws SQLException {
        double[] darray = new double[passengerCountInCabin(i)];
        Connection con = connectionForDB();
        String query = "SELECT Cost FROM passenger WHERE C_ID = "+ i ;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
            for(int k = 0; k < passengerCountInCabin(i); k++){
                rs.next();
                darray[k] = rs.getDouble("Cost");
            }
        return darray;
    }

}
