/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author nguyenphuduc
 */
public class Transcript {

    private String StudentId;
    private String StudentName;
    private String ClassIdString;
    private String SubjectId;
    private String SubjectName;
    private String EmployeeId;
    private String EmployeeName;
    private float Transcripts;

    public Transcript() {
    }

    public Transcript(String StudentId, String SubjectId, String EmployeeId, float Transcripts) {
        this.StudentId = StudentId;
        this.SubjectId = SubjectId;
        this.EmployeeId = EmployeeId;
        this.Transcripts = Transcripts;
    }
    
    public Transcript(String StudentId, String StudentName, String SubjectId, String SubjectName, float Transcripts) {
        this.StudentId = StudentId;
        this.StudentName = StudentName;
        this.SubjectId = SubjectId;
        this.SubjectName = SubjectName;
        this.Transcripts = Transcripts;
    }

    public Transcript(String StudentId, String StudentName, String SubjectId, String SubjectName, String EmployeeId, float Transcripts) {
        this.StudentId = StudentId;
        this.StudentName = StudentName;
        this.SubjectId = SubjectId;
        this.SubjectName = SubjectName;
        this.EmployeeId = EmployeeId;
        this.Transcripts = Transcripts;
    }

    public Transcript(String StudentId, String StudentName, String ClassIdString, String SubjectId, String SubjectName, String EmployeeName, float Transcripts) {
        this.StudentId = StudentId;
        this.StudentName = StudentName;
        this.ClassIdString = ClassIdString;
        this.SubjectId = SubjectId;
        this.SubjectName = SubjectName;
        this.EmployeeName = EmployeeName;
        this.Transcripts = Transcripts;
    }
    
    

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String SubjectId) {
        this.SubjectId = SubjectId;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public float getTranscripts() {
        return Transcripts;
    }

    public void setTranscripts(float Transcripts) {
        this.Transcripts = Transcripts;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public String getClassIdString() {
        return ClassIdString;
    }

    public void setClassIdString(String ClassIdString) {
        this.ClassIdString = ClassIdString;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }


    @Override
    public String toString() {
        return StudentId + "\n" + StudentName +  "\n" + ClassIdString + "\n" + SubjectId + "\n" + SubjectName + "\n" + EmployeeName + "\n" + Transcripts;
    }
}
