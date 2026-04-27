package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:local.properties",
        "classpath:${env}..properties"
})
public interface WebConfig extends Config {

    @Key("browserName")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("remoteUrl")
    String getRemoteURL();
}