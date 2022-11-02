package com.mycompany.whiteboard;

/**
 *
 * @author juan
 */
public class Admin extends User {
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.accessLevel = 2;
    }
    
    public void registerUser() {
        //just a prototype
    }
}
