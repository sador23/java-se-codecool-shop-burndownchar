package com.codecool.shop.model;

import org.mindrot.jbcrypt.BCrypt;
import spark.Request;

import javax.persistence.*;

@Entity
@Table(name="user_info")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String psw;

    @JoinColumn(name="contact_id")
    @OneToOne
    private UserContact contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void login(Request req){
        req.session().attribute("user",this);
    }

    public void logout(Request req){
        req.session().removeAttribute("user");

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public User(){

    }

    public User(String name, String email, String psw){
        this.name=name;
        this.email=email;
        this.psw=psw;

    }

    public boolean authenticate_user(String password){
        return BCrypt.checkpw(password, this.psw);
    }

    public UserContact getContact() {
        return contact;
    }

    public void setContact(UserContact contact) {
        this.contact = contact;
    }
}
