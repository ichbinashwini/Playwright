package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private String emailField = "#input-email";
    private String passwordField = "#input-password";
    private String forgotPasswordLink= "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
    private String loginButton=  "//input[@type='submit']";
    private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";
    private Page page;

    public LoginPage(Page page) {
    this.page= page;
    }

    public String getLoginPageTitle(){
        return page.title();

    }

    public boolean isForgotPWDLinkExists(){
      if( page.isVisible(forgotPasswordLink)){
          return true;
      } return false;
    }

    public boolean doLogin(String userName, String password) throws InterruptedException {
        page.fill(emailField, userName );
        page.fill(passwordField, password);
        page.click(loginButton);
        Thread.sleep(5000);
        if (page.isVisible(logoutLink)){
            System.out.println("User is successfully signed in");
            return true ;
        } return false;
    }


}
