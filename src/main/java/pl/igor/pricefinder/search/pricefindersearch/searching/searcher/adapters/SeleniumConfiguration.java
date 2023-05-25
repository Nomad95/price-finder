package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import lombok.Getter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumConfiguration {

    @Getter
    private WebDriver webDriver;
//
//    static {
//        System.setProperty("phantomjs.binary.path", findFile(""));
//    }

    public SeleniumConfiguration() {
        Capabilities capabilities = DesiredCapabilities.phantomjs();
        //capabilities.asMap().put("unhandledPromptBehavior", "dismiss");
        //capabilities.asMap().put("loggingPrefs", "browser");


        final ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-dev-shm-usage");
//        chromeOptions.setCapability("browserless:token", "YOUR-API-TOKEN");

        try {
            webDriver = new RemoteWebDriver(
                    new URL("http://localhost:3000/webdriver"),
                    chromeOptions
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    static private String findFile(String filename) {
        try {
            File file = ResourceUtils.getFile("classpath:webdriver/phantomjs");
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            return "";
        }
    }


}
