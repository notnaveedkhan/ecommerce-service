package com.ecommerceservice.application.utility;

public class Util {

    public static String mask(String value, int visibleDigits) {
        int cardNumberLength = value.length();
        if (cardNumberLength <= visibleDigits) {
            return value;
        }
        String visiblePart = value.substring(cardNumberLength - visibleDigits);
        return "x".repeat(cardNumberLength - visibleDigits) +
                visiblePart;
    }

}
