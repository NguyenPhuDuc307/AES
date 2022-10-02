/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author nguyenphuduc
 */
public class Subject {

    private String SubjectId;
    private String ClassId;
    private String SubjectName;
    private int CreditsNumber;

    public Subject() {
    }

    public Subject(String SubjectId, String ClassId, String SubjectName, int CreditsNumber) {
        this.SubjectId = SubjectId;
        this.ClassId = ClassId;
        this.SubjectName = SubjectName;
        this.CreditsNumber = CreditsNumber;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String SubjectId) {
        this.SubjectId = SubjectId;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String ClassId) {
        this.ClassId = ClassId;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public int getCreditsNumber() {
        return CreditsNumber;
    }

    public void setCreditsNumber(int CreditsNumber) {
        this.CreditsNumber = CreditsNumber;
    }

    @Override
    public String toString() {
        return "Subject{" + "SubjectId=" + SubjectId + ", ClassId=" + ClassId + ", SubjectName=" + SubjectName + ", CreditsNumber=" + CreditsNumber + '}';
    }
}
