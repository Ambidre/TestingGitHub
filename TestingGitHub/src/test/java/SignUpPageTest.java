import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignUpPageTest {
    private WebDriver driver;
    private SignUpPage signUpPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://github.com/join");
        signUpPage= new SignUpPage(driver);
    }

    @Test
    //Проверка того, что мы находимся на верной странице
    public void getHeadingTest(){
        String heading = signUpPage.getHeadingText();
        Assert.assertEquals("Create your account", heading);
    }

    @Test
    //Проверка ввода пароля, который не удовлетворяет требованиям
    public void signUpWithShortPassTest(){
        SignUpPage sp = signUpPage.typePassword("qwe");
        String error = sp.getPasswordErrorText();
        Assert.assertEquals("Password is too short (minimum is 8 characters), needs at least 1 number, and is in a list of passwords commonly used on other websites", error);
    }

    @Test
    //Регистрация на email, который уже зарегистрирован
    public void signUpWithExistingEmailTest(){
        SignUpPage sp = signUpPage.typeEmail("mstitelnica1999@gmail.com");
        String error = sp.getEmailErrorText();
        Assert.assertEquals("Email is invalid or already taken", error);
    }

    @Test
    //Регистрация с логином пользовтаеля "username"
    public void signUpReservedUsernameTest(){
        String username = "username";
        SignUpPage sp = signUpPage.typeUserName(username);
        String error = sp.getUsernameErrorText();
        Assert.assertEquals("Username '"+username +"' is unavailable.", error);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
