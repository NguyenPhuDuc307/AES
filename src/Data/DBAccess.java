/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Entity.Encrypt;
import Entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nguyenphuduc
 */
public class DBAccess {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet = null;

    public DBAccess() {
        try {
            SQLServerConnection sqlConnection = new SQLServerConnection();
            connection = sqlConnection.getConnection();
            statement = connection.createStatement();
        } catch (Exception e) {
        }
    }

    public int Update(String string) {
        try {
            int i = statement.executeUpdate(string);
            return i;
        } catch (Exception e) {
            return -1;
        }
    }

    public ResultSet Query(String string) {
        try {
            resultSet = statement.executeQuery(string);
            return resultSet;
        } catch (Exception e) {
            return null;
        }
    }

    // kiểm tra trùng lặp username
    public static boolean checkUserName(String username) throws SQLException {
        String query = "select * from tb_User where Username = '" + username + "'";
        DBAccess data = new DBAccess();
        resultSet = data.Query(query);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    // đăng ký tài khoản
    public static boolean Register(User user) throws SQLException {
        String query = "insert into tb_User values('" + user.getUsername() + "','" + user.getPassword() + "',N'" + user.getFullName() + "')";
        DBAccess data = new DBAccess();
        int result = data.Update(query);
        if (result != 0) {
            return true;
        }
        return false;
    }

    // đăng nhập tài khoản
    public static boolean Login(User user) {
        String query = "select * from tb_User where Username = '" + user.getUsername() + "' and [Password] = '" + user.getPassword() + "'";
        try {
            DBAccess data = new DBAccess();
            resultSet = data.Query(query);

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    // lấy ra IdUser từ Username
    public static int getIdUserByUserName(String username) {
        String query = "select * from tb_User where Username = '" + username + "'";
        DBAccess acc = new DBAccess();

        try {
            ResultSet rs = acc.Query(query);
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // lấy danh sách mã hoá
    public static ArrayList<Encrypt> getAllEncrypt() {
        ArrayList<Encrypt> list = new ArrayList<>();
        String query = "select e.IdEncrypt, u.FullName, e.DateTimeCreated, e.Encrypt, e.[Key] from tb_Encrypt e, tb_User u WHERE e.UserCreated = u.IdUser";
        DBAccess acc = new DBAccess();

        try {
            resultSet = acc.Query(query);
            while (resultSet.next()) {
                list.add(new Encrypt(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    // đăng ký tài khoản
    public static boolean Encrypt(Encrypt encrypt) throws SQLException {
        String query = "insert into tb_Encrypt values('" + encrypt.getUserCreated()+ "','" + encrypt.getDateTimeCreated()+ "','" + encrypt.getEncrypt()+ "','"+ encrypt.getKey() +"')";
        DBAccess data = new DBAccess();
        int result = data.Update(query);
        if (result != 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        User user = new User("nguyenphuduc", "123", "Nguyễn Phú Đức");

        boolean check = DBAccess.Login(user);
        boolean check1 = DBAccess.checkUserName("admin");
        boolean check2 = DBAccess.Register(user);

        ArrayList<Encrypt> en = DBAccess.getAllEncrypt();

        for (Encrypt encrypt : en) {
            System.out.println(encrypt);
        }

        System.out.println(check);
        System.out.println(check1);
        System.out.println(check2);
    }

}
