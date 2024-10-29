package base;

import drivers.DriverManager;
import enums.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.TodoPage;

import static drivers.DriverManager.quitDriver;

public class BaseTest
{
    public TodoPage todoPage;
    public static ChromeDriver driver;
    public Actions act;

    String baseUrl = "https://todomvc.com/examples/angular/dist/browser/#/all";
    public String taskName1 = "Buy groceries";
    public String taskName2 = "Read a book";
    public String taskName3 = "Clean the house";
    public String taskName4 = "Go to the gym";
    public String taskName5 = "Prepare dinner";
    public String taskName6 = "Do the laundry";
    public String specialCharacters1 = "! @ # $ % ^ & * ( ) _ + - = { } [ ] ; '  : < > ? , . /  | ~ `";
    public String specialCharacters2 = "¡ ¢ £ ¤ ¥ ¦ § ¨ © ª « ¬ ® ¯ ° ± ² ³ ´ µ ¶ · ¸ ¹ º » ¼ ½ ¾ ¿";
    public String specialCharacters3 = "À Á Â Ã Ä Å Æ Ç È É Ê Ë Ì Í Î Ï Ð Ñ Ò Ó Ô Õ Ö × Ø Ù Ú Û Ü Ý Þ ß";
    public String longTask1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    public String longTask2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    //
////    @Parameters("browser")
//    @BeforeClass
//    public void setUp()
//    {
//        DriverManager.createDriver(Browser.CHROME);
//        DriverManager.getDriver().get(baseUrl);
//        todoPage = new TodoPage();
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown()
//    {
//        quitDriver();
//    }

    @BeforeMethod
    public void setUp() {
        // Initialize ChromeDriver (Selenium 4 manages drivers automatically)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        // Initialize the login page object
        todoPage = new TodoPage();

        //Instantiating Actions class
        act = new Actions(driver);

    }


    // After each test, quit the browser
    @AfterMethod
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }
}
