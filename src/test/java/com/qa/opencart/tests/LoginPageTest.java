package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    private String LOGIN_PAGE_TITLE = "Account Login";

    @Test(priority = 1)
    public void verifyLoginPageTitle() {
        loginPage = homePage.navToLoginPage();
        String actualLoginPageTitle = loginPage.getLoginPageTitle();
        System.out.println("Login page title = " + actualLoginPageTitle);
        Assert.assertEquals(actualLoginPageTitle, LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void verifyIfForgotPWDLinkVisible() {
        if (loginPage.isForgotPWDLinkExists()) {
            System.out.println("Forgot password link is visible");
        } else System.out.println("Forgot password link is NOT visible");

    }

    @Test(priority = 3)
    public void verifyIfLoginWorks() throws InterruptedException {
        Assert.assertTrue(loginPage.doLogin("ashwinidn0143+1@gmail.com","12345678"));
    }


}
