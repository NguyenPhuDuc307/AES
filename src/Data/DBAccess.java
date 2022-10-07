package Data;

import Entity.Employee;
import Entity.Class;
import Entity.Student;
import Entity.Subject;
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
        String query = "EXEC SP_CreateEmployee ?, ?, ?, ?, ?, ?, true";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getFullName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setInt(5, employee.getSex());
            preparedStatement.setString(6, employee.getAddress());

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Thêm mới nhân viên thành công! Mã nhân viên [" + AES.AES.decrypt(employee.getEmployeeId()) + "]";
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
                        resultSet.getBoolean(8),
                        resultSet.getInt(9)));
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
                        resultSet.getBoolean(8),
                        resultSet.getInt(9));
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
        String query = "EXEC SP_UpDateEmployee ?,?,?,?,?,?,?,?,? ";
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
            preparedStatement.setInt(9, employee.getRole());
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

    public static List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        String query = "EXEC SP_GetSubjects";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Subject(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getBoolean(4)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // getAllClassId
    public static String getAllClassId() {
        String string = "";
        String query = "EXEC SP_GetClassId";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                string += "\n" + resultSet.getString(1);
            }
        } catch (SQLException e) {
        }
        return string.substring(1);
    }
    
    public static String getAllSubjectName() {
        String string = "";
        String query = "EXEC SP_GetSubjectName";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                string += "\n" + resultSet.getString(1);
            }
        } catch (SQLException e) {
        }
        return string.substring(1);
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
                return "Thêm lớp học thất bại, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("Violation of PRIMARY KEY")) {
                return "Lớp học đã tồn tại";
            }
            return er;
        }
    }
    
    public static String insertSubject(Subject subject) {
        String query = "SP_AddSubject ?, ?, ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subject.getSubjectId());
            preparedStatement.setString(2, subject.getSubjectName());
            preparedStatement.setInt(3, subject.getCreditsNumber());

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Thêm môn học thành công!";
            } else {
                return "Thêm môn học thất bại, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("Violation of PRIMARY KEY")) {
                return "Môn học đã tồn tại";
            }
            return er;
        }
    }
    
    public static String updateSubject(Subject subject) {
        String query = "EXEC SP_UpDateSubject ?,?,?,?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subject.getSubjectId());
            preparedStatement.setString(2, subject.getSubjectName());
            preparedStatement.setInt(3, subject.getCreditsNumber());            
            preparedStatement.setBoolean(4, subject.isEnable());


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
    
    public static String deleteSubject(String SubjectId) {
        String query = "EXEC SP_DeleteSubject ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, SubjectId);

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Xoá môn học thành công!";
            } else {
                return "Xoá môn học thất bại, vui lòng kiểm tra lại!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("REFERENCE constraint")) {
                return "Không thể xoá môn học vì đang được sử dụng, bạn chỉ có thể ngưng môn học này";
            }
            return er;
        }
    }
    
    public static String deleteEmployee(String EmployeeId) {
        String query = "EXEC SP_DeleteEmployee ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, EmployeeId);

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Xoá nhân viên thành công!";
            } else {
                return "Xoá nhân viên thất bại, vui lòng kiểm tra lại!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("REFERENCE constraint")) {
                return "Không thể xoá nhân viên vì đang được sử dụng, bạn chỉ có thể đình chỉ nhân viên này";
            }
            return er;
        }
    }
    
    public static String deleteStudent(String StudentId) {
        String query = "EXEC SP_DeleteStudent ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, StudentId);

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Xoá sinh viên thành công!";
            } else {
                return "Xoá sinh viên thất bại, vui lòng kiểm tra lại!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("REFERENCE constraint")) {
                return "Không thể xoá sinh viên vì đang được sử dụng, bạn chỉ có thể đình ngưng sinh viên này";
            }
            return er;
        }
    }
    
    public static String deleteClass(String ClassId) {
        String query = "EXEC SP_DeleteClass ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ClassId);

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Xoá lớp học thành công!";
            } else {
                return "Xoá lớp học thất bại, vui lòng kiểm tra lại!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("REFERENCE constraint")) {
                return "Không thể xoá lớp học vì đang được sử dụng, bạn chỉ có thể đình ngưng lớp học này";
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

    // getAllClass
    public static List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String query = "EXEC SP_GetStudents";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Student(
                        resultSet.getString(1),
                        resultSet.getString(2),
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

    // Insert Class
    public static String insertStudent(Student student) {
        String query = "EXEC SP_CreateStudent ?, ?, ?, ?, ?, ?, ?, true";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getClassId());
            preparedStatement.setString(3, student.getFullName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getSex());
            preparedStatement.setString(7, student.getAddress());

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return "Thêm sinh viên thành công!";
            } else {
                return "Thêm sinh viên thất bại, vui lòng kiểm tra lại thông tin!";
            }
        } catch (SQLException e) {
            String er = e.toString().replaceAll("com.microsoft.sqlserver.jdbc.SQLServerException: ", "") + "!";
            if (er.contains("Violation of PRIMARY KEY")) {
                return "Sinh viên đã tồn tại";
            }
            if (er.contains("FOREIGN KEY")) {
                return "Vui lòng chọn lại lớp học";
            }
            return er;
        }
    }

    // Update Class
    public static String updateStudent(Student student) {
        String query = "EXEC SP_UpDateStudent ?, ?, ?, ?, ?, ?, ?, ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getClassId());
            preparedStatement.setString(3, student.getFullName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getSex());
            preparedStatement.setString(7, student.getAddress());
            preparedStatement.setBoolean(8, student.isEnable());

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
        List<Employee> employees = getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
