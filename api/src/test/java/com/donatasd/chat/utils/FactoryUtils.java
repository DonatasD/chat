package com.donatasd.chat.utils;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class FactoryUtils {

    private static final int DEFAULT_STRING_LENGTH = 10;

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String alphanumericString() {
        return alphanumericString(DEFAULT_STRING_LENGTH);
    }

    public static String alphanumericString(int stringLength) {
        return RandomStringUtils.randomAlphabetic(stringLength);
    }

}
