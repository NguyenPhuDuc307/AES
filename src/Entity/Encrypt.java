/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nguyenphuduc
 */
public class Encrypt {

    private int IdEncrypt;
    private String UserCreated;
    private String DateTimeCreated;
    private String Encrypt;
    private String Key;

    public Encrypt() {
    }
    
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
    
    public Encrypt(String UserCreated, String Encrypt, String Key) {
        this.UserCreated = UserCreated;
        this.DateTimeCreated = df.format(dateobj);
        this.Encrypt = Encrypt;
        this.Key = Key;
    }

    public Encrypt(int IdEncrypt, String UserCreated, String Encrypt, String Key) {
        this.IdEncrypt = IdEncrypt;
        this.UserCreated = UserCreated;
        this.DateTimeCreated = df.format(dateobj);
        this.Encrypt = Encrypt;
        this.Key = Key;
    }

    public Encrypt(int IdEncrypt, String UserCreated, String DateTimeCreated, String Encrypt, String Key) {
        this.IdEncrypt = IdEncrypt;
        this.UserCreated = UserCreated;
        this.DateTimeCreated = DateTimeCreated;
        this.Encrypt = Encrypt;
        this.Key = Key;
    }

    public int getIdEncrypt() {
        return IdEncrypt;
    }

    public void setIdEncrypt(int IdEncrypt) {
        this.IdEncrypt = IdEncrypt;
    }

    public String getUserCreated() {
        return UserCreated;
    }

    public void setUserCreated(String UserCreated) {
        this.UserCreated = UserCreated;
    }

    public String getDateTimeCreated() {
        return DateTimeCreated;
    }

    public void setDateTimeCreated(String DateTimeCreated) {
        this.DateTimeCreated = DateTimeCreated;
    }

    public String getEncrypt() {
        return Encrypt;
    }

    public void setEncrypt(String Encrypt) {
        this.Encrypt = Encrypt;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    @Override
    public String toString() {
        return IdEncrypt + "\n" + UserCreated + "\n" + DateTimeCreated + "\n" + Encrypt + "\n" + Key;
    }
}
