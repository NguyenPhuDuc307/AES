/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Data.DBAccess;
import Entity.Encrypt;
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

            // ngắt chuỗi thành mảng chuỗi
            String[] arrString = inputString.split("\n");

            // ++ chứa người gửi
            String iduser = String.valueOf(arrString[0].toCharArray());

            // ++ chứa yêu cầu
            String funcString = String.valueOf(arrString[1].toCharArray());

            // ++ chứa nội dung
            String key = String.valueOf(arrString[2].toCharArray());

            // ++ chứa nội dung
            String string = StringHandling.StringHandling.getString(arrString, 3, arrString.length);

            if (funcString.equals("encrypt")) {
                // mã hoá nội dung
                String enString = AES.AES.encrypt(string, key);

                // cập nhật vào database
                Encrypt encrypt = new Encrypt(string, enString, key);

                DBAccess.Encrypt(encrypt);

                out.println(enString);
            } else if (funcString.equals("decrypt")) {
                // giải mã nội dung
                String deString = AES.AES.decrypt(String.valueOf(string), String.valueOf(key));

                // chuyển nội dung về dạng byte
                String inputBase64 = null;

                String outputString = "";

                if (deString != null) {

                    // thực hiện đếm số lượng ký tự
                    String demString = StringHandling.StringHandling.frequencyCount(deString);

                    outputString = StringHandling.StringHandling.stringSoncatenation(demString, "\n", deString);

                    // chuyển nội dung về dạng byte
                    inputBase64 = StringHandling.StringHandling.getBase(outputString);

                } else {
                    // chuyển nội dung về dạng byte
                    inputBase64 = StringHandling.StringHandling.getBase("null");
                }
                out.println(inputBase64);
            } else if (funcString.equals("login")) {
                // ngắt chuỗi string
                String[] arrLogin = string.split("\n");

                // username
                String username = String.valueOf(arrLogin[0].toCharArray());

                // pass
                String pass = String.valueOf(arrLogin[1].toCharArray());

                // Create user
                User user = new User(username, pass);

                boolean result = DBAccess.Login(user);
                out.println(result);
            } else if (funcString.equals("getlist")) {
                // lấy ra danh sách encrypt
                String output = StringHandling.StringHandling.getStringEncrypt();

                // chuyển nội dung về dạng byte
                String inputBase64 = null;

                if (output != null) {
                    // chuyển nội dung về dạng byte
                    inputBase64 = StringHandling.StringHandling.getBase(output);
                } else {
                    inputBase64 = StringHandling.StringHandling.getBase("null");
                }
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
