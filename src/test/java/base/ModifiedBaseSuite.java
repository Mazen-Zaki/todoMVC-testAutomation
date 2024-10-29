package base;

import enums.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.TodoPage;
import utilities.Configurations;

import static drivers.NewDriverManager.*;

public class ModifiedBaseSuite {


    private static final Logger logger = LogManager.getLogger(ModifiedBaseSuite.class);

    public TodoPage todoPage;


    @BeforeClass(alwaysRun = true)
    public void setup() {
//        createDriver(Browsers.HEADLESS_CHROME);
        logger.info("set up drivers");
        createDriver(Browsers.CHROME);
        getDriver().get(Configurations.baseUrl);
    }


    @AfterMethod
    public void cleanUp()
    {
        logger.info("cleaning up after test");
        getDriver().manage().deleteAllCookies();

        todoPage.deleteAllTasksIndividually();
    }


    @AfterClass(alwaysRun = true)
    public void teardown() {
        quitDriver ();
        logger.info("Finishing Test Execution");
    }

}
