package com.mycompany.whiteboard;

/**
 *
 * @author juan
 */
public class Student extends User {

    private String major;
    private String[] courses;
    
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
    
    
}
