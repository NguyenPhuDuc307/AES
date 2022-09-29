/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StringHandling;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author nguyenphuduc
 */
public class StringHandling {

    static int i, j, k, c = 0, w;

    // đếm số lượng xuất hiện ký tự
    public static String frequencyCount(String s) {

        String result = "";

        char[] z = new char[s.length()];
        for (w = 0; w < s.length(); w++) {
            z[w] = s.charAt(w);
        }
        for (i = 0; i < w; i++) {
            char ch = z[i];
            for (j = i + 1; j < w; j++) {
                if (z[j] == ch) {
                    for (k = j; k < (w - 1); k++) {
                        z[k] = z[k + 1];
                    }
                    w--;
                    j = i;
                }
            }
        }

        int[] t = new int[w];
        for (i = 0; i < w; i++) {
            for (j = 0, c = 0; j < s.length(); j++) {
                if (z[i] == s.charAt(j)) {
                    c++;
                }
            }
            t[i] = c;
            if (i == 0) {
                if (String.valueOf(z[i]).equals(" ")) {
                    result += "Số lần xuất hiện của [khoảng trắng] trong chuỗi: " + c;
                } else if (String.valueOf(z[i]).equals("\n")) {
                    result += "Số lần xuất hiện [xuống dòng] trong chuỗi: " + c;
                } else {
                    result += "Số lần xuất hiện của " + z[i] + " trong chuỗi: " + c;
                }
            } else if (String.valueOf(z[i]).equals(" ")) {
                result += "\nSố lần xuất hiện của [khoảng trắng] trong chuỗi: " + c;
            } else if (String.valueOf(z[i]).equals("\n")) {
                result += "\nSố lần xuất hiện [xuống dòng] trong chuỗi: " + c;
            } else {
                result += "\nSố lần xuất hiện của " + z[i] + " trong chuỗi: " + c;
            }
        }
        return result;
    }

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

    //
    public static String getString(String[] arrStrings, int begin, int end) {
        String string = "";
        for (int i = begin; i < end; i++) {
            if (i != begin) {
                string += "\n" + arrStrings[i].toString();
            } else {
                string += arrStrings[i].toString();
            }

        }
        return string;
    }
    
    public static String getBase(String input){
        String inputBase64 = null;
        byte[] deByte = input.getBytes(StandardCharsets.UTF_8);
        inputBase64 = Base64.getEncoder().encodeToString(deByte);
        return inputBase64;
    }

    public static void main(String[] args) {

        StringHandling afs = new StringHandling();

        String string = afs.frequencyCount("int[] t = new int[w];\n"
                + "        for (i = 0; i < w; i++) {\n"
                + "            for (j = 0, c = 0; j < s.length(); j++) {\n"
                + "                if (z[i] == s.charAt(j)) {\n"
                + "                    c++;\n"
                + "                }\n"
                + "            }\n"
                + "            t[i] = c;\n");

        System.out.println(string);
    }

}
