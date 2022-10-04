/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author nguyenphuduc
 */
public class Employee {

    private String EmployeeId;
    private int UserId;
    private String UserName;
    private String FullName ;
    private String Email ;
    private String PhoneNumber ;
    private int Sex ;
    private String Address ;
    private boolean Enable;

    public Employee() {
    }

    public Employee(String EmployeeId, int UserId, String FullName, String Email, String PhoneNumber, int Sex, String Address, boolean Enable) {
        this.EmployeeId = EmployeeId;
        this.UserId = UserId;
        this.FullName = FullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Sex = Sex;
        this.Address = Address;
        this.Enable = Enable;
    }
    
    public Employee(String EmployeeId, String UserName, String FullName, String Email, String PhoneNumber, int Sex, String Address, boolean Enable) {
        this.EmployeeId = EmployeeId;
        this.UserName = UserName;
        this.FullName = FullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Sex = Sex;
        this.Address = Address;
        this.Enable = Enable;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String EmployeeId) {
        this.EmployeeId = EmployeeId;
    }
    
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int Sex) {
        this.Sex = Sex;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public boolean isEnable() {
        return Enable;
    }

    public void setEnable(boolean Enable) {
        this.Enable = Enable;
    }

    @Override
    public String toString() {
        return EmployeeId + "\n" + UserId + "\n" + FullName + "\n" + Email + "\n" + PhoneNumber + "\n" + Sex + "\n" + Address + "\n" + Enable;
    }
}
