package tests.config;

import org.aeonbits.owner.ConfigFactory;

public class Project {
    public static BrowserConfig config = ConfigFactory.create(BrowserConfig.class, System.getProperties());

    public static boolean isRemoteWebDriver() {
        return !config.remoteBrowser().equals("");
    }
}
