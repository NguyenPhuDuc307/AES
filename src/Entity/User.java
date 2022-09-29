/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author nguyenphuduc
 */
public class User {

    private int IdUser;
    private String Username;
    private String Password;
    private String FullName;

    public User() {
    }
    
    public User(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }
    
    public User(String Username, String Password, String FullName) {
        this.Username = Username;
        this.Password = Password;
        this.FullName = FullName;
    }

    public User(int IdUser, String Username, String Password, String FullName) {
        this.IdUser = IdUser;
        this.Username = Username;
        this.Password = Password;
        this.FullName = FullName;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    @Override
    public String toString() {
        return "User{" + "IdUser=" + IdUser + ", Username=" + Username + ", Password=" + Password + ", FullName=" + FullName + '}';
    }
}
