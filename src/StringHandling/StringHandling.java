/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StringHandling;

import Data.DBAccess;
import Entity.Employee;
import Entity.Class;
import Entity.Student;
import Entity.Subject;
import Entity.Transcript;
import java.awt.Color;
import java.awt.Component;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenphuduc
 */
public class StringHandling {

    // nối chuỗi
    public static String stringSoncatenation(String s1, String s2) {
        return s1 + "\n" + s2;
    }

    public static String stringSoncatenation(String s1, String s2, String s3) {
        return s1 + "\n" + s2 + "\n" + s3;
    }

    public static String stringSoncatenation(String s1, String s2, String s3, String s4) {
        return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4;
    }

    public static String stringSoncatenation(String s1, String s2, String s3, String s4, String s5) {
        return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5;
    }

    public static String stringSoncatenation(String s1, String s2, String s3, String s4, String s5, String s6) {
        return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6;
    }

    public static String stringSoncatenation(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6 + "\n" + s7;
    }

    public static String stringSoncatenation(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6 + "\n" + s7 + "\n" + s8;
    }

    public static String stringSoncatenation(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
        return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6 + "\n" + s7 + "\n" + s8 + "\n" + s9;
    }

    public static String stringSoncatenation(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10) {
        return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6 + "\n" + s7 + "\n" + s8 + "\n" + s9 + "\n" + s10;
    }

    // lấy ra đoạn string từ array
    public static String getString(String[] arrStrings, int begin, int end) {
        String string = "";
        for (int i = begin; i < end; i++) {
            if (i != begin) {
                string += "\n" + arrStrings[i];
            } else {
                string += arrStrings[i];
            }

        }
        return string;
    }

    // lấy ra kiểu byte từ string
    public static String getBase(String input) {
        String inputBase64 = null;
        byte[] deByte = input.getBytes(StandardCharsets.UTF_8);
        inputBase64 = Base64.getEncoder().encodeToString(deByte);
        return inputBase64;
    }

    public static void changeTable(JTable table, int column_index, String text) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String st_val = removeAccent(String.valueOf(table.getValueAt(row, column_index).toString()));
                if (text.isEmpty()) {

                    c.setBackground(Color.getHSBColor(150, 204, 30));
                } else {
                    if (st_val.toLowerCase().contains(text.toLowerCase())) {
                        c.setBackground(Color.getHSBColor(255, 204, 200));
                    } else {
                        c.setBackground(Color.getHSBColor(255, 204, 30));
                    }
                }
                return c;
            }
        });
    }

    public static Transcript getTranscript(List<Transcript> listTranscripts, String StudentId, String SubjectId) {
        for (Transcript transcript : listTranscripts) {
            if (transcript.getStudentId().equals(StudentId) && transcript.getSubjectId().equals(SubjectId )) {
                return transcript;
            }
        }
        return null;
    }

    public void changeTabledefault(JTable table, int column_index, String text) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setBackground(Color.ORANGE);
                return c;
            }
        });
    }

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    //chuyển list Employee thành chuỗi
    public static String getStringEmployee() {
        String string = "";
        List<Employee> listEmployees = DBAccess.getAllEmployees();
        if (!listEmployees.isEmpty()) {
            for (Employee employee : listEmployees) {
                string += employee + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    public static String getStringTranscript() {
        String string = "";
        List<Transcript> listTranscripts = DBAccess.getAllTranscripts();
        if (!listTranscripts.isEmpty()) {
            for (Transcript transcript : listTranscripts) {
                string += transcript + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    public static String getStringStudent1() {
        String string = "";
        List<Student> listStudents = DBAccess.getAllStudents1();
        if (!listStudents.isEmpty()) {
            for (Student student : listStudents) {
                string += student.toString1() + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    //chuyển list Class thành chuỗi
    public static String getStringClass() {
        String string = "";
        List<Class> listClasses = DBAccess.getAllCLasses();
        if (!listClasses.isEmpty()) {
            for (Class cl : listClasses) {
                string += cl + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    public static String getStringClassId() {
        String string = "";
        List<Class> listClasses = DBAccess.getAllCLasses();
        if (!listClasses.isEmpty()) {
            for (Class cl : listClasses) {
                string += cl.getClassIdString() + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    //chuyển list Subject thành chuỗi
    public static String getStringSubject() {
        String string = "";
        List<Subject> listSubjects = DBAccess.getAllSubjects();
        if (!listSubjects.isEmpty()) {
            for (Subject subject : listSubjects) {
                string += subject + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }
    
    public static String getStringSubjectId() {
        String string = "";
        List<Subject> listSubjects = DBAccess.getAllSubjects();
        if (!listSubjects.isEmpty()) {
            for (Subject subject : listSubjects) {
                string += subject.getSubjectId() + " - " + subject.getSubjectName() + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    //chuyển list Student thành chuỗi
    public static String getStringStudent() {
        String string = "";
        List<Student> listStudents = DBAccess.getAllStudents();
        if (!listStudents.isEmpty()) {
            for (Student student : listStudents) {
                string += student + "\n";
            }
            return string.substring(0, string.length() - 1);
        }
        return string;
    }

    // chuyển chuỗi thành model
    public static void getDefaultTableModel_Employee(DefaultTableModel model, List<Employee> listEmployees) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 8;

        for (Employee employee : listEmployees) {
            Object[] row = new Object[COLUMN];
            row[0] = String.valueOf(employee.getEmployeeId());
            row[1] = String.valueOf(employee.getFullName());
            row[3] = String.valueOf(employee.getPhoneNumber());
            row[2] = String.valueOf(employee.getEmail());
            if (employee.getSex() == 1) {
                row[4] = "Nam";
            } else {
                row[4] = "Nữ";
            }

            row[5] = String.valueOf(employee.getAddress());
            if (employee.isEnable() == true) {
                row[6] = "Đang làm";
            } else {
                row[6] = "Đã nghỉ việc";
            }
            if (employee.getRole() == 1) {
                row[7] = "Quản trị viên";
            } else {
                row[7] = "Phòng công tác";
            }
            model.addRow(row);
        }
    }

    // chuyển chuỗi thành model
    public static void getDefaultTableModel_Student(DefaultTableModel model, List<Student> students) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 8;

        for (Student student : students) {
            Object[] row = new Object[COLUMN];
            row[0] = String.valueOf(student.getStudentId());
            row[1] = String.valueOf(student.getClassId());
            row[2] = String.valueOf(student.getFullName());
            row[3] = String.valueOf(student.getPhoneNumber());
            row[4] = String.valueOf(student.getEmail());
            if (student.getSex() == 1) {
                row[5] = "Nam";
            } else {
                row[5] = "Nữ";
            }

            row[6] = String.valueOf(student.getAddress());
            if (student.isEnable() == true) {
                row[7] = "Đang học";
            } else {
                row[7] = "Đã thôi học";
            }
            model.addRow(row);
        }
    }

    // chuyển chuỗi thành model
    public static void getDefaultTableModel_Student1(DefaultTableModel model, List<Student> students) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 3;

        for (Student student : students) {
            Object[] row = new Object[COLUMN];
            row[0] = String.valueOf(student.getStudentId());
            row[1] = String.valueOf(student.getClassId());
            row[2] = String.valueOf(student.getFullName());
            model.addRow(row);
        }
    }

    // chuyển chuỗi thành model
    public static void getList_Employee(List<Employee> list, String string) {

        list.removeAll(list);

        int COLUMN = 9;

        // ngắt chuỗi thành array
        String arrString[] = string.split("\n");

        for (int i = 0; i < arrString.length; i += COLUMN) {

            // tạo ra đối tượng object
            Employee employee = new Employee();

            for (int j = i; j < i + COLUMN; j++) {

                switch (j % COLUMN) {
                    case 0 ->
                        employee.setEmployeeId(String.valueOf(arrString[j]));
                    case 1 ->
                        employee.setUserId(Integer.parseInt(arrString[j]));
                    case 2 ->
                        employee.setFullName(String.valueOf(arrString[j]));
                    case 3 ->
                        employee.setEmail(String.valueOf(arrString[j]));
                    case 4 ->
                        employee.setPhoneNumber(String.valueOf(arrString[j]));
                    case 5 ->
                        employee.setSex(Integer.parseInt(arrString[j]));
                    case 6 ->
                        employee.setAddress(String.valueOf(arrString[j]));
                    case 7 ->
                        employee.setEnable(Boolean.parseBoolean(arrString[j]));
                    case 8 ->
                        employee.setRole(Integer.parseInt(arrString[j]));
                    default -> {
                    }
                }

            }
            // lưu vào danh sách
            list.add(employee);
        }
    }

    // chuyển chuỗi thành model
    public static void getList_Class(List<Class> list, String string) {

        list.removeAll(list);

        int COLUMN = 3;

        // ngắt chuỗi thành array
        String arrString[] = string.split("\n");

        for (int i = 0; i < arrString.length; i += COLUMN) {

            // tạo ra đối tượng object
            Class cl = new Class();

            for (int j = i; j < i + COLUMN; j++) {

                switch (j % COLUMN) {
                    case 0 ->
                        cl.setClassIdString(String.valueOf(arrString[j]));
                    case 1 ->
                        cl.setClassName(String.valueOf(arrString[j]));
                    case 2 ->
                        cl.setEnable(Boolean.parseBoolean(arrString[j]));
                    default -> {
                    }
                }
            }
            // lưu vào danh sách
            list.add(cl);
        }
    }

    // chuyển chuỗi thành model
    public static void getList_Subject(List<Subject> list, String string) {

        list.removeAll(list);

        int COLUMN = 4;

        // ngắt chuỗi thành array
        String arrString[] = string.split("\n");

        for (int i = 0; i < arrString.length; i += COLUMN) {

            // tạo ra đối tượng object
            Subject subject = new Subject();

            for (int j = i; j < i + COLUMN; j++) {

                switch (j % COLUMN) {
                    case 0 ->
                        subject.setSubjectId(String.valueOf(arrString[j]));
                    case 1 ->
                        subject.setSubjectName(String.valueOf(arrString[j]));
                    case 2 ->
                        subject.setCreditsNumber(Integer.parseInt(arrString[j]));
                    case 3 ->
                        subject.setEnable(Boolean.parseBoolean(arrString[j]));
                    default -> {
                    }
                }
            }
            // lưu vào danh sách
            list.add(subject);
        }
    }

    public static void getList_Transcript(List<Transcript> list, String string) {

        list.removeAll(list);

        int COLUMN = 7;

        // ngắt chuỗi thành array
        String arrString[] = string.split("\n");

        for (int i = 0; i < arrString.length; i += COLUMN) {

            // tạo ra đối tượng object
            Transcript transcript = new Transcript();

            for (int j = i; j < i + COLUMN; j++) {

                switch (j % COLUMN) {
                    case 0 ->
                        transcript.setStudentId(String.valueOf(arrString[j]));
                    case 1 ->
                        transcript.setStudentName(String.valueOf(arrString[j]));
                    case 2 ->
                        transcript.setClassIdString(String.valueOf(arrString[j]));
                    case 3 ->
                        transcript.setSubjectId(String.valueOf(arrString[j]));
                    case 4 ->
                        transcript.setSubjectName(String.valueOf(arrString[j]));
                    case 5 ->
                        transcript.setEmployeeName(String.valueOf(arrString[j]));
                    case 6 ->
                        transcript.setTranscripts(Float.parseFloat(arrString[j]));
                    default -> {
                    }
                }
            }
            // lưu vào danh sách
            list.add(transcript);
        }
    }

    // chuyển chuỗi thành model
    public static void getDefaultTableModel_Class(DefaultTableModel model, List<Class> listClass) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 3;

        for (Entity.Class cla : listClass) {
            Object[] row = new Object[COLUMN];
            row[0] = String.valueOf(cla.getClassIdString());
            row[1] = String.valueOf(cla.getClassName());

            if (cla.isEnable() == true) {
                row[2] = "Đang học";
            } else {
                row[2] = "Đã ngưng";
            }
            model.addRow(row);
        }
    }

    // chuyển chuỗi thành model
    public static void getDefaultTableModel_Subject(DefaultTableModel model, List<Subject> listSubjects) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 4;

        for (Subject subject : listSubjects) {
            Object[] row = new Object[COLUMN];
            row[0] = String.valueOf(subject.getSubjectId());
            row[1] = String.valueOf(subject.getSubjectName());
            row[2] = String.valueOf(subject.getCreditsNumber());

            if (subject.isEnable() == true) {
                row[3] = "Đang học";
            } else {
                row[3] = "Đã ngưng";
            }
            model.addRow(row);
        }

    }

    // chuyển chuỗi thành model
    public static void getDefaultTableModel_Transcript(DefaultTableModel model, List<Transcript> listTranscripts) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 7;

        for (Transcript transcript : listTranscripts) {
            Object[] row = new Object[COLUMN];
            row[0] = transcript.getStudentId();
            row[1] = transcript.getClassIdString();
            row[2] = transcript.getStudentName();
            row[3] = transcript.getSubjectId();
            row[4] = transcript.getSubjectName();
            row[5] = transcript.getEmployeeName();
            row[6] = transcript.getTranscripts();

            model.addRow(row);
        }
    }

    public static void getDefaultTableModel_Transcript_C(DefaultTableModel model, List<Transcript> listTranscripts, String ClassId) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 7;

        for (Transcript transcript : listTranscripts) {
            if (transcript.getClassIdString().equals(ClassId)) {
                Object[] row = new Object[COLUMN];
                row[0] = transcript.getStudentId();
                row[1] = transcript.getClassIdString();
                row[2] = transcript.getStudentName();
                row[3] = transcript.getSubjectId();
                row[4] = transcript.getSubjectName();
                row[5] = transcript.getEmployeeName();
                row[6] = transcript.getTranscripts();

                model.addRow(row);
            }
        }
    }

    public static void getDefaultTableModel_Transcript_S(DefaultTableModel model, List<Transcript> listTranscripts, String SubjectId) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 7;

        for (Transcript transcript : listTranscripts) {
            if (transcript.getSubjectId().equals(SubjectId)) {
                Object[] row = new Object[COLUMN];
                row[0] = transcript.getStudentId();
                row[1] = transcript.getClassIdString();
                row[2] = transcript.getStudentName();
                row[3] = transcript.getSubjectId();
                row[4] = transcript.getSubjectName();
                row[5] = transcript.getEmployeeName();
                row[6] = transcript.getTranscripts();
                model.addRow(row);
            }
        }
    }

    public static void getDefaultTableModel_Transcript_CS(DefaultTableModel model, List<Transcript> listTranscripts, String ClassId, String SubjectIdString) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 7;

        for (Transcript transcript : listTranscripts) {
            if (transcript.getClassIdString().equals(ClassId) && transcript.getSubjectId().equals(SubjectIdString)) {
                Object[] row = new Object[COLUMN];
                row[0] = transcript.getStudentId();
                row[1] = transcript.getClassIdString();
                row[2] = transcript.getStudentName();
                row[3] = transcript.getSubjectId();
                row[4] = transcript.getSubjectName();
                row[5] = transcript.getEmployeeName();
                row[6] = transcript.getTranscripts();
                model.addRow(row);
            }
        }
    }

    // chuyển chuỗi thành model
    public static void getList_Student(List<Student> list, String string) {

        list.removeAll(list);

        int COLUMN = 8;

        // ngắt chuỗi thành array
        String arrString[] = string.split("\n");

        for (int i = 0; i < arrString.length; i += COLUMN) {

            // tạo ra đối tượng object
            Student student = new Student();

            for (int j = i; j < i + COLUMN; j++) {

                switch (j % COLUMN) {
                    case 0:
                        student.setStudentId(String.valueOf(arrString[j]));
                        break;
                    case 1:
                        student.setClassId(String.valueOf(arrString[j]));
                    case 2:
                        student.setFullName(String.valueOf(arrString[j]));
                        break;
                    case 3:
                        student.setPhoneNumber(String.valueOf(arrString[j]));
                        break;
                    case 4:
                        student.setEmail(String.valueOf(arrString[j]));
                        break;
                    case 5:
                        student.setSex(Integer.parseInt(arrString[j]));
                        break;
                    case 6:
                        student.setAddress(String.valueOf(arrString[j]));
                        break;
                    case 7:
                        student.setEnable(Boolean.parseBoolean(arrString[j]));
                        break;
                    default:
                        break;
                }

            }
            // lưu vào danh sách
            list.add(student);
        }
    }

    public static void getList_Student1(List<Student> list, String string) {

        list.removeAll(list);

        int COLUMN = 3;

        // ngắt chuỗi thành array
        String arrString[] = string.split("\n");

        for (int i = 0; i < arrString.length; i += COLUMN) {

            // tạo ra đối tượng object
            Student student = new Student();

            for (int j = i; j < i + COLUMN; j++) {

                switch (j % COLUMN) {
                    case 0:
                        student.setStudentId(String.valueOf(arrString[j]));
                        break;
                    case 1:
                        student.setClassId(String.valueOf(arrString[j]));
                    case 2:
                        student.setFullName(String.valueOf(arrString[j]));
                        break;
                    default:
                        break;
                }

            }
            // lưu vào danh sách
            list.add(student);
        }
    }

    // check passwork
    public static boolean isValidPassword(String password) {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();
    }

    public static Employee getEmployee(List<Employee> employees, String EmployeeID) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(EmployeeID)) {
                return employee;
            }
        }
        return null;
    }

    public static Class getClass(List<Class> listClass, String ClassId) {
        for (Class clas : listClass) {
            if (clas.getClassIdString().equals(ClassId)) {
                return clas;
            }
        }
        return null;
    }

    public static Subject getSubject(List<Subject> listSubject, String SubjectId) {
        for (Subject subject : listSubject) {
            if (subject.getSubjectId().equals(SubjectId)) {
                return subject;
            }
        }
        return null;
    }

    public static String getListSubjectFromListTranscript(List<Transcript> listTranscript) {
        String result = "";
        for (Transcript transcript : listTranscript) {
            result += transcript.getSubjectId() + " - " + transcript.getSubjectName() + "\n";
        }
        return result.substring(0, result.length() - 1);
    }

    public static String getListClassFromListTranscript(List<Transcript> listTranscript) {
        String result = "";
        for (Transcript transcript : listTranscript) {
            result += transcript.getClassIdString() + "\n";
        }
        return result.substring(0, result.length() - 1);
    }

    public static Student getStudent(List<Student> listStudent, String StudentId) {
        for (Student student : listStudent) {
            if (student.getStudentId().equals(StudentId)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getStringClass());
    }

}
