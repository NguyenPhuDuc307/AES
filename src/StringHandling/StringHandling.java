/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StringHandling;

import Data.DBAccess;
import Entity.Employee;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
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

    //chuyển list Employee thành chuỗi
    public static String getStringEmployee() {
        String string = "";
        List<Employee> listEmployees = DBAccess.getAllEmployees();

        for (Employee employee : listEmployees) {
            string += employee + "\n";
        }

        return string.substring(0, string.length() - 1);
    }

    // chuyển chuỗi thành model
    public static void getDefaultTableModel_Employee(DefaultTableModel model, String string) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        int COLUMN = 7;

        // ngắt chuỗi thành array
        String arrString[] = string.split("\n");

        for (int i = 0; i < arrString.length; i += COLUMN) {

            // tạo ra đối tượng object
            Object[] row = new Object[COLUMN - 1];
            for (int j = i; j < i + COLUMN; j++) {

                switch (j % COLUMN) {
                    case 0:
                        row[0] = String.valueOf(arrString[j]);
                        break;
                    case 1:
                        break;
                    case 2:
                        row[1] = String.valueOf(arrString[j]);
                        break;
                    case 3:
                        row[2] = String.valueOf(arrString[j]);
                        break;
                    case 4:
                        row[3] = String.valueOf(arrString[j]);
                        break;
                    case 5:
                        if (null == String.valueOf(arrString[j])) {
                            row[4] = "Khác";
                        } else {
                            switch (String.valueOf(arrString[j])) {
                                case "1":
                                    row[4] = "Nam";
                                    break;
                                case "2":
                                    row[4] = "Nữ";
                                    break;
                                default:
                                    row[4] = "Khác";
                                    break;
                            }
                        }
                        break;
                    case 6:
                        row[5] = String.valueOf(arrString[j]);
                        break;
                    default:
                        break;
                }

            }
            // lưu vào danh sách
            model.addRow(row);
        }
    }

    public static void main(String[] args) {

    }

}
