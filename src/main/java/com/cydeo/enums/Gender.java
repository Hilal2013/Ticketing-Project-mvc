package com.cydeo.enums;

public enum Gender {
    MALE("Male"),FEMALE("Female");//if you want to give special name //i dont want capital

private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
