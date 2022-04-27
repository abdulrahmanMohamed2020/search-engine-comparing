package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;
    private static final Duration TIMEOUT = Duration.ofSeconds(30);
    private static final Duration POLLING_TIMEOUT = Duration.ofMillis(200);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    protected WebElement findElement(By locator) {
        Wait<WebDriver> driverWait = new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findElements(By locator) {
        Wait<WebDriver> driverWait = new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void actionClickByKeys(By locator) {
        WebElement element = findElement(locator);
        scrollToElement(element);

        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.sendKeys(Keys.ENTER);
        } catch (StaleElementReferenceException ex) {
            findElement(locator).sendKeys(Keys.ENTER);
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
