package com.codecool.shop.entity;

import org.mindrot.jbcrypt.BCrypt;

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
}
