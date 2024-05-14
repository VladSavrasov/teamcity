package org.example.api.generators;

import org.testng.annotations.DataProvider;

public class DataProvidersForParametrizedTests {

    @DataProvider
    public static Object[] nums() {
        return new Object[]{1,254,255};
    }

    @DataProvider
    public static Object[] badNums() {
        return new Object[]{0,256};
    }
}
