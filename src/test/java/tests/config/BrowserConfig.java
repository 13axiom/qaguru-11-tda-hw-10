package tests.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface BrowserConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browser.size")
    @DefaultValue("3840x2160")
    String browserSize();

    @Key("remote.browser")
    //@DefaultValue("selenoid.autotests.cloud/wd/hub")
    String remoteBrowser();


}
