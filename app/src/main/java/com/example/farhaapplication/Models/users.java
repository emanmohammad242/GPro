package com.example.farhaapplication.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class users {


    private int id_user;
    private String phone_num ;
    private String email;
    private String password ;
    private String address;
    private int login_state;
    private String full_name;

    public users(int id_user, String phone_num, String email
            , String password, String address, int login_state, String full_name) {
        this.id_user = id_user;
        this.phone_num = phone_num;
        this.email = email;
        this.password = password;
        this.address = address;
        this.login_state = login_state;
        this.full_name = full_name;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLogin_state() {
        return login_state;
    }

    public void setLogin_state(int login_state) {
        this.login_state = login_state;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }


    @Override
    public String toString() {
        return "users{" +
                "id_user=" + id_user +
                ", phone_num='" + phone_num + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", login_state=" + login_state +
                ", full_name='" + full_name + '\'' +
                '}'+"\n";
    }
}
