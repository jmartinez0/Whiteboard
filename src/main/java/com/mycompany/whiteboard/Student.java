package com.mycompany.whiteboard;

/**
 *
 * @author juan
 */
public class Student extends User {
    private String major;
    private String[] courses;
        
    public Student(String username, String password, String name, String major, String[] courses) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.major = major;
        this.courses = courses;
    }
    
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
