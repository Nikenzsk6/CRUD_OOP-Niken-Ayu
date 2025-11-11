/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kelas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class class_User extends class_koneksi{
   private String userName, userEmail, userPassword, userFullName; 
   private int userStatus;
   private final Connection koneksi;                                //penggunaan FINAL membuat variabel koneksi hanya bisa diisi 1x 
   private PreparedStatement ps;
   private Statement st;
   private ResultSet rs;
   private String query;
   
   public class_User(){
       koneksi = super.configDB();
   }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
    
    public void tambah_user(){
        query = "INSERT INTO user VALUES(?,?,MD5(?),?,?)";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, userPassword);
            ps.setString(4, userFullName);
            ps.setInt(5, userStatus);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan ");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan ");
        }
    }
    
    public void ubah_user(){
        if("".equals(userPassword)){
            query = "UPDATE user SET userEmail=?,userFullName=?,userStatus=? "
                    + "WHERE userName=?";
            try {
                ps = koneksi.prepareStatement(query);
                ps.setString(1, userEmail);
                ps.setString(2, userFullName);
                ps.setInt(3, userStatus);
                ps.setString(4, userName);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ");
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah ");
            }
        }else{
            query = "UPDATE user SET userEmail=?,userPassword=MD5(?),userFullName=?,userStatus=? "
                    + "WHERE userName=?";
            try {
                ps = koneksi.prepareStatement(query);
                ps.setString(1, userEmail);
                ps.setString(2, userPassword);
                ps.setString(3, userFullName);
                ps.setInt(4, userStatus);
                ps.setString(5, userName);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ");
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah ");
            }
        }   
    }
    
    public void hapus_user(){
         query = "DELETE FROM user WHERE userName = ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
           
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus ");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus ");
        }
    }
    
    public ResultSet showUser(){
        query = "SELECT * FROM user";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
        }
        return rs;
    }
    
    public void login(){
        query = "SELECT * FROM user WHERE userName=? AND userPassword=MD5(?)";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
           
            rs = ps.executeQuery();
            if(rs.next()){
                class_Sesion.setUsername(rs.getString("userName"));
                class_Sesion.setEmail(rs.getString("userEmail"));
                class_Sesion.setFullname(rs.getString("userFullName"));
               class_Sesion.setStatus("Active"); //status milik sesion
                JOptionPane.showMessageDialog(null, "Anda Berhasil Login ");
            }else{
                class_Sesion.setStatus("Non Active");//status milik sesion
                JOptionPane.showMessageDialog(null, "Anda Gagal Login ");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            
        }
    }
    
    public void logOut(){
        class_Sesion.setUsername("");
        class_Sesion.setEmail("");
        class_Sesion.setFullname("");
        class_Sesion.setStatus("");
    }
}
