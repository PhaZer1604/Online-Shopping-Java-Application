package project;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DbConnector {

    public static int insertToUser(String username, String name, String email, String password, String card) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothingdb","admin","");
            String sql = "INSERT INTO userinfo"
                                  +"(username, name, email, password, card)"
                                  +"VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, card);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Signing up successful!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Occured");
        }
        return 0;
    }
    
    public static boolean signer(String username, String pass){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothingdb","admin","");
            String check = "SELECT username, password FROM userinfo WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(check);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }   
}
