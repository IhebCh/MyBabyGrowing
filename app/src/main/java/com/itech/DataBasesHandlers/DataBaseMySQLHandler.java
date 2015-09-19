package com.itech.DataBasesHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class DataBaseMySQLHandler {

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine
            = "jdbc:mysql://localhost:3306/bemyapp";
          //  = "jdbc:mysql://10.0.2.120:3306/bibliodb_server";
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
}
