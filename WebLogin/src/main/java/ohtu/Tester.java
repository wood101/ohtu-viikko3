package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        //Väärä salasana
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep1");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        //Olematon käyttäjä
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekkaa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep1");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        Random r = new Random();
        
        //Uusi käyttäjä
        element = driver.findElement(By.name("username"));
        element.sendKeys("arto"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep1234");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkep1234");
        element = driver.findElement(By.name("signup"));
        element.submit();
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        sleep(2);
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
