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
    public String connectionUrl = "jdbc:sqlserver://DESKTOP-R83B3L3\\SQLEXPRESS:1433;databaseName=ThiTracNghiemProject;integratedSecurity=true;";
    // Declare the JDBC objects.
    public Connection con = null;
    public void initConnect(){
        try {
         // Establish the connection.
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         con = DriverManager.getConnection(connectionUrl, "sa", "123456");
        }
        catch (Exception e) {
           e.printStackTrace();
        }
    }
}
