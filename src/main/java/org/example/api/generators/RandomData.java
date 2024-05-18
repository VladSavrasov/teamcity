package org.example.api.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    private static final int LENGTH = 10;
    public static String randomStringForIdsAndNames ;

    public static String getString() {
        return "test" + RandomStringUtils.randomAlphabetic(LENGTH);
    }

    public static void setRandomStringForIdsAndNames() {
        randomStringForIdsAndNames = "test" + RandomStringUtils.randomAlphabetic(LENGTH);
    }
    public static String getRandomStringForIdsAndNames() {
        if(randomStringForIdsAndNames == null){
            setRandomStringForIdsAndNames();
        }
            return randomStringForIdsAndNames;
    }

    public static String getStringOfSomeChar(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }
}
