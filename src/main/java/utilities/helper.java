package utilities;

import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class helper
{

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


}
