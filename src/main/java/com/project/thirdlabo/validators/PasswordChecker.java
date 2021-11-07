package com.project.thirdlabo.validators;

import java.util.Locale;

public class PasswordChecker {

    private static final String SPECIAL_CHARS = " !\"#$%&'()©*+,-/:;<=>?[\\]^_`{|}~";

    public boolean isNotShorter(String email, int minLength) {
        return email.length() >= minLength;
    }

    public boolean hasUpperCase(String email) {
        return !email.equals(email.toLowerCase());
    }

    public boolean hasSpecialChar(String email) {
        for (int i = 0; i < SPECIAL_CHARS.length(); i++) {
            char c = SPECIAL_CHARS.charAt(i);
            if (email.indexOf(c) > -1) {
                return true;
            }
        }
        return false;
    }
}
