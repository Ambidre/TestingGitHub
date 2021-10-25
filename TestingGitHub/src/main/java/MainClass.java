import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://github.com");

        //MainPage mainPage = new MainPage(driver);
        //MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        //mainPage.register("amdftrye","rherhe@gdfh.com","dsfgdfh54fdhrdh");
    }
}
