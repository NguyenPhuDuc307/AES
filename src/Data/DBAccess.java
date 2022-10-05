package Data;

import Entity.Employee;
import Entity.Class;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class DBAccess {

    static Connection connection = SQLServerConnection.getConnection();
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    // Login
    public static String Login(User user) {
        String query = "EXEC SP_Login ?, ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(4);
            } else {
                return "Đăng nhập thất bại, vui lòng kiểm tra lại thông tin đăng nhập!";
            }
        } catch (SQLException e) {
            return e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
        }
    }

    // Register
    public static String Register(User user) {
        String query = "EXEC SP_Register ?, ?, ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRoles());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Đăng ký tài khoản thành công!";
            } else {
                return "Đăng ký tài khoản thất bại, vui lòng kiểm tra lại thông tin đăng ký!";
            }
        } catch (SQLException e) {
            return e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
        }
    }

    // Register
    public static String addEmployee(Employee employee) {
        String query = "EXEC SP_CreateEmployee ?, ?, ?, ?, ?, ?, ?, true";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getUserName());
            preparedStatement.setString(3, employee.getFullName());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setInt(6, employee.getSex());
            preparedStatement.setString(7, employee.getAddress());

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Thêm mới nhân viên thành công! Mã nhân viên [" + employee.getEmployeeId() + "]";
            } else {
                return "Thêm nhân viên thất bại thất bại, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            return e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
        }
    }

    // lấy danh sách mã hoá
    public static List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String query = "EXEC SP_GetEmployees";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Employee(
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getBoolean(8)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // Get Employee By EmloyeeId
    public static Employee getEmployeeById(String EmployId) {
        String query = "EXEC SP_getEmployeeById ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, EmployId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getBoolean(8));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // Insert Employee
    public static String insertEmployee(Employee employee) {
        String query = "EXEC SP_CreateEmployee ?, ?, ?, ?, ?, ?, ?, ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getEmployeeId());
            preparedStatement.setInt(2, employee.getUserId());
            preparedStatement.setString(3, employee.getFullName());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setInt(6, employee.getSex());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setBoolean(8, employee.isEnable());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Thêm nhân viên thành công!";
            } else {
                return "Thêm nhân viên thất bại, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            return e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
        }
    }

    // Update Employee
    public static String updateEmployee(Employee employee) {
        String query = "EXEC SP_UpDateEmployee ?,?,?,?,?,?,?,? ";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getEmployeeId());
            preparedStatement.setInt(2, employee.getUserId());
            preparedStatement.setString(3, employee.getFullName());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setInt(6, employee.getSex());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setBoolean(8, employee.isEnable());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Cập nhật thông tin thành công!";
            } else {
                return "Cập nhật thông tin thất bại, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            return e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
        }
    }

    // getAllClass
    public static List<Class> getAllCLasses() {
        List<Class> list = new ArrayList<>();
        String query = "EXEC SP_GetClasses";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Class(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getBoolean(3)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // Insert Class
    public static String insertClass(Class classes) {
        String query = "SP_AddClass ?, ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classes.getClassIdString());
            preparedStatement.setString(2, classes.getClassName());

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Thêm lớp học thành công!";
            } else {
                return "Thêm lớp học, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("Violation of PRIMARY KEY")) {
                return "Lớp học đã tồn tại";
            }
            return er;
        }
    }

    // Update Class
    public static String updateClass(Class classes) {
        String query = "EXEC SP_UpdateClass ?,?,?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classes.getClassIdString());
            preparedStatement.setString(2, classes.getClassName());
            preparedStatement.setBoolean(3, classes.isEnable());

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Cập nhật thông tin thành công!";
            } else {
                return "Cập nhật thông tin thất bại, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            return e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
        }
    }

    public static void main(String[] args) {
        Class cl = new Class("19DTHE2", "Công nghệ");
        System.out.println(insertClass(cl));
    }

}
