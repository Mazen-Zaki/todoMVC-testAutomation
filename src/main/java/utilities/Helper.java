package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static drivers.NewDriverManager.getDriver;

public class Helper
{

    private static final Logger logger = LogManager.getLogger(Helper.class);

    // Method to enter text in a text field
    public static void enterText (final WebElement element, final String text)
    {
        try
        {
            element.click ();
            element.clear ();
            element.sendKeys (text);
        }
        catch (Exception e)
        {
            System.out.println ("Error: " + e.getMessage ());
        }
    }

    // Method to wait for a specified time
    public static void waitFor (final int time)
    {
        try
        {
            TimeUnit.SECONDS.sleep (time);
        }
        catch (InterruptedException e)
        {
            System.out.println ("Error: " + e.getMessage ());
        }
    }

    public static void waitForElementToBeClickable(WebElement element, String elementName) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            logger.error("Failed to find clickable element: {}", elementName);
            throw e;
        }
    }

    public static void waitForElementToBeVisible(WebElement element, String elementName) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.error("Element: {} is not visible", elementName);
            throw e;
        }
    }


}
