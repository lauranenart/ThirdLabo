package com.project.thirdlabo.validators;

public class PhoneValidator {

    public boolean numberMatchesPrefix(String phoneNumber, String prefix) {
        switch(prefix) {
            case "LT":
                if (phoneNumber.length() != 9 ) {
                    return false;
                }
                break;
            case "US":
                if (phoneNumber.length() != 12) {
                    return false;
                }
                break;
        }
        return true;
    }

    public boolean hasOnlyNumbers(String phoneNumber, String prefix) {
        int plusIndex = phoneNumber.indexOf('+');

        if (plusIndex == 0) {
            String numberAfterPlus = phoneNumber.substring(plusIndex + 1);
            for (int i = 0; i < numberAfterPlus.length(); i++) {
                if (numberAfterPlus.charAt(i) > '9' && numberAfterPlus.charAt(i) < 0) {
                    return false;
                }
            }
        }
        else {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (phoneNumber.charAt(i) >= '0' && phoneNumber.charAt(i) <= '9') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
