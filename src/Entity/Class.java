/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author nguyenphuduc
 */
public class Class {
    private String ClassIdString;
    private String ClassName;
    private boolean Enable;

    public Class() {
    }

    public Class(String ClassIdString, String ClassName) {
        this.ClassIdString = ClassIdString;
        this.ClassName = ClassName;
    }

    public Class(String ClassIdString, String ClassName, boolean Enable) {
        this.ClassIdString = ClassIdString;
        this.ClassName = ClassName;
        this.Enable = Enable;
    }

    
    public String getClassIdString() {
        return ClassIdString;
    }

    public void setClassIdString(String ClassIdString) {
        this.ClassIdString = ClassIdString;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public boolean isEnable() {
        return Enable;
    }

    public void setEnable(boolean Enable) {
        this.Enable = Enable;
    }
    
    

    @Override
    public String toString() {
        return ClassIdString + "\n" + ClassName + "\n" + Enable;
    }
    
    
}
