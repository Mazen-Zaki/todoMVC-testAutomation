package tests;

import base.ModifiedBaseSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TodoPage;

import static org.testng.Assert.assertTrue;
import static utilities.Configurations.*;

public class AddTasksTest extends ModifiedBaseSuite
{
    private static final Logger logger = LogManager.getLogger(AddTasksTest.class);


    @BeforeClass
    public void initialize()
    {
        logger.info("starting add todo test");
        todoPage = new TodoPage();
    }

    @Test(priority = 1)
    public void addTasks()
    {
        logger.info("adding todos");
        todoPage.addTask(taskName1)
                .addTask(taskName2);

        assertTrue(todoPage.isTaskExists(taskName1));
        assertTrue(todoPage.isTaskExists(taskName2));
    }

    @Test(priority = 2)
    public void addingSpecialCharactersTasks()
    {
        todoPage.addTask(specialCharacters3)
                .addTask(specialCharacters2);

        assertTrue(todoPage.isTaskExists(specialCharacters2));
        assertTrue(todoPage.isTaskExists(specialCharacters3));
    }

    @Test(priority = 3)
    public void addingLongLengthTasks()
    {
        todoPage.addTask(longTask1)
                .addTask(longTask2);

        assertTrue(todoPage.isTaskExists(longTask1));
        assertTrue(todoPage.isTaskExists(longTask2));
    }

    @Test(priority = 4)
    public void addTaskThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .refreshPage();

        assertTrue(todoPage.isTaskExists(taskName1));
    }







}
