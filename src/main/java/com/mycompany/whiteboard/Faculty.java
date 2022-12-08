package com.mycompany.whiteboard;

/**
 *
 * @author juan
 */
public class Faculty extends User {
    private String[] courses;

    public Faculty(String username, String password, String name, String[] courses) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.courses = courses;
    }
    
    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
       
}
