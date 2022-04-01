package tests.config;
import org.aeonbits.owner.Config;

public interface ApiConfig extends Config {

    @Key("baseUrl")
    @org.aeonbits.owner.Config.DefaultValue("https://demoqa.com")
    String baseUrl();

    @Key("apiToken")
    String apiToken();


}
