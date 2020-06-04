import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class emezzetaRegistrationForm {

    //Initialization of Selenim WebDriver
    public static final WebDriver driver = new ChromeDriver();

    //Define WebDriver wait
    WebDriverWait wait = new WebDriverWait(driver, 10);

    //Global variables - Locators
    String urlEnv = "https://www.emmezeta.rs/customer/account/create/";
    String buttonCreateAnAccount = "button.action.submit.primary.account-button";
    String emailField = "email_address";
    String passwordField = "password";
    String confirmationPasswordField = "password-confirmation";
    String nameField = "firstname";
    String lastNameField = "lastname";
    String validationMsgForEmptyEmailField = "email_address-error";
    String validationMsgForEmptyPasswordField = "password-error";
    String validationMsgForEmptyConfirmationPasswordField = "password-confirmation-error";
    String validationMsgForEmptyNameField = "firstname-error";
    String validationMsgForEmptyLastNameField = "lastname-error";
    String validationMsgForPasswordWithLessOf8Ch = "password-error";
    String validationMsgForDifferentConfirmationPassword = "password-confirmation-error";
    String buttonDozvoliKolacice ="btn-cookie-allow";


    //Global variables - Expected results
    String expectedResultForRequiredEmptyFields = "Ovo je neophodno polje.";
    String expectedResultForPasswordWithLessOf8Ch = "Minimalna dužina ovog polja mora biti jednaka ili veća od 8 simbola. Prazni prostori biti će zanemareni.";
    String expectedResultForDifferentConfirmationPassword = "Molimo unesite ponovno istu vrednost";



    //Gloval variables - Test data
    String validDataForEmail = "dokmanovic.ognjen@gmail.com";
    String validDataForPassword = "dokmandokman";
    String passwordWithLessOf8Ch = "dokman";
    String confirmationPasswordDifferentFromPassword = "dokmandokman123";
    String validDataForName = "Ognjen";
    String validDataForLastName = "Dokmanovic";



    //Actions which are propagated on all tests
    @Before
    public void OpenApplication() {

        //Open URL Environment
        driver.get(urlEnv);

        //Maximize windows
        driver.manage().window().maximize();
        System.out.println("01. Successfully open Registration page");


    }

    //01.TC- The one where user submits all required fields empty
    @Test
    public void Test01() throws Exception{

        //Wait element
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonDozvoliKolacice)));
        Thread.sleep(5000);

        //Click on Dozvoli kolacice button
        driver.findElement(By.id(buttonDozvoliKolacice)).click();

        //Click on Create an account button
        driver.findElement(By.cssSelector(buttonCreateAnAccount)).click();
        System.out.println("02. I click on Create an account button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(validationMsgForEmptyEmailField)));

        //Verify that alert message is present
        if (driver.findElement(By.id(validationMsgForEmptyEmailField)) !=null && driver.findElement(By.id(validationMsgForEmptyPasswordField)) !=null && driver.findElement(By.id(validationMsgForEmptyConfirmationPasswordField)) !=null && driver.findElement(By.id(validationMsgForEmptyNameField)) !=null && driver.findElement(By.id(validationMsgForEmptyLastNameField)) !=null){

            String actualResultForValidationMsgEmptyEmailField = driver.findElement(By.id(validationMsgForEmptyEmailField)).getText();
            String actualResultForValidationMsgEmptyPasswordField = driver.findElement(By.id(validationMsgForEmptyPasswordField)).getText();
            String actualResultForValidationMsgEmptyConfirmationPasswordField = driver.findElement(By.id(validationMsgForEmptyConfirmationPasswordField)).getText();
            String actualResultForValidationMsgEmptyNameField = driver.findElement(By.id(validationMsgForEmptyNameField)).getText();
            String actualResultForValidationMsgEmptyLastNameField = driver.findElement(By.id(validationMsgForEmptyLastNameField)).getText();

            Assert.assertEquals(expectedResultForRequiredEmptyFields, actualResultForValidationMsgEmptyEmailField);
            Assert.assertEquals(expectedResultForRequiredEmptyFields, actualResultForValidationMsgEmptyPasswordField);
            Assert.assertEquals(expectedResultForRequiredEmptyFields, actualResultForValidationMsgEmptyConfirmationPasswordField);
            Assert.assertEquals(expectedResultForRequiredEmptyFields, actualResultForValidationMsgEmptyNameField);
            Assert.assertEquals(expectedResultForRequiredEmptyFields, actualResultForValidationMsgEmptyLastNameField);
            System.out.println("03. Validation messages are present and user can't create new account");

        }else {

            throw new Exception("03. ERROR Validation messages aren't present");
        }

        }


    //07.TC- The one where user submits Password with less of eight characters
    @Test
    public void Test07() throws Exception{

        //Wait element
        Thread.sleep(5000);

        //Enter valid data for Email address
        driver.findElement(By.id(emailField)).sendKeys(validDataForEmail);
        System.out.println("02.I enter valid Email");

        //Enter Password with less of eight characters
        driver.findElement(By.id(passwordField)).sendKeys(passwordWithLessOf8Ch);
        System.out.println("03.I enter Password with less of eight characters");

        //Enter Confirmation password same like Password
        driver.findElement(By.id(confirmationPasswordField)).sendKeys(passwordWithLessOf8Ch);
        System.out.println("04.I enter Confirmation password same like Password");

        //Enter valid data for Name
        driver.findElement(By.id(nameField)).sendKeys(validDataForName);
        System.out.println("05.I enter valid Name");

        //Enter valid data for Last name
        driver.findElement(By.id(lastNameField)).sendKeys(validDataForLastName);
        System.out.println("06.I enter valid Last name");

        //Click on Create an account button
        driver.findElement(By.cssSelector(buttonCreateAnAccount));
        System.out.println("07. I click on Create an account button");

        //Wait element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(validationMsgForPasswordWithLessOf8Ch)));

        //Verify that alert message is present
        if (driver.findElement(By.id(validationMsgForPasswordWithLessOf8Ch)) != null) {

            String actualResultForValidationMsgPasswordWithLessOf8Ch = driver.findElement(By.id(validationMsgForPasswordWithLessOf8Ch)).getText();
            Assert.assertEquals(expectedResultForPasswordWithLessOf8Ch, actualResultForValidationMsgPasswordWithLessOf8Ch);
            System.out.println("08. Validation messages are present and user can't create new account");

        } else {

            throw new Exception("08. Validation message isn't present");
        }


        }

        //08.TC- The one where user submits Password and Confirmation password different from each other
        @Test
        public void Test08() throws Exception {

            //Wait element
            Thread.sleep(5000);

            //Enter valid data for Email address
            driver.findElement(By.id(emailField)).sendKeys(validDataForEmail);
            System.out.println("02.I enter valid Email");

            //Enter valid data for Password
            driver.findElement(By.id(passwordField)).sendKeys(validDataForPassword);
            System.out.println("03.I enter valid Password");

            //Enter Confirmation password different from Password
            driver.findElement(By.id(confirmationPasswordField)).sendKeys(confirmationPasswordDifferentFromPassword);
            System.out.println("04.I enter Confirmation password different from Password");

            //Enter valid data for Name
            driver.findElement(By.id(nameField)).sendKeys(validDataForName);
            System.out.println("05.I enter valid Name");

            //Enter valid data for Last name
            driver.findElement(By.id(lastNameField)).sendKeys(validDataForLastName);
            System.out.println("06.I enter valid Last name");

            //Click on Create an account button
            driver.findElement(By.cssSelector(buttonCreateAnAccount)).click();
            System.out.println("07. I click on Create an account button");

            //Wait element
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(validationMsgForDifferentConfirmationPassword)));

            //Verify that alert message is present
            if (driver.findElement(By.id(validationMsgForDifferentConfirmationPassword)) != null) {

                String actualResultForValidationMsgDifferentConfirmationPassword = driver.findElement(By.id(validationMsgForDifferentConfirmationPassword)).getText();
                Assert.assertEquals(expectedResultForDifferentConfirmationPassword, actualResultForValidationMsgDifferentConfirmationPassword);
                System.out.println("08. Validation messages are present and user can't create new account");

            } else {

                throw new Exception("08. Validation message isn't present");
            }

            driver.close();
        }







    }


