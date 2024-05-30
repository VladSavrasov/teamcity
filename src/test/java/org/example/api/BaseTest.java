package org.example.api;

import org.assertj.core.api.SoftAssertions;
import org.example.api.generators.TestDataStorage;
import org.example.api.models.ServerAuthDettings.ServerAuthSettings;
import org.example.api.requests.CheckedRequests;
import org.example.api.requests.UncheckedRequests;
import org.example.api.requests.checked.ManageServerAuthSettingsReq;
import org.example.api.spec.Specifications;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected SoftAssertions softy;
    public TestDataStorage testDataStorage;
    public CheckedRequests checkedWithSuperUser = new CheckedRequests(Specifications.getSpec().superUserSpec());
    public UncheckedRequests uncheckedWithSuperUser = new UncheckedRequests(Specifications.getSpec().superUserSpec());


    @BeforeMethod
    public void beforeTest() {
        softy = new SoftAssertions();
        testDataStorage = TestDataStorage.getStorage();
    }

    @AfterMethod
    public void afterTest() {
        testDataStorage.delete();
        softy.assertAll();
    }



    @BeforeGroups("sistemtests")
    public void checkPermissionsTrueTest() {
        ManageServerAuthSettingsReq manageServerAuthSettingsReq = new ManageServerAuthSettingsReq(Specifications.getSpec().superUserSpec());
        ServerAuthSettings authenticationSettings = manageServerAuthSettingsReq.checkPermissions();
        Assert.assertTrue(authenticationSettings.isPerProjectPermissions());
    }
}
