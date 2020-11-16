import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://github.com");
        mainPage = new MainPage(driver);

    }

    @Test
    //Проверка перехода на страницу авторизации при нажатии на кнопку "Sign in"
    public void signInTest(){
        LoginPage loginPage = mainPage.clickSignIn();
        String heading = loginPage.getHeadingText();
        Assert.assertEquals("Sign in to GitHub", heading);
    }

    @Test
    //Проверка отображения сообщения о том, что при создании аккаунта возникли проблемы
    public void registerFailTest(){
        SignUpPage signUpPage = mainPage.register("testuser", "testemail", "password1");
        String error = signUpPage.getMainErrorText();
        Assert.assertEquals("There were problems creating your account.", error);
    }

    @Test
    //Проверка поведения системы на ввод неверного e-mail
    public void signUpInvalidEmailTest(){
        SignUpPage signUpPage = mainPage.register("qeqwe", "qweq", "password1");
        String error = signUpPage.getEmailErrorText();
        Assert.assertEquals("Email is invalid or already taken", error);
    }


    @After
    public void tearDown(){
        driver.quit();
    }
}
