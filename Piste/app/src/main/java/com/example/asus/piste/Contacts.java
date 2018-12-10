package com.example.asus.piste;

/**
 * Created by ASUS on 22/04/2018.
 */

public class Contacts {
    private String name,email,mobile;

    public Contacts(String name, String email, String mobile)
    {
        this.setName(name);
        this.setEmail(email);
        this.setMobile(mobile);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
