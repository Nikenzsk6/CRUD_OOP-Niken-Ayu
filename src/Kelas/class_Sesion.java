/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kelas;

/**
 *
 * @author HP
 */
public class class_Sesion {
   private static String username, email, fullname, status; //tipe static berarti class nya tidak perlu dibuatkan objek 
   //static bisa di panggil di frame yang lain karena di simpan temporary di memory. kalau di hapus/tutup maka hilang
   //kalau tidak static maka tidak bisa di panggil di frame lain.

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        class_Sesion.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        class_Sesion.email = email;
    }

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        class_Sesion.fullname = fullname;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        class_Sesion.status = status;
    }
    
    
}
