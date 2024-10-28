package drivers;


import enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

public class DriverManager
{
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final String NO_SANDBOX = "--no-sandbox";
    private static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
    private static final String CUSTOM_WINDOW_SIZE = "--window-size=1050,600";
    private static final String HEADLESS = "--headless";

    private DriverManager()
    {
    }

    public static void createDriver(Browser browser) {
        switch (browser) {
            case FIREFOX -> setupFirefoxDriver();
            case EDGE -> setupEdgeDriver();
            default -> setupChromeDriver();
        }
        setupBrowserTimeouts();
    }

    private static void setDriver(final WebDriver driver)
    {
        DriverManager.DRIVER.set(driver);
    }

    private static void setupBrowserTimeouts()
    {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
    }

    private static void setupChromeDriver ()
    {
        final var isHeadless = Boolean.parseBoolean (
                Objects.requireNonNullElse (System.getProperty ("headless"), "true"));
        final var chromePrefs = new HashMap<String, Object> ();
        chromePrefs.put ("safebrowsing.enabled", "true");
        chromePrefs.put ("download.prompt_for_download", "false");
        chromePrefs.put ("download.default_directory",
                String.valueOf (Paths.get (System.getProperty ("user.home"), "Downloads")));
        chromePrefs.put("profile.password_manager_leak_detection", false);

        final var options = new ChromeOptions ();
        options.addArguments (NO_SANDBOX);
        options.addArguments (DISABLE_DEV_SHM);
        options.addArguments (CUSTOM_WINDOW_SIZE);
        if (isHeadless) {
            options.addArguments (HEADLESS);
        }
        options.addArguments ("--safebrowsing-disable-download-protection");
        options.setExperimentalOption ("prefs", chromePrefs);

        setDriver (WebDriverManager.chromedriver ()
                .capabilities (options)
                .create ());
    }


    private static void setupEdgeDriver()
    {
        WebDriverManager.edgedriver().setup();
        setDriver(new EdgeDriver());
    }

    private static void setupFirefoxDriver()
    {
        final var options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1050,600");
        options.addArguments("--headless");
        WebDriverManager.firefoxdriver().setup();
        setDriver(new FirefoxDriver(options));
    }


    public static WebDriver getDriver ()
    {
        return DriverManager.DRIVER.get ();
    }


    public static void quitDriver()
    {
        if (null != DRIVER.get()) {
            getDriver().quit();
            DRIVER.remove();
        }
    }

}
