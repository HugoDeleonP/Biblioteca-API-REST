package com.example.biblioteca.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private static final String URL = "jdbc:mysql://127.0.0.1:3307/biblioteca?user=root";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try(Connection conn = DatabaseConnect.connect()){
            if(conn != null){
                System.out.println("Conexão realizada com sucesso!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
