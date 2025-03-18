package com.qa.opencart.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    private static ThreadLocal<Playwright> PlaywrightThread = new ThreadLocal<>();
    private static ThreadLocal<Browser> BrowserThread = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> BrowserContextThread = new ThreadLocal<>();
    private static ThreadLocal<Page> PageThread = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return PlaywrightThread.get();
    }

    public static Browser getBrowser() {
        return BrowserThread.get();
    }

    public static BrowserContext getBrowserContext() {
        return BrowserContextThread.get();
    }

    public static Page getPage() {
        return PageThread.get();
    }

    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Launching browser - " + browserName);

        // playwright = Playwright.create();
        PlaywrightThread.set(Playwright.create());

        switch (browserName.toLowerCase()) {

        case "chromium":
           // browser=  playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserThread.set( getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            break;

        case "firefox":
           // browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserThread.set( getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            break;
        case "safari":
           // browser= playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserThread.set( getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            break;
        case "chrome":
           // browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
            BrowserThread.set( getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
            break;

        default:
            System.out.println("Please pass the right browser name........");
            break;
    }

    /*
    * Creating a page here by using thread local of browserContext>>page same ad commented lines
    *
    * */

   // browserContext= browser.newContext();
        BrowserContextThread.set(getBrowser().newContext());
   // page = browserContext.newPage();
        PageThread.set(getBrowserContext().newPage());
   // page.navigate(prop.getProperty("url").trim());
        getPage().navigate(prop.getProperty("url").trim());

    return getPage();
}

public Properties init_properties() {

    try {
        FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
        prop = new Properties();
        prop.load(ip);

    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return prop;
}

    /**
     * Take screenshot
     */
    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/reports/screenshot/" + System.currentTimeMillis() + ".png";
        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }
}
