package com.qa.opencart.pages;


import com.microsoft.playwright.Page;

public class HomePage {

    private String searchField = "//input[@name='search']";
    private String searchIcon = "div#search button";
    private String searchPageHeader = "div#content h1";
    private String loginButton = "(//a[normalize-space()='Login'])[1]";
    public String myAccountBtn = "//span[normalize-space()='My Account']";
    private Page page;

    public HomePage(Page page){
        this.page=page;
    }

    public String getHomePageTiltle(){
        return page.title();
    }

    public String getHomePageURL(){
        return page.url();
    }

    public String doSearch(String productName){
        page.fill(searchField, productName);
        page.click(searchIcon);
        String header = page.textContent(searchPageHeader);
        return header;
    }

    public LoginPage navToLoginPage(){
        page.click(myAccountBtn);
        page.click(loginButton);
        return new LoginPage (page);

    }

}
