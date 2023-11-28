package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexing {

    static Connection connection = null;

    Indexing(Document document, String url){

        String title = document.title();
        String contents = document.text();
        String link = url;

        connection = JavaConnectionWithDataBase.makeConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mytable VALUES(?,?,?)");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,link);
            preparedStatement.setString(3,contents);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
}
