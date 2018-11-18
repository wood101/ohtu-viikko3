package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();  
    } 
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }     
    
    @When("^nonexistent username \"([^\"]*)\" and nonexistent password \"([^\"]*)\" are given$")
    public void nonexistant_username_is_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        navigateToNewUser();   
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void correct_username_and_password_are_given(String username, String password) throws Throwable {
        createNewUser(username, password, password);
    }
    
    @Then("^a new user is created$")
    public void new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @When("^a too short an username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void short_username_and_correct_password_are_given(String username, String password) throws Throwable {
        createNewUser(username, password, password);
    }

    @When("^a valid username \"([^\"]*)\" and a too short a password \"([^\"]*)\" and matching password confirmation are entered$")
    public void correct_username_and_short_password_are_given(String username, String password) throws Throwable {
        createNewUser(username, password, password);
    }    

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" with password confirmation \"([^\"]*)\" are entered$")
    public void correct_username_and_password_are_given_with_wrong_password_confirmation(String username, String password, String passwordConfirmation) throws Throwable {
        createNewUser(username, password, passwordConfirmation);
    }   
    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void new_user_is_created(String error) throws Throwable {
        pageHasContent(error);
    }
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void correct_user_is_created(String username, String password) throws Throwable {
        navigateToNewUser();  
        createNewUser(username, password, password); 
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void incorrect_user_is_created(String username, String password) throws Throwable {
        navigateToNewUser(); 
        createNewUser(username, password, password); 
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void navigateToNewUser() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();  
    }
    
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 

    private void createNewUser(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
    
}
