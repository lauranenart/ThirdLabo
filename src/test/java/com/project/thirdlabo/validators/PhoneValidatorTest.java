package com.project.thirdlabo.validators;

import com.project.thirdlabo.validators.PhoneValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    PhoneValidator phoVal = new PhoneValidator();

    @Test
    void testHasOnlyNumbers() {
        assertTrue(phoVal.hasOnlyNumbers("+37068543265","LT"));
    }

    @Test
    void testHasOnlyNumbersFail() {
        assertFalse(phoVal.hasOnlyNumbers("LabaiSlaptas","LT"));
    }

    @Test
    void testPhoneNumberVariant() {
        assertTrue(phoVal.numberMatchesPrefix("868156265","LT"));
    }

    @Test
    void testPhoneNumberVariantFail() {
        assertFalse(phoVal.numberMatchesPrefix("86816","LT"));
    }

    @Test
    void testDifferentCountry() {
        assertTrue(phoVal.numberMatchesPrefix("+12345678912","US"));
    }

    @Test
    void testDifferentCountryFail() {
        assertFalse(phoVal.numberMatchesPrefix("+123456789","LT"));
    }

}