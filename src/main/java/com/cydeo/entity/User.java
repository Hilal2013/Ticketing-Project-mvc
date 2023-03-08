package com.cydeo.entity;

import com.cydeo.enums.Gender;

import java.time.LocalDateTime;

public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean enabled;//will use for security//when we create a new use we need a confirmation mechanism
    //enable is false user didnt confirm yet
    private String phone;
    private Role role;
    private Gender gender;

    public User(Long id, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String firstName, String lastName, String userName, String passWord, boolean enabled, String phone, Role role, Gender gender) {
        super(id, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
        this.phone = phone;
        this.role = role;
        this.gender = gender;
    }
}
