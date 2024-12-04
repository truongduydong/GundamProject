/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Fpt
 */
public class DAO_Users {
    public static DAO_Users INSTANCE = new DAO_Users();
    public Connection con;
    private String status = "OK";
    
    public DAO_Users() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection();
                System.out.println("Kết nối database: " + con.isClosed());
            } catch (Exception e) {
                status = "Error ar connection" + e.getMessage();
            }
        }
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Users login(String user, String pass){
        String sql = "select * from Users_HE176167 where username = ? and password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                );
            }
        } catch (Exception e) {
            status = "Error ar read Users_HE176167 " + e.getMessage();
        }
        return null;
        
    }
    // xem đã tồn tại tài khoản chưa
    public Users checkSignUp(String user){
        String sql = "select * from Users_HE176167 where username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                );
            }
        } catch (Exception e) {
            status = "Error ar read Users_HE176167 " + e.getMessage();
        }
        return null;
        
    }
    
    
    
    // tìm lại pass word
    public String checkPassWord(String username, String email, String name, String phonenumber){
        String sql = "select * from Users_HE176167 where username = ? and email = ? and phone_number = ? and full_name = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, phonenumber);
            ps.setString(4, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return (new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)).getPassword()
                );
            }
        } catch (Exception e) {
            status = "Error ar read Users_HE176167 " + e.getMessage();
        }
        return null;
        
    }
    
    // check xem đúng mật khẩu cần sửa chưa
    public Users checkChangePassWord(String username, String oldpassword, String name, String phonenumber, String email){
        String sql = "select * from Users_HE176167 where username = ? and [password] = ? and email = ? and full_name = ? \n" +
"and phone_number = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, oldpassword);
            ps.setString(3, email);
            ps.setString(4, name);
            ps.setString(5, phonenumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return (new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                ));
            }
        } catch (Exception e) {
            status = "Error ar read Users_HE176167 " + e.getMessage();
        }
        return null;
        
    }
    // sửa mật khẩu
    public void changePassWord(String username, String newpasword){
        String sql = "UPDATE Users_HE176167  SET password = ?  WHERE username = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newpasword);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            status = "Error ar read Users_HE176167 " + e.getMessage();
        }
        
    }
    // thêm tài khoản
    public void signUp(String name, String phonenumber, String email, String username, String pass){
        String sql = "INSERT INTO Users_HE176167 (username, password, email, full_name, phone_number, isAdmin) \n" +
"VALUES(?,?,?,?,?,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, name);
            ps.setString(5, phonenumber);
            ps.executeUpdate();
        } catch (Exception e) {
            status = "Error ar read Users_HE176167 " + e.getMessage();
        }
        
    }
    
    public List<Users> loadAllUsers() {
        List<Users> user = new Vector<>();
        String sql = "select * from Users_HE176167";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.add(new Users(
                        rs.getInt(1),
                        rs.getString(2), //Name
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                ));
            }
        } catch (Exception e) {
            status = "Error ar read Products_HE176167 " + e.getMessage();
        }
        
        return user;
    }
     
     public void deleteUser(String user_id){
        String sql = "delete from Users_HE176167 where user_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(user_id));
            ps.executeUpdate();
        } catch (Exception e) {
            status = "Error ar read Products_HE176167 " + e.getMessage();
        }
        
    }
}
