import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://github.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    //Попытка входа с пустыми полями
    public void loginWithEmptyCredsTest(){
        LoginPage newLoginPage = loginPage.loginWithInvalidCreds("","");
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    //Попытка входа с неверными значениями
    public void loginWithIncorrectCredsTest(){
        LoginPage newLoginPage = loginPage.loginWithInvalidCreds("qweqwe","qweqwe");
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    //Переход на страницу создания аккаунта
    public void createAccTest(){
        SignUpPage signUpPage = loginPage.createAccount();
        String headingSignIn = signUpPage.getHeadingSignInText();
        Assert.assertEquals("Welcome to GitHub!", headingSignIn);
    }


    @After
    public void tearDown(){
        driver.quit();
    }
}
