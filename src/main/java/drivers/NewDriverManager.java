package drivers;

import enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class NewDriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<> ();
    private static final String NO_SANDBOX = "--no-sandbox";
    private static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
    private static final String HEADLESS = "--headless=new";
    private static final Logger LOG = LogManager.getLogger ("DriverManager.class");

    public static void createDriver (final Browsers browser) {
        switch (browser) {
            case FIREFOX -> setupFirefoxDriver ();
            case EDGE -> setupEdgeDriver ();
            case OPERA -> setupOperaDriver ();
            case HEADLESS_CHROME -> setupHeadlessChromeDriver();
            default -> setupChromeDriver ();
        }
        setupBrowserTimeouts ();
    }

    public static WebDriver getDriver () {
        return NewDriverManager.DRIVER.get();
    }

    public static void quitDriver () {
        if (null != DRIVER.get ()) {
            LOG.info ("Closing the driver...");
            getDriver ().quit ();
            DRIVER.remove ();
        }
    }

    private static void setDriver (final WebDriver driver) {
        NewDriverManager.DRIVER.set (driver);
    }

    private static void setupBrowserTimeouts () {
        LOG.info ("Setting Browser Timeouts....");
        getDriver ().manage ()
                .timeouts ()
                .implicitlyWait (Duration.ofSeconds (20));
        getDriver ().manage ()
                .timeouts ()
                .pageLoadTimeout (Duration.ofSeconds (20));
        getDriver ().manage ()
                .timeouts ()
                .scriptTimeout (Duration.ofSeconds (20));
    }

    private static void setupChromeDriver () {

        LOG.info ("Setting up Chrome Driver....");


        // Configure ChromeOptions
        final var options = new ChromeOptions();

        final var chromePrefs = new HashMap<String, Object>();
        chromePrefs.put ("safebrowsing.enabled", "true");
        chromePrefs.put("profile.password_manager_leak_detection", false);

        options.addArguments (NO_SANDBOX);
        options.addArguments (DISABLE_DEV_SHM);
        options.addArguments ("--safebrowsing-disable-download-protection");

        options.setExperimentalOption ("prefs", chromePrefs);

        var driver = WebDriverManager.chromedriver ()
                .capabilities (options)
                .create();
        driver.manage().window().maximize();
        setDriver (driver);

        LOG.info ("Chrome Driver created successfully!");
    }

    private static void setupHeadlessChromeDriver () {
        LOG.info ("Setting up Headless Chrome Driver....");

        final var chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("safebrowsing.enabled", true); // Boolean type for this flag
        chromePrefs.put("profile.password_manager_leak_detection", false);

        final var options = new ChromeOptions();
        options.addArguments("--headless=new");  // Enable modern headless mode
        options.addArguments("--disable-gpu");   // Disable GPU rendering (fixes headless issues)
        options.addArguments("--disable-extensions");  // Disable extensions
        options.addArguments("--disable-popup-blocking"); // Prevent pop-up blocking issues
        options.addArguments("--remote-allow-origins=*"); // Allow CORS for remote content

        // Optional: Add additional arguments
        options.addArguments("--remote-allow-origins=*");  // Allow remote content

        // Set a large enough window size to ensure the page renders fully
        options.addArguments("--window-size=1920,1080");

        // Optional: Spoof a desktop user agent to avoid blocking
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        options.setExperimentalOption ("prefs", chromePrefs);

        setDriver (WebDriverManager.chromedriver ()
                .capabilities (options)
                .create ());
        LOG.info ("Headless Chrome Driver created successfully!");
    }

    private static void setupEdgeDriver () {
        LOG.info ("Setting up Edge Driver....");
        setDriver (WebDriverManager.edgedriver ()
                .create ());
        LOG.info ("Edge Driver created successfully!");
    }

    private static void setupFirefoxDriver () {
        LOG.info ("Setting up Firefox Driver....");
        final var options = new FirefoxOptions();
        options.addArguments (NO_SANDBOX);
        options.addArguments (DISABLE_DEV_SHM);
        options.addArguments (HEADLESS);
        setDriver (WebDriverManager.firefoxdriver ()
                .capabilities (options)
                .create ());
        LOG.info ("Firefox Driver created successfully!");
    }


    private static void setupOperaDriver () {
        LOG.info ("Setting up Opera Driver....");
        setDriver (WebDriverManager.operadriver ()
                .create ());
        LOG.info ("Opera Driver created successfully!");

    }


    private NewDriverManager () {
    }

}
