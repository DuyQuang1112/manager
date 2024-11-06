package com.myproject.constant;

public class RegexConst {
    public static final String IDENTIFICATION_NUMBER_REGEX = "^[0-9]{12}$";
    public static final String PHONE_NUMBER_REGEX = "^[0-9]{10}$";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9._]{2,15}$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
}
