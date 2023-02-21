package org.foxminded.rymarovych.service;

public class SingleQuotesWrapper {
    private SingleQuotesWrapper() {}

    public static String wrapInSingleQuotes(String str) {
        return String.format("'%s'", str);
    }
}
