package app.ij.listviews.Models;

import java.io.Serializable;

public class User implements Serializable {

    private String uid;
    private String name;
    private String email;

    public User() {
    }

    // Instantiates a new User.

    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    // Gets uid.

    public String getUid() {
        return uid;
    }

    //Sets uid.

    public void setUid(String uid) {
        this.uid = uid;
    }

    // Gets name.

    public String getName() {
        return name;
    }

    // Sets name.


    public void setName(String name) {
        this.name = name;
    }

    //gets email
    public String getEmail() {
        return email;
    }

    //Sets email.

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
