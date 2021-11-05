package com.project.thirdlabo;

public class EmailValidator {

    private static final String ILLEGAL_EMAIL_CHARS = " !\"#$%&'()©*+,-/:;<=>?[\\]^_`{|}~";

    public boolean hasEtaSymbol(String email) {
        return email.contains("@");
    }

    public boolean hasCorrectSymbols(String email) {
        for (int i = 0; i < ILLEGAL_EMAIL_CHARS.length(); i++) {
            char c = ILLEGAL_EMAIL_CHARS.charAt(i);
            if (email.indexOf(c) > -1) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCorrectDomain(String email) {
        int indexOfEta = email.indexOf('@');
        if (indexOfEta == -1) {
            return false;
        }

        String afterEta = email.substring(indexOfEta + 1);
        long countOfDots = afterEta.chars().filter(chr -> chr == '.').count();
        if (countOfDots < 1) {
            return false;
        }

        int indexOfDot = afterEta.indexOf('.');
        if (indexOfDot >= 60 || indexOfDot <= 2) {
            return false;
        }

        String afterDot = afterEta.substring(indexOfDot + 1);
        if (afterDot.length() >= 5 || afterDot.length() <= 1) {
            return false;
        }
        return true;
    }

}
