package tests;

import base.ModifiedBaseSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TodoPage;
import utilities.Configurations;

import static drivers.NewDriverManager.getDriver;
import static org.testng.Assert.assertTrue;

public class AddToDoTest extends ModifiedBaseSuite {

    private static final Logger logger = LogManager.getLogger(AddToDoTest.class);


    private TodoPage todoPage;

    @BeforeClass
    public void initialize() {
        logger.info("starting add to do test");
        todoPage = new TodoPage(getDriver());
    }

    @Test
    public void addTasks()
    {

        logger.info("adding todos");
        todoPage.addTask(Configurations.taskName1)
                .addTask(Configurations.taskName2);

        assertTrue(todoPage.isTaskExists(Configurations.taskName1));
        assertTrue(todoPage.isTaskExists(Configurations.taskName2));
    }
}
