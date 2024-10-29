package tests;

import base.ModifiedBaseSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TodoPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static utilities.Configurations.*;

public class EditTasksTest extends ModifiedBaseSuite
{

    private static final Logger logger = LogManager.getLogger(EditTasksTest.class);


    @BeforeClass
    public void initialize()
    {
        logger.info("starting edit todo test");
        todoPage = new TodoPage();
    }

    @Test(priority = 1)
    public void editTaskWithEmpty()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, "");

        assertFalse(todoPage.isTaskExists(""));
    }

    @Test(priority = 2)
    public void editTaskWithSpecialCharacters()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, specialCharacters2);

        assertTrue(todoPage.isTaskExists(specialCharacters2));
    }

    @Test(priority = 3)
    public void editTaskWithLongLength()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, longTask1);

        assertTrue(todoPage.isTaskExists(longTask1));
    }

    @Test(priority = 4)
    public void editTaskThenClickEscape()
    {
        todoPage.addTask(taskName1)
                .openEditField(taskName1)
                .clickEscape();

        assertFalse(todoPage.isEditingModeON());
    }

    @Test(priority = 5)
    public void editTaskWithExistingTask()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .clearAndEditTask(taskName1, taskName2);

        assertTrue(todoPage.isTaskExists(taskName2));
        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test(priority = 6)
    public void editTaskThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, taskName2)
                .refreshPage();

        assertTrue(todoPage.isTaskExists(taskName2));
    }


}
