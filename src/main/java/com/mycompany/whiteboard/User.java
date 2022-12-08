package com.mycompany.whiteboard;

/**
 *
 * @author juan
 */
public class User {
    String username;
    String password;
    String name;
    String email;
    String type;

    public User() {
        username = "";
        password = "";
        name = "";
    }
    
    public User(String username, String email, String name, String type) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.type = type;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } 
    
}
