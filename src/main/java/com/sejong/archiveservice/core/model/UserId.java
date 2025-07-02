package com.sejong.archiveservice.core.model;

public class UserId {

    private String value;

    private UserId(String value) {
        this.value = value;
    }

    public static UserId of(String value) {
        return new UserId(value);
    }

    public String userId() { return value; }
}
