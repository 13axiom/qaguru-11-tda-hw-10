package tests.config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.Sources("classpath:auth/auth.properties")
public interface AuthConfig extends Config {
    @Key("user")
    String username();

    @Key("pass")
    String password();
}
