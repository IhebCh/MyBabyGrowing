package com.itech.mybabygrowing.backend;


import com.itech.mybabygrowing.backend.models.Conseil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine
            = "jdbc:mysql://localhost:3306/bemyapp";
    static final String username = "root";
    static final String password = "";

    public Connection connecter() {
        Connection con = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection(chaine, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return con;
    }

    public List<Conseil> getAllConseils() {
        List<Conseil> list = new ArrayList<>();
        String query = "select * from conseil";
        try {
            Connection con = connecter();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Conseil conseil = new Conseil();
                conseil.setWeek(rs.getInt(1));
                conseil.setConseil(rs.getString(2));
               // conseil.setImage(rs.getBytes(3));


                list.add(conseil);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Conseil getConseilByWeek(String id_conseil){
        Conseil conseil=new Conseil();
        String query = "SELECT * FROM  `conseil` WHERE " +
                "id="+id_conseil+";";
        try {
            Connection con = connecter();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                conseil.setWeek(rs.getInt(1));
                conseil.setConseil(rs.getString(2));
                conseil.setImage(rs.getBytes(3));

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conseil;
    }
}

