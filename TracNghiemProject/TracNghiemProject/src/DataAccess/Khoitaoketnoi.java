/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;
import java.sql.*;

/**
 *
 * @author nmthe
 */
public class Khoitaoketnoi {
    
    // Declare the JDBC objects.
    public static Connection ketNoi;
    public static Connection getKN() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ThiTracNghiemProject;";
            ketNoi = DriverManager.getConnection(url, "sa", "sa");
        }catch(ClassNotFoundException | SQLException e) {
             e.printStackTrace();
        }
        return ketNoi;
    }
    }
