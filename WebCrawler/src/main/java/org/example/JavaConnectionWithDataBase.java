package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaConnectionWithDataBase {

    static Connection connection = null;

    public static Connection makeConnection(){
        if(connection != null) return connection;

        String id = "root";
        String password = "A@ecis22al";
        String dataBase = "searchEngineApp";
        return makeConnection(id,password,dataBase);
    }

    public static Connection makeConnection(String user, String pass, String dataBase){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dataBase+"?user="+user+"&password="+pass);

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return connection;

    }
}
