/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import java.sql.*;
/**
 *
 * @author nmthe
 */
public class ClientSide {
    public void getDSBaiThi(ResultSet rs){
        try {
            while (rs.next()) {
               System.out.println(rs.getString("Causo") + " : " + rs.getString("Noidung"));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
