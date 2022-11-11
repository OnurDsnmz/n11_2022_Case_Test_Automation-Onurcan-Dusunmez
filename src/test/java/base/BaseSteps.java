package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Objects;

public class BaseSteps {
    public WebDriver webDriver;

    public BaseSteps() {
        System.setProperty("webdriver.chrome.driver","src/driver/chromedriver.exe");
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
    }

    public enum TimeOut {
        LOW(5),
        MIDDLE(10),
        HIGH(15),
        CUSTOM_MAX(60);
        private final int value;

        public int getValue() {
            return value;
        }

        private TimeOut(int value) {
            this.value = value;
        }
    }

    public void geturl(){
        webDriver.get("https://www.n11.com/kampanyalar");
    }

    public void waitElement(WebElement element, TimeOut timeOut) {

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, timeOut.value);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*public void elementControl(WebElement element, TimeOut timeOut){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOut.value);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }*/

    public boolean elementIsDisabled(WebElement element) {
        String attribute = element.getAttribute("class");
        return Objects.equals(attribute, " disabled ");
    }

    public List<WebElement> findElements(By element){
        return webDriver.findElements(element);
    }

    public WebElement findElement(By element){
        return webDriver.findElement(element);
    }

    public void DriverQuit() {
        webDriver.quit();
    }
}

