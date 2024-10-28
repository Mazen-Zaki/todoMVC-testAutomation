package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TodoTests extends BaseTest
{
//    @BeforeClass
//    public void setUp()
//    {
//        final String website = "https://todomvc.com/examples/angular/dist/browser/#/all";
//        getDriver().get(website);
//        todoPage = new TodoPage();
//    }


    /* User Story 1 - Add/Edit Tasks - 11 test scripts */

    @Test
    public void addTasks()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2);

        assertTrue(todoPage.isTaskExists(taskName1));
        assertTrue(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void markTaskCompleteTest()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .clearAndEditTask(taskName1, taskName3)
                .clearAndEditTask(taskName2, taskName4);

        assertTrue(todoPage.isTaskExists(taskName3));
        assertTrue(todoPage.isTaskExists(taskName4));
    }

    @Test
    public void addingSpecialCharactersTasks()
    {
        todoPage.addTask(specialCharacters3)
                .addTask(specialCharacters2);

        assertTrue(todoPage.isTaskExists(specialCharacters2));
        assertTrue(todoPage.isTaskExists(specialCharacters3));
    }

    @Test
    public void addingLongLengthTasks()
    {
        todoPage.addTask(longTask1)
                .addTask(longTask2);

        assertTrue(todoPage.isTaskExists(longTask1));
        assertTrue(todoPage.isTaskExists(longTask2));
    }

    @Test
    public void editTaskWithEmpty()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, "");

        assertFalse(todoPage.isTaskExists(""));
    }

    @Test
    public void editTaskWithSpecialCharacters()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, specialCharacters2);

        assertTrue(todoPage.isTaskExists(specialCharacters2));
    }

    @Test
    public void editTaskWithLongLength()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, longTask1);

        assertTrue(todoPage.isTaskExists(longTask1));
    }

    @Test
    public void editTaskThenClickEscape()
    {
        todoPage.addTask(taskName1)
                .openEditField(taskName1)
                .clickEscape();

        assertFalse(todoPage.isEditingModeON());
    }

    @Test
    public void editTaskWithExistingTask()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .clearAndEditTask(taskName1, taskName2);

        assertTrue(todoPage.isTaskExists(taskName2));
        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void addTaskThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .refreshPage();

        assertTrue(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void editTaskThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .clearAndEditTask(taskName1, taskName2)
                .refreshPage();

        assertTrue(todoPage.isTaskExists(taskName2));
    }


    /**************************************************************************************************************************************************/
    /* User Story 2 - Mark tasks - 11 test scripts */

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

    /**************************************************************************************************************************************************/
    /* User Story 3 - Delete tasks - 5 test scripts */

    @Test
    public void deleteTask()
    {
        todoPage.addTask(taskName1)
                .deleteTask(taskName1);

        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void deleteTaskThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .deleteTask(taskName1)
                .refreshPage();

        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void deleteAllTasksIndividually()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .addTask(taskName3)
                .deleteTask(taskName1)
                .deleteTask(taskName2)
                .deleteTask(taskName3);

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }

    @Test
    public void deleteAllTasks()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .addTask(taskName3)
                .deleteAllTasksIndividually();

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }

    @Test
    public void deleteCompletedTasks()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName2)
                .deleteCompletedTasksIndividually();

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }

    /**************************************************************************************************************************************************/
    /* User Story 4 - Filter tasks - 14 test scripts */

    @Test
    public void validateTogglingBetweenFilters()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterActive()
                .filterCompleted()
                .filterAll();

        //assertTrue(todoPage.isAllTasksDisplayed());
    }

    @Test
    public void filterActive()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterActive();

        assertTrue(todoPage.isTaskExists(taskName2));
        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void filterCompleted()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterCompleted();

        assertTrue(todoPage.isTaskExists(taskName1));
        assertFalse(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void filterAll()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterAll();

        assertTrue(todoPage.isTaskExists(taskName1));
        assertTrue(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void filterActiveThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterActive()
                .refreshPage();

        assertTrue(todoPage.isTaskExists(taskName2));
        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void filterCompletedThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterCompleted()
                .refreshPage();

        assertTrue(todoPage.isTaskExists(taskName1));
        assertFalse(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void filterAllThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterAll()
                .refreshPage();

        assertTrue(todoPage.isTaskExists(taskName1));
        assertTrue(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void filterActiveThenDeleteTask()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterActive()
                .deleteTask(taskName2);

        assertTrue(todoPage.isTaskExists(taskName1));
        assertFalse(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void filterCompletedThenDeleteTask()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterCompleted()
                .deleteTask(taskName1);

        assertFalse(todoPage.isTaskExists(taskName1));
        assertTrue(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void filterAllThenDeleteTask()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterAll()
                .deleteTask(taskName1);

        assertFalse(todoPage.isTaskExists(taskName1));
        assertTrue(todoPage.isTaskExists(taskName2));
    }

    @Test
    public void filterActiveThenMarkTaskComplete()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .filterActive()
                .markTaskComplete(taskName1);

        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void filterCompletedThenMarkTaskIncomplete()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .filterCompleted()
                .markTaskComplete(taskName1);

        assertFalse(todoPage.isTaskExists(taskName1));
    }

    @Test
    public void filterAllThenMarkTaskComplete()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .filterAll()
                .markTaskComplete(taskName1);

        assertTrue(todoPage.isTaskComplete(taskName1));
    }

    @Test
    public void filterActiveThenMarkAllTasksComplete()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .filterActive()
                .toggleTasksComplete();

        assertTrue(todoPage.isAllTasksComplete());
    }


    /**************************************************************************************************************************************************/
    /* User Story 5 - Clear Completed tasks - 6 test scripts */

    @Test
    public void clearCompletedTasks()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName2)
                .clearCompletedTasks();

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }

    @Test
    public void clearCompletedTasksThenRefreshPage()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName2)
                .clearCompletedTasks()
                .refreshPage();

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }

    @Test
    public void clearCompletedTasksThenAddTask()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName2)
                .clearCompletedTasks()
                .addTask(taskName3);

        assertTrue(todoPage.isTaskExists(taskName3));
    }

    @Test
    public void activeFilterThenClearCompletedTasks()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName2)
                .filterActive()
                .clearCompletedTasks()
                .filterAll();

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }

    @Test
    public void completedFilterThenClearCompletedTasks()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName2)
                .filterCompleted()
                .clearCompletedTasks()
                .filterAll();

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }

    @Test
    public void allFilterThenClearCompletedTasks()
    {
        todoPage.addTask(taskName1)
                .addTask(taskName2)
                .markTaskComplete(taskName1)
                .markTaskComplete(taskName2)
                .filterAll()
                .clearCompletedTasks()
                .filterAll();

        assertTrue(todoPage.isTaskCountCorrect(0));
        assertTrue(todoPage.isAllTasksDeleted());
    }


}
