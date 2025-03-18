package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {


    @Test(priority = 1)
    public void testHomePagetitle(){
     String actualTitle = homePage.getHomePageTiltle();
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);

    }
    @Test (priority = 2)
    public void testHomePageURL(){
        String actualURL = homePage.getHomePageURL();
        Assert.assertEquals(actualURL, prop.getProperty("url"));
    }
    @DataProvider
    public Object[][] getProductData(){
        return new Object[][]{
        {"Mac"},
        {"iPhone"},
        {"iPad"}
        };
    }

    @Test (priority = 3, dataProvider = "getProductData")
    public void testDoSearch(String productName){
        String actualHeader = homePage.doSearch(productName);
        Assert.assertEquals(actualHeader, "Search - "+ productName);

    }



}
