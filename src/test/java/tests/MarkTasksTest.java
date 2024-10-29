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

public class MarkTasksTest extends ModifiedBaseSuite
{

    private static final Logger logger = LogManager.getLogger(MarkTasksTest.class);


    @BeforeClass
    public void initialize()
    {
        logger.info("starting mark todo test");
        todoPage = new TodoPage();
    }

    @Test
    public void markTaskComplete()
    {
        todoPage.addTask(taskName6)
                .markTaskComplete(taskName6);

        assertTrue(todoPage.isTaskComplete(taskName6));
    }

    @Test
    public void markTaskCompleteThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .markTaskComplete(taskName1)
                .refreshPage();

        assertTrue(todoPage.isTaskComplete(taskName1));
    }

    @Test
    public void markTaskCompleteThenEditTask()
    {
        todoPage.addTask(taskName1)
                .markTaskComplete(taskName1)
                .clearAndEditTask(taskName1, taskName2);

        assertTrue(todoPage.isTaskExists(taskName2));
        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void markTaskCompleteThenDeleteTask()
    {
        todoPage.addTask(taskName5)
                .markTaskComplete(taskName5)
                .deleteTask(taskName5);

        assertFalse(todoPage.isTaskExists(taskName5));
    }

    @Test
    public void markTaskCompleteThenMarkIncomplete()
    {
        todoPage.addTask(taskName1)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName1);

        assertFalse(todoPage.isTaskComplete(taskName1));
    }

    @Test
    public void markTaskCompleteThenMarkIncompleteThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName1)
                .refreshPage();

        assertFalse(todoPage.isTaskComplete(taskName1));
    }

    @Test
    public void markTaskCompleteThenMarkIncompleteThenDeleteTask()
    {
        todoPage.addTask(taskName5)
                .markTaskComplete(taskName5)
                .markTaskComplete(taskName5)
                .deleteTask(taskName5);

        assertFalse(todoPage.isTaskExists(taskName5));
    }

    @Test
    public void markTaskCompleteThenMarkIncompleteThenEditTask()
    {
        todoPage.addTask(taskName5)
                .markTaskComplete(taskName5)
                .markTaskComplete(taskName5)
                .clearAndEditTask(taskName5, taskName2);

        assertTrue(todoPage.isTaskExists(taskName2));
        assertFalse(todoPage.isTaskExists(taskName5));
    }

    @Test
    public void markAllTasksComplete()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .toggleTasksComplete();

        assertTrue(todoPage.isAllTasksComplete());
    }

    @Test
    public void markAllTasksCompleteMarkAllTasksIncomplete()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .toggleTasksComplete()
                .toggleTasksComplete();

        assertTrue(todoPage.isAllTasksComplete());
    }

    @Test
    public void verifyTaskCountMarked()
    {
        int countMarked;

        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1);

        countMarked =  todoPage.getTaskCount() - todoPage.markedTasksCount();

        assertTrue(todoPage.isItemLeftCorrect(countMarked));

    }



}
