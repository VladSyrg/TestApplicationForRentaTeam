package com.syrgdev.testapplicationforrentateam.network;


import java.util.List;

public class ResponseSchema {

    List<UserSchema> data;

    public ResponseSchema(List<UserSchema> data) {
        this.data = data;
    }

    public List<UserSchema> getData() {
        return data;
    }
}
