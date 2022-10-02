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

    private int UserId;
    private String Username;
    private String Password;
    private int Roles;

    public User() {
    }
    
    public User(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }
    
    public User(String Username, String Password, int Roles) {
        this.Username = Username;
        this.Password = Password;
        this.Roles = Roles;
    }


    public User(int UserId, String Username, String Password, int Roles) {
        this.UserId = UserId;
        this.Username = Username;
        this.Password = Password;
        this.Roles = Roles;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
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
    
    public int getRoles() {
        return Roles;
    }

    public void setRoles(int Roles) {
        this.Roles = Roles;
    }

    @Override
    public String toString() {
        return "User{" + "UserId=" + UserId + ", Username=" + Username + ", Password=" + Password + ", Roles=" + Roles + '}';
    }
}
