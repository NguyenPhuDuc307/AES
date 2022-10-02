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
    private int UserId;
    private String ClassId ;
    private String FullName ;
    private String Email ;
    private String PhoneNumber ;
    private int Sex ;
    private String Address ;

    public Student() {
    }

    public Student(String StudentId, int UserId, String ClassId, String FullName, String Email, String PhoneNumber, int Sex, String Address) {
        this.StudentId = StudentId;
        this.UserId = UserId;
        this.ClassId = ClassId;
        this.FullName = FullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Sex = Sex;
        this.Address = Address;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
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

    @Override
    public String toString() {
        return "Student{" + "StudentId=" + StudentId + ", UserId=" + UserId + ", ClassId=" + ClassId + ", FullName=" + FullName + ", Email=" + Email + ", PhoneNumber=" + PhoneNumber + ", Sex=" + Sex + ", Address=" + Address + '}';
    }
}
