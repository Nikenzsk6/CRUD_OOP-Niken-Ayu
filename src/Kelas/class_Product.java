/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class class_Product extends class_koneksi {

    private String productName, productDescription;
    private int productId, productCategory, productPrice;
    
    private final Connection koneksi; //penggunaan FINAL membuat variabel koneksi hanya bisa diisi 1x 
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public class_Product() {
        koneksi = super.configDB();
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductDescription() {
        return productDescription;
    }
    
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    public int getProducId() {
        return productId;
    }
    
    public void setProducId(int produId) {
        this.productId = produId;
    }
    
    public int getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }
    
    public int getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    
    
    public void tambahProduct(){
        query = "INSERT INTO product VALUES(?,?,?,?,?)";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setString(2, productName);
            ps.setInt(3, productCategory);
            ps.setString(4, productDescription);
            ps.setInt(5, productPrice);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan ");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan ");
        }
    }
    
    public void ubahProduct(){
            query = "UPDATE product SET productName=?,productCategory=?,productDescription=? "
                    + ", productPrice=? WHERE productId=?";
            try {
                ps = koneksi.prepareStatement(query);
                ps.setString(1, productName);
                ps.setInt(2, productCategory);
                ps.setString(3, productDescription);
                ps.setInt(4, productPrice);
                ps.setInt(5, productId);
                
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ");
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah ");
            }
        }   
    
    
    public void hapusProduct(){
         query = "DELETE FROM product WHERE productId = ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, productId);
           
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus ");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus ");
        }
    }
    
    public ResultSet showProduct(){
        query = "SELECT p.productid, p.productName, c.categoryName, p.productDescription, "
                + "p.productPrice FROM product p "
                + "LEFT JOIN category c ON p.productCategory = c.categoryId";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
        }
        return rs;
    }
    
    
   
    public ResultSet comboCategory() {
        query = "SELECT categoryName FROM category";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kategori");
        }
        return rs;
    }

    public String kodeCategory(String namaCategory) {
        query = "SELECT categoryId FROM category WHERE categoryName = ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, namaCategory);
            rs = ps.executeQuery();
            
            if (rs.next()){
                int id = rs.getInt("categoryId");
                setProductCategory(id);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "";
    }
    
    public ResultSet autoID(){
        query = "SELECT productId AS id FROM product";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat generate Id Product!");
            System.out.println(e);
        }
        return rs;
    }
    
}
