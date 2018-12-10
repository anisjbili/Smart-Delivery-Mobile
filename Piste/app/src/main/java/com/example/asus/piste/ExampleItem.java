package com.example.asus.piste;

/**
 * Created by ASUS on 13/05/2018.
 */
public class ExampleItem {
    private String mClient;
    private String mX;
    private String mY;
    private String Code;
    private String Address;

    public ExampleItem(String mClient, String mX, String mY, String Code, String Address) {
        this.mClient = mClient;
        this.mX = mX;
        this.mY = mY;
        this.Code = Code;
        this.Address = Address;
    }

    public String getmClient() {
        return mClient;
    }

    public String getmX() {
        return mX;
    }

    public String getmY() {
        return mY;
    }

    public String getCode() {
        return Code;
    }

    public String getAddress() {
        return Address;
    }
}
