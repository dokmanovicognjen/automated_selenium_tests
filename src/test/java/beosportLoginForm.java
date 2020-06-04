import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.AccessibleObject;

public class beosportLoginForm {

    //Initialization of Selenium WebDriver

    public static final WebDriver driver = new ChromeDriver();

    //Global define WebDriver wait

    WebDriverWait wait = new WebDriverWait(driver, 10);

    //URL env
    String envURL = "https://rs.beosport.com/";

    //Locators
    String buttonNalog = "a.skip-link.skip-account";
    String buttonAccountOnHomePage = "Prijavi se";
    String buttonPrijaviSeOnLoginPage = "send2";
    String fieldEmail = "email";
    String fieldPassword = "pass";
    String fieldValidationMsgEmailEmpty = "advice-required-entry-email";
    String fieldValidationMsgPasswordEmpty = "advice-required-entry-pass";
    String fieldValidationMsgEmailWrongFormat = "advice-validate-email-email";
    String fieldValidationMsgPasswordWithLessOf6Ch = "advice-validate-password-pass";
    String fieldValidationMsgWrongData = "li.error-msg";
    String fieldMyAccount = "div.page-title";

    //Validation Messages
    String validationMsgEmptyField = "Ovo polje je obavezno.";
    String validationMsgEmailWrongFormat = "Molimo unesite ispravnu email adresu. Na primer johndoe@domain.com.";
    String validationMsgPasswordWithLessOf6Ch = "Molimo unesite 6 ili više karaktera bez praznih polja na početku i kraju.";
    String validationMsgWrongData = "Nevažeća prijava ili lozinka.";
    String validationMsgMyAccount = "Moj nalog";

    //Test data
    String validEmail = "dokmanovic.ognjen@gmail.com";
    String validPassword = "proba123";
    String wrongEmail = "dokmanovic@gmail.com";
    String wrongPassword = "proba12345";
    String wrongFormatEmail = "dokmanovic.ognjen@gmail";
    String passwordWithLessOf6Ch = "proba";

    @Before
    public void openApplication() {

        //Open URL Environment
        driver.get(envURL);

        //Maximize windows
        driver.manage().window().maximize();
        System.out.println("01. Successfully open home page");

        //Logic for wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(buttonNalog)));

        //Click on Account on home page
        driver.findElement(By.cssSelector(buttonNalog)).click();
        System.out.println("02. Successfully open dropdown menu");

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(buttonAccountOnHomePage)));

        //Click on SingIn option from dropdown menu
        driver.findElement(By.linkText(buttonAccountOnHomePage)).click();
        System.out.println("03. Successfully click on SignIn in dropdown menu");

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonPrijaviSeOnLoginPage)));
        System.out.println("04. I can see Log in form page");
    }

    //01.TC- The one where user submits all required fields empty
    @Test
    public void Test01() throws Exception {

        //Click on Login button on Login page
        driver.findElement(By.id(buttonPrijaviSeOnLoginPage)).click();
        System.out.println("05. I click on Login button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldValidationMsgEmailEmpty)));

        //Verify that alert message in present
        if (driver.findElement(By.id(fieldValidationMsgEmailEmpty)) != null && driver.findElement(By.id(fieldValidationMsgPasswordEmpty)) != null) {

            String actualValidationMsgEmptyEmailField = driver.findElement(By.id(fieldValidationMsgEmailEmpty)).getText();
            Assert.assertEquals(validationMsgEmptyField, actualValidationMsgEmptyEmailField);

            String actualValidationMsgEmptyPasswordField = driver.findElement(By.id(fieldValidationMsgPasswordEmpty)).getText();
            Assert.assertEquals(validationMsgEmptyField, actualValidationMsgEmptyPasswordField);
            System.out.println("06.Validation message is present and user can't access on his account");

        } else {

            throw new Exception("06. ERROR Validation message isn't present");
        }

    }

    //02.TC- The one where user submits valid data for Email and leave empty Password field
    @Test
    public void Test02() throws Exception {

        //Enter valid data for Email address
        driver.findElement(By.id(fieldEmail)).sendKeys(validEmail);
        System.out.println("05.I enter valid email");

        //Click on Login button on Login page
        driver.findElement(By.id(buttonPrijaviSeOnLoginPage)).click();
        System.out.println("06. I click on Login button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldValidationMsgPasswordEmpty)));

        //Verify that alert message is present
        if (driver.findElement(By.id(fieldValidationMsgPasswordEmpty)) != null) {

            String actualvalidationMsgEmptyPasswordField = driver.findElement(By.id(fieldValidationMsgPasswordEmpty)).getText();
            Assert.assertEquals(validationMsgEmptyField, actualvalidationMsgEmptyPasswordField);
            System.out.println("07. Validation message is present and user can't access on his account");

        } else {

            throw new Exception("07. ERROR Validation message isn't present");
        }

    }

    //03.TC- The one where user submits empty Email field and valid data for Password
    @Test
    public void Test03() throws Exception {

        //Enter valid data for Password
        driver.findElement(By.id(fieldPassword)).sendKeys(validPassword);
        System.out.println("05. I enter valid password");

        //Click on Login button on Login page
        driver.findElement(By.id(buttonPrijaviSeOnLoginPage)).click();
        System.out.println("06. I click on login button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldValidationMsgEmailEmpty)));

        //Verify that alert message is present
        if (driver.findElement(By.id(fieldValidationMsgEmailEmpty)) != null) {

            String actualValidationMsgEmptyEmailField = driver.findElement(By.id(fieldValidationMsgEmailEmpty)).getText();
            Assert.assertEquals(validationMsgEmptyField, actualValidationMsgEmptyEmailField);
            System.out.println("07. Validation message is present and user can't access on his account");

        } else {

            throw new Exception("07. Validation message isn't present");
        }
    }

    //04.TC- The one where user submits wrong format for Email
    @Test
    public void Test04() throws Exception {

        //Enter wrong format for Email address
        driver.findElement(By.id(fieldEmail)).sendKeys(wrongFormatEmail);
        System.out.println("05. I enter wrong format for email");

        //Enter valid data for Password
        driver.findElement(By.id(fieldPassword)).sendKeys(validPassword);
        System.out.println("06. I enter valid password");

        //Click on Login button on Login page
        driver.findElement(By.id(buttonPrijaviSeOnLoginPage)).click();
        System.out.println("07. I click on Login button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldValidationMsgEmailWrongFormat)));

        //Verify that alert message is present
        if (driver.findElement(By.id(fieldValidationMsgEmailWrongFormat)) != null) {

            String actualValidationMsgEmailWrongFormat = driver.findElement(By.id(fieldValidationMsgEmailWrongFormat)).getText();
            System.out.println("08. Validation message is present and user can't access on his account");
            Assert.assertEquals(validationMsgEmailWrongFormat, actualValidationMsgEmailWrongFormat);

        } else {

            throw new Exception("08. Validation message isn't present");
        }
    }

    //05.TC- The one where user submits Password with less of six characters
    @Test
    public void Test05() throws Exception {

        //Enter valid data for Email address
        driver.findElement(By.id(fieldEmail)).sendKeys(validEmail);
        System.out.println("05. I enter valid email");

        //Enter Password with less of six characters
        driver.findElement(By.id(fieldPassword)).sendKeys(passwordWithLessOf6Ch);
        System.out.println("06. I enter password with less of six characters");

        //Click on Login button on Login page
        driver.findElement(By.id(buttonPrijaviSeOnLoginPage)).click();
        System.out.println("07. I click on Login button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldValidationMsgPasswordWithLessOf6Ch)));

        //Verify that alert message is present
        if (driver.findElement(By.id(fieldValidationMsgPasswordWithLessOf6Ch)) != null) {

            String actualValidaionMsgPasswordWithLessOf6Ch = driver.findElement(By.id(fieldValidationMsgPasswordWithLessOf6Ch)).getText();
            System.out.println("08. Validation message is present and user can't access on his account");
            Assert.assertEquals(validationMsgPasswordWithLessOf6Ch, actualValidaionMsgPasswordWithLessOf6Ch);

        } else {

            throw new Exception("08. Validation message isn't present");
        }
    }

    //06.TC- The one where user submits wrong data for all required fields
    @Test
    public void Test06() throws Exception {

        //Enter wrong data for email address
        driver.findElement(By.id(fieldEmail)).sendKeys(wrongEmail);
        System.out.println("05. I enter wrong email");

        //Enter wrong data for Password
        driver.findElement(By.id(fieldPassword)).sendKeys(wrongPassword);
        System.out.println("06. I enter wrong password");

        //Click on Login button on Login page
        driver.findElement(By.id(buttonPrijaviSeOnLoginPage)).click();
        System.out.println("07. I click on login button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fieldValidationMsgWrongData)));

        //Verify that alert message is present
        if (driver.findElement(By.cssSelector(fieldValidationMsgWrongData)) != null) {

            String actualValidationMsgWrongData = driver.findElement(By.cssSelector(fieldValidationMsgWrongData)).getText();
            Assert.assertEquals(validationMsgWrongData, actualValidationMsgWrongData);
            System.out.println("08. Validation message is present and user can't access on his account");

        } else {

            throw new Exception("08. ERROR Validation message isn't present");
        }
    }

    //07.TC- The one where user submits valid data for all required fields
    @Test
    public void Test07() throws Exception {

        //Enter valid data for Email
        driver.findElement(By.id(fieldEmail)).sendKeys(validEmail);
        System.out.println("05. I enter valid Email");

        //Enter valid data for Password
        driver.findElement(By.id(fieldPassword)).sendKeys(validPassword);
        System.out.println("06. I enter valid Password");

        //Click on Login button on Login page
        driver.findElement(By.id(buttonPrijaviSeOnLoginPage)).click();
        System.out.println("07. I click on login button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fieldMyAccount)));

        //Verify that My account page is open
        if (driver.findElement(By.cssSelector(fieldMyAccount)) != null) {

            String actualValidationMsgMyAccount = driver.findElement(By.cssSelector(fieldMyAccount)).getText();
            Assert.assertEquals(validationMsgMyAccount, actualValidationMsgMyAccount);
            System.out.println("08. My account page is open and the user can use his account");

        } else {

            throw new Exception("08. ERROR My account page isn't open");
        }
        driver.close();
    }

}
