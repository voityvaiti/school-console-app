package org.foxminded.rymarovych.service;

public class SingleQuotesWrapper {
    public static String wrapInSingleQuotes(String str) {
        return String.format("'%s'", str);
    }
}
