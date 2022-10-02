package Data;

import java.sql.*;
import javax.swing.*;

public class SQLServerConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:1433; databaseName=AES; user=sa; password=Phuduc@30072001";
            Connection con = DriverManager.getConnection(URL);

            return con;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(new SQLServerConnection().getConnection());
        } catch (Exception e) {
        }
    }

}
