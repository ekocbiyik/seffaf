package com.payment.seffaf.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.exceptions.ValidationException;
import com.payment.seffaf.model.Gender;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * enbiya on 02.04.2019
 */
public class ValidationUtils {

    public static void phoneNumberValidation(String phoneNumber) throws ValidationException {
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(phoneNumber, "TR");
            if (!phoneUtil.isValidNumber(swissNumberProto)) {
                throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER, String.format("invalid parameter: %s", phoneNumber));
            }
        } catch (NumberParseException e) {
            e.printStackTrace();
            throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER, String.format("invalid parameter: %s", phoneNumber));
        }
    }

    public static void textValidation(String value) throws ValidationException {
        if (value.contains("[a-zA-Z]+") == false && value.length() < 3) {
            throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER, String.format("invalid parameter: %s", value));
        }
        if (value == null || value.isEmpty()) {
            throw new ValidationException(SeffafExceptionCode.REQUIRED_PARAMETER, String.format("required parameter: %s", value));
        }
    }

    public static void emailValidation(String email) throws ValidationException {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER, String.format("invalid parameter: %s", email));
        }
    }

}
