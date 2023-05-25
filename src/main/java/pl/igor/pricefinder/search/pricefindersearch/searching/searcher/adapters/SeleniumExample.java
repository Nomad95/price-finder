package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.UserAgents;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumExample {
    private SeleniumConfiguration config;
    private String url = "https://nbsklep.pl/meskie/obuwie/biegowe";
//    private String url = "https://www.zalando.pl/obuwie-sportowe-mezczyzni/";
    private WebDriverWait webDriverWait;

    public SeleniumExample() {
        config = new SeleniumConfiguration();

        config.getWebDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.webDriverWait =  new WebDriverWait(config.getWebDriver(), 15, 15000);
        //WebDriverWait webDriverWait = new WebDriverWait(config.getWebDriver(), 1, 1000);
        //config.getWebDriver().get(url);
    }

    public void closeWindow() {
        this.config.getWebDriver().close();
    }

    public String getTitle() {
        return this.config.getWebDriver().getTitle();
    }

    public void getAboutBaeldungPage() {
        print();
        //clickAboutLink();
        //clickAboutUsLink();
    }

    private void print() {
        try (Playwright playwright = Playwright.create()) {
            BrowserType chromium = playwright.chromium();
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            BrowserType.LaunchPersistentContextOptions launchPersistentContextOptions = new BrowserType.LaunchPersistentContextOptions();
            launchPersistentContextOptions.setBypassCSP(true);
            launchPersistentContextOptions.setArgs(List.of("--disable-web-security"));
            Browser browser = chromium.launch(launchOptions);
            Page page = browser.newPage();

            HashMap<String, String> headers = new HashMap<>();
                    headers.put("authority", "ceneo.pl");
                    headers.put("method", "GET");
                    headers.put("path", "/api/frontend/pl/products/filtered/from_category/13033?withChildCategoryName=13033&withGoogleAnalyticsGtag=true&withVariantsAvailability=true&associations_sas%5B0%5D%5Bpurpose%5D=product_colours&associations_sas[0][filter][onlyVisibleOnListing]=true&page=2&limit=48");
                    headers.put("scheme", "https");
                    headers.put("accept", "application/json, text/plain, */*");
                    headers.put("accept-encoding", "gzip, deflate, br");
                    headers.put("accept-language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7");
                    headers.put("accept-version", "2");
                    headers.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MzExMjgxMjQsImp0aSI6IlZobERVMlB2MkRhU05XbGRPMXNMK05Qb2ZXYXlkWWI1UjBmd1wvRFJBTWxrPSIsImlzcyI6Im5ic2tsZXAucGwiLCJleHAiOjE2MzExMjk5MjQsImRhdGEiOnsiY3VzdG9tZXIiOiJhMjk4NWM0Yi1hMzNiLTQ0YmEtYmFkZS04NDQ1NWVlNDEzOWYifX0.3Z706AAaw4HDyflyRyVhIxAu4vAsXny8lnjP2JSCJZf0aZM6W417DOj6j9tVrPAbLDzMGoVFWKkht7NMOeCVwA");
                    headers.put("cookie", "_gcl_au=1.1.186566074.1631129024; kg-uuid=f14dcde1-3bd9-49d7-bae7-4fb7abe047e4; _snrs_uuid=14616674-841c-4485-ad37-941b61172954; _snrs_puuid=14616674-841c-4485-ad37-941b61172954; _snrs_sa=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&appear:1631129025&sessionVisits:8; _snrs_sb=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&leaves:1631129214; _snrs_p=host:nbsklep.pl&permUuid:14616674-841c-4485-ad37-941b61172954&uuid:14616674-841c-4485-ad37-941b61172954&identityHash:&user_hash:&init:1631129025&last:1631129025&current:1631129214&uniqueVisits:1&allVisits:3");
                    headers.put("dnt", "1");
                    headers.put("is-currency-code", "PLN");
                    headers.put("is-delivery-country-code", "pl");
                    headers.put("referer", "https://ceneo.pl");//TODO: ????;
                    headers.put("sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"");
                    headers.put("sec-ch-ua-mobile", "?0");
                    headers.put("sec-ch-ua-platform", "Linux");
                    headers.put("sec-fetch-dest", "empty");
                    headers.put("sec-fetch-mode", "cors");
                    headers.put("sec-fetch-site", "same-origin");
                    headers.put("sec-gpc", "1");
                    headers.put("user-agent", UserAgents.I.randomizeUserAgent());
                    headers.put("x-request-id", "fde4643b-f019-4fca-9efd-712aef6275e2");;

            page.setExtraHTTPHeaders(headers);
            page.navigate("https://www.google.com/search?q=New+Balance+1080+v12");
//            page.evaluate("\n" +
//                    "    setTimeout(() => {\n" +
//                    "      resolve('resolved');\n" +
//                    "    }, 10000);\n" +
//                    "  ");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            page.evaluate("" +
                    "for (let i=0; i < 6000; i++) {" +
                    "window.scrollBy(0,10);\n" +
                    "    scrolldelay = setTimeout(50);" +
                    "}");
//            page.evaluate("\n" +
//                    "    setTimeout(() => {\n" +
//                    "      resolve('resolved');\n" +
//                    "    }, 10000);\n" +
//                    "  ");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String content = page.content();
            System.out.println(content);
            // other actions...
            browser.close();
        }
        //JavascriptExecutor webDriver = (JavascriptExecutor) config.getWebDriver();
        //Object inner = webDriver.executeScript("return document.body.innerHTML;");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriverWait.until(webDr -> {
            JavascriptExecutor webDriver = (JavascriptExecutor) webDr;
            webDriver.executeScript("" +
                    "for (let i=0; i < 6000; i++) {" +
                    "window.scrollBy(0,10);\n" +
                    "    scrolldelay = setTimeout(50);" +
                    "}");
            return "dupa";
        } );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriverWait.until(webDr -> {
            JavascriptExecutor webDriver = (JavascriptExecutor) webDr;
            webDriver.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        return "dupa";
        } );
        String pageSource =  webDriverWait.until(webDr -> {
            WebElement body = webDr.findElement(By.tagName("body"));
            return body.getAttribute("innerHTML");
        } );

//        for (int i = 0; i < 100; i++) {
//            WebElement body = config.getWebDriver().findElement(By.tagName("body"));
//            pageSource = body.getAttribute("innerHTML");
//        }
        WebElement body = config.getWebDriver().findElement(By.tagName("body"));
        pageSource = body.getAttribute("innerHTML");
//        String outerHTML = element.getAttribute("outerHTML");
        //String pageSource = this.config.getWebDriver().getPageSource();
        System.out.println(pageSource);
        //config.getWebDriver().quit();
    }

    private void clickAboutLink() {
        Actions actions = new Actions(config.getWebDriver());
        WebElement aboutElement = this.config.getWebDriver()
                .findElement(By.id("menu-item-6138"));

        actions.moveToElement(aboutElement).perform();
    }

    private void clickAboutUsLink() {
        WebElement element = this.config.getWebDriver()
                .findElement(By.partialLinkText("About Baeldung."));
        element.click();
    }

}
