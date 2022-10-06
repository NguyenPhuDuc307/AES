/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author nguyenphuduc
 */
public class Student {

    private String StudentId;
    private String ClassId ;
    private String FullName ;
    private String Email ;
    private String PhoneNumber ;
    private int Sex ;
    private String Address ;
    private boolean Enable;

    public Student() {
    }

    public Student(String StudentId, String ClassId, String FullName, String Email, String PhoneNumber, int Sex, String Address) {
        this.StudentId = StudentId;
        this.ClassId = ClassId;
        this.FullName = FullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Sex = Sex;
        this.Address = Address;
    }

    public Student(String StudentId, String ClassId, String FullName, String Email, String PhoneNumber, int Sex, String Address, boolean Enable) {
        this.StudentId = StudentId;
        this.ClassId = ClassId;
        this.FullName = FullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Sex = Sex;
        this.Address = Address;
        this.Enable = Enable;
    }
    
    

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String ClassId) {
        this.ClassId = ClassId;
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
        return StudentId + "\n" + ClassId + "\n" + FullName + "\n" + Email + "\n" + PhoneNumber + "\n" + Sex + "\n" + Address + "\n" + Enable;
    }
}
