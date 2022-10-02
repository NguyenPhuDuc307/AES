/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Data.DBAccess;
import Entity.Employee;
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

    public void run() {
        try {
            // lấy ra chuỗi byte input với một dòng
            String chuoi = in.nextLine().trim();

            // lấy chuỗi string từ chuỗi byte
            String inputString = new String(Base64.getDecoder().decode(chuoi), StandardCharsets.UTF_8);

            String[] arrString = inputString.split("\n");// ngắt chuỗi thành mảng chuỗi

            String funcString = String.valueOf(arrString[0].toCharArray());// ++ chứa yêu cầu

            String string = StringHandling.StringHandling.getString(arrString, 1, arrString.length);// ++ chứa nội dung

            if (funcString.equals("login")) {
                String[] arrLogin = string.split("\n");// ngắt chuỗi string
                String username = String.valueOf(arrLogin[0].toCharArray());// username

                String pass = String.valueOf(arrLogin[1].toCharArray());// pass
                String passEncrypt = AES.AES.encrypt(pass, "AES");

                User user = new User(username, passEncrypt);// Create user
                String result = DBAccess.Login(user);
                out.println(result);

            } else if (funcString.equals("register")) {
                String[] arrLogin = string.split("\n");// ngắt chuỗi string
                String username = String.valueOf(arrLogin[0].toCharArray());// username

                String pass = String.valueOf(arrLogin[1].toCharArray());// pass
                String passEncrypt = AES.AES.encrypt(pass, "AES");

                int role = Integer.parseInt(String.valueOf(arrLogin[2].toCharArray()));// role
                User user = new User(username, passEncrypt, role);// Create user

                // hàm register
                String result = DBAccess.Register(user);
                out.println(result);

            } else if (funcString.equals("getAllEmployee")) {

                // lấy ra list Employee dạng chuỗi
                String resultString = StringHandling.StringHandling.getStringEmployee();

                // chuyển thông tin về dạng byte
                byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                String inputBase64 = Base64.getEncoder().encodeToString(inputByte);

                // gửi đi
                out.println(inputBase64);
            } else if (funcString.equals("getEmployeeById")) {

                // lấy ra list Employee dạng chuỗi
                Employee employee = DBAccess.getEmployeeById(string);

                String resultString = employee.toString();

                // chuyển thông tin về dạng byte
                byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                String inputBase64 = Base64.getEncoder().encodeToString(inputByte);

                // gửi đi
                out.println(inputBase64);
            } else if (funcString.equals("updateEmployee")) {

                // ngắt chuỗi
                String arr[] = string.split("\n");
                
                //tạo biến employee
                Employee employee = new Employee(arr[0], Integer.parseInt(arr[1]),arr[2],arr[3],arr[4],Integer.parseInt(arr[5]),arr[6]);

                String resultString = DBAccess.updateEmployee(employee);

                // chuyển thông tin về dạng byte
                byte[] inputByte = resultString.getBytes(StandardCharsets.UTF_8);
                String inputBase64 = Base64.getEncoder().encodeToString(inputByte);

                // gửi đi
                out.println(inputBase64);
            }

        } catch (Exception e) {
            System.err.println(name + "has departed");
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }
}
