package com.payment.seffaf.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.exceptions.ValidationException;
import com.payment.seffaf.model.Gender;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IBANValidator;

/**
 * enbiya on 02.04.2019
 */
public class ValidationUtils {

    public static void UUIDValidation(String uuid) throws ValidationException {
        if (!uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER, String.format("invalid parameter: %s", uuid));
        }
    }

    public static void IBANValidation(String iban) throws ValidationException {
        IBANValidator instance = IBANValidator.getInstance();
        if (!instance.isValid(iban)) {
            throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER, String.format("invalid parameter: %s", iban));
        }
    }

    public static void creditCardValidation(String cardNumber) throws ValidationException {
        CreditCardValidator ccv = new CreditCardValidator();
        if (!ccv.isValid(cardNumber)) {
            throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER, String.format("invalid parameter: %s", cardNumber));
        }
    }

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
