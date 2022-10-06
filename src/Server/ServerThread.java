/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Data.DBAccess;
import Entity.Employee;
import Entity.Class;
import Entity.Student;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

/**
 *
 * @author nguyenphuduc
 */
public class ServerThread implements Runnable {

    private Scanner in = null;
    private PrintWriter out = null;
    private Socket socket = null;
    private String name;

    public ServerThread(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        this.in = new Scanner(this.socket.getInputStream());
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            // lấy ra chuỗi byte input với một dòng
            String chuoi = in.nextLine().trim();

            // lấy chuỗi string từ chuỗi byte
            String inputString = new String(Base64.getDecoder().decode(chuoi), StandardCharsets.UTF_8);

            String[] arrString = inputString.split("\n");// ngắt chuỗi thành mảng chuỗi

            String funcString = String.valueOf(arrString[0].toCharArray());// ++ chứa yêu cầu

            String string = StringHandling.StringHandling.getString(arrString, 1, arrString.length);// ++ chứa nội dung
            switch (funcString) {
                case "login" -> {
                    String[] arrLogin = string.split("\n");// ngắt chuỗi string
                    String username = String.valueOf(arrLogin[0].toCharArray());// username
                    String pass = String.valueOf(arrLogin[1].toCharArray());// pass
                    String passEncrypt = AES.AES.encrypt(pass, "AES");
                    User user = new User(username, passEncrypt);// Create user
                    String result = DBAccess.Login(user);
                    out.println(result);
                }
                case "register" -> {
                    String[] arrRegister = string.split("\n");// ngắt chuỗi string
                    String username = String.valueOf(arrRegister[0].toCharArray());// username
                    String usernameEncrypt = AES.AES.encrypt(username, "AES");
                    String pass = String.valueOf(arrRegister[1].toCharArray());// pass
                    String passEncrypt = AES.AES.encrypt(pass, "AES");
                    int role = Integer.parseInt(String.valueOf(arrRegister[2].toCharArray()));// role
                    User user = new User(usernameEncrypt, passEncrypt, role);// Create user
                    String EmployID = String.valueOf(arrRegister[3]);
                    String EmployIDEncrypt = AES.AES.encrypt(EmployID, "AES");
                    String Name = String.valueOf(arrRegister[4]);
                    int Sex = Integer.parseInt(String.valueOf(arrRegister[5]));
                    String Email = String.valueOf(arrRegister[6]);
                    String Phonenum = String.valueOf(arrRegister[7]);
                    String Address = String.valueOf(arrRegister[8]);

                    Employee employee = new Employee(EmployIDEncrypt , Name, Email, Phonenum, Sex, Address);
                    // hàm register
                    String result2 = "";
                    String result1 = DBAccess.Register(user);
                    if (result1.equals("Đăng ký tài khoản thành công!")) {
                        result2 = DBAccess.addEmployee(employee);
                        out.println(result2);
                    } else {
                        out.println(result1);
                    }
                }
                case "getAllEmployee" -> {
                    // lấy ra list Employee dạng chuỗi
                    String resultString = StringHandling.StringHandling.getStringEmployee();
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                case "getAllClass" -> {
                    // lấy ra list Class dạng chuỗi
                    String resultString = StringHandling.StringHandling.getStringClass();
                    
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    
                    // gửi đi
                    out.println(inputBase64);
                }
                case "getAllStudents" -> {
                    // lấy ra list Student dạng chuỗi
                    String resultString = StringHandling.StringHandling.getStringStudent();
                    
                    String Class = DBAccess.getAllClassId();
                    
                    String result = resultString + "\n\n" + Class;
                    
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = result.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                case "getEmployeeById" -> {
                    // lấy ra list Employee dạng chuỗi
                    Employee employee = DBAccess.getEmployeeById(string);
                    String resultString = employee.toString();
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                case "updateEmployee" -> {
                    // ngắt chuỗi
                    String arr[] = string.split("\n");
                    //tạo biến employee
                    Employee employee = new Employee(arr[0], Integer.parseInt(arr[1]), arr[2], arr[3], arr[4], Integer.parseInt(arr[5]), arr[6], Boolean.parseBoolean(arr[7]), Integer.parseInt(arr[8]));
                    String resultString = DBAccess.updateEmployee(employee);
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                case "addClass" -> {
                    String arr[] = string.split("\n");
                    Class cl = new Class(arr[0], arr[1]);
                    String resultString = DBAccess.insertClass(cl);
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                case "updateClass" -> {
                    String arr[] = string.split("\n");
                    Class cl = new Class(arr[0], arr[1], Boolean.parseBoolean(arr[2]));
                    String resultString = DBAccess.updateClass(cl);
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                case "addStudent" -> {
                    String arr[] = string.split("\n");
                    Student student = new Student(arr[0], arr[1], arr[2],arr[3],arr[4], Integer.parseInt(arr[5]), arr[6]);
                    String resultString = DBAccess.insertStudent(student);
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                case "updateStudent" -> {
                    String arr[] = string.split("\n");
                    Student student = new Student(arr[0], arr[1], arr[2],arr[3],arr[4], Integer.parseInt(arr[5]), arr[6], Boolean.parseBoolean(arr[7]));
                    String resultString = DBAccess.updateStudent(student);
                    // chuyển thông tin về dạng byte
                    byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                    String inputBase64 = Base64.getEncoder().encodeToString(inputByte);
                    // gửi đi
                    out.println(inputBase64);
                }
                default -> {
                }
            }

        } catch (NumberFormatException e) {
            System.err.println(name + "has departed");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
