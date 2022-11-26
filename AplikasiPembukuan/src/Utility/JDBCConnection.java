/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Vardina Nava M K
 */
public class JDBCConnection {
    public static Connection getConnection(){
        Connection conn=null;
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pembukuan?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";
			String pw = "";
			conn = DriverManager.getConnection(url, user, pw);
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println("koneksi gagal");
        }
        
        return conn;
    }
}
