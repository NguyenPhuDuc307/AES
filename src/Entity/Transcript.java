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
    private String SubjectId;
    private String EmployeeId;
    private float Transcripts;

    public Transcript() {
    }

    public Transcript(String StudentId, String SubjectId, String EmployeeId, float Transcripts) {
        this.StudentId = StudentId;
        this.SubjectId = SubjectId;
        this.EmployeeId = EmployeeId;
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

    @Override
    public String toString() {
        return "Transcript{" + "StudentId=" + StudentId + ", SubjectId=" + SubjectId + ", EmployeeId=" + EmployeeId + ", Transcripts=" + Transcripts + '}';
    }
}
