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
public class class_Category extends class_koneksi{

    private String categoryName;
    private int categoryId;
    
    private final Connection koneksi; //penggunaan FINAL membuat variabel koneksi hanya bisa diisi 1x 
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;

    public class_Category() {
        koneksi = super.configDB();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void tambahCategory() {
        query = "INSERT INTO category(categoryName) VALUES (?)";
        try {
            ps = koneksi.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoryName);
            ps.executeUpdate();
             // Ambil ID yang baru dibuat (AUTO_INCREMENT)
             rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idBaru = rs.getInt(1);
            }
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan ");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan ");
        }
    }

    public void ubahCategory() {
        query = "UPDATE category SET categoryName=? WHERE categoryId=?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, categoryName);
            ps.setInt(2, categoryId);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah ");
        }
    }

    public void hapusCategory() {
        query = "DELETE FROM category WHERE categoryId = ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, categoryId);

            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus ");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus ");
        }
    }

    public ResultSet showCategory() {
        query = "SELECT * FROM category";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
        }
        return rs;
    }

     public ResultSet autoID() {
        query = "SELECT categoryId AS id FROM category";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat generate Id Category!");
            System.out.println(e);
        }
        return rs;
    }


}
