package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.URL;

public class TestRemote {
    public static void main(String[] args) throws Exception {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}