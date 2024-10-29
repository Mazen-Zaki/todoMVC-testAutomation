package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static drivers.NewDriverManager.getDriver;
import static org.testng.Assert.assertTrue;

public class TodoPage
{

    private final WebDriver driver;
    Actions act;

    // Locators
    private final By taskInputField = By.className("new-todo");
    private final By allTasks = By.cssSelector(".todo-list li");


    /***************************************** Elements *****************************************/

    public WebElement taskInputFieldElement()
    {
        return getDriver().findElement(By.className("new-todo"));
    }

    public WebElement allTasksElement ()
    {
        return getDriver().findElement(By.cssSelector(".todo-list li"));
    }

    public WebElement taskElement(String taskName)
    {
        return getDriver().findElement(By.xpath("//label[text()='" + taskName + "']"));
    }

    public WebElement taskCheckboxElement(String taskName)
    {
        return getDriver().findElement(By.xpath("//label[text()='" + taskName + "']/preceding-sibling::input"));
    }

    public WebElement taskDeleteButtonElement(String taskName)
    {
        return getDriver().findElement(By.xpath("//label[text()='" + taskName + "']/following-sibling::button"));
    }

    public WebElement taskEditFieldElement()
    {
        return getDriver().findElement(By.id("edit-todo-input"));
    }

    public WebElement taskEditFieldElement(String taskName)
    {
        return getDriver().findElement(By.xpath("//label[text()='" + taskName + "']/following-sibling::input"));
    }

    public WebElement taskElementEditField(String taskName)
    {
        return getDriver().findElement(By.xpath("//label[text()='" + taskName + "']/following-sibling::input"));
    }

    public WebElement taskElementEditField()
    {
        return getDriver().findElement(By.xpath("//label[text()='']/following-sibling::input"));
    }

    public WebElement clearCompletedButtonElement()
    {
        return getDriver().findElement(By.className("clear-completed"));
    }

    public WebElement toggleAllButtonElement()
    {
        return getDriver().findElement(By.className("toggle-all"));
    }

    public WebElement toggleAllCheckboxElement()
    {
        return getDriver().findElement(By.className("toggle-all"));
    }

    public WebElement destroyButtonElement()
    {
        return getDriver().findElement(By.className("destroy"));
    }

    public WebElement filterActiveElement()
    {
        return getDriver().findElement(By.xpath("//a[text()='Active']"));
    }

    public WebElement filterCompletedElement()
    {
        return getDriver().findElement(By.xpath("//a[text()='Completed']"));
    }

    public WebElement filterAllElement()
    {
        return getDriver().findElement(By.xpath("//a[text()='All']"));
    }

    public WebElement todoCountElement()
    {
        return getDriver().findElement(By.className("todo-count"));
    }



    /***************************************** Constructor *****************************************/

    public TodoPage()
    {
        this.driver = getDriver();
        act = new Actions(driver);
    }

    /***************************************** Actions *****************************************/

    public TodoPage addTask(String taskName)
    {
        taskInputFieldElement().sendKeys(taskName + "\n");
        return this;
    }

    public TodoPage clearAndEditTask(String taskName, String newTaskName)
    {
        WebElement editTaskElement = taskElement(taskName);
        act.doubleClick(editTaskElement).perform();

        taskEditFieldElement().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        taskEditFieldElement().sendKeys(newTaskName + Keys.ENTER);

        return this;
    }

    public TodoPage markTaskComplete(String taskName)
    {
        taskCheckboxElement(taskName).click();
        // driver.findElement(By.xpath("//label[text()='" + taskName + "']/preceding-sibling::input")).click();
        return this;
    }

    public TodoPage deleteTask(String taskName)
    {
        WebElement task = taskElement(taskName);
        act.moveToElement(task).perform();
        taskDeleteButtonElement(taskName).click();
//        WebElement task = driver.findElement(By.xpath("//label[text()='" + taskName + "']"));
//        act.moveToElement(task).perform();
//        driver.findElement(By.xpath("//label[text()='" + taskName + "']/following-sibling::button")).click();
        return this;
    }

    public TodoPage openEditField(String taskName)
    {
        WebElement editTaskElement = taskElement(taskName);
        act.doubleClick(editTaskElement).perform();
//        WebElement editTaskElement= driver.findElement(By.xpath("//label[text()='" + taskName + "']"));
//        act.doubleClick(editTaskElement).perform();
        return this;
    }

    public TodoPage refreshPage()
    {
        getDriver().navigate().refresh();
        return this;
    }

    public TodoPage clearCompletedTasks()
    {
        clearCompletedButtonElement().click();
//        driver.findElement(By.className("clear-completed")).click();
        return this;
    }

    public TodoPage toggleTasksComplete()
    {
        toggleAllButtonElement().click();
//        driver.findElement(By.className("toggle-all")).click();
        return this;
    }

    public TodoPage deleteAllTasksIndividually()
    {

        try
        {
            for (WebElement task : driver.findElements(allTasks))
            {
                act.moveToElement(task).perform();
                destroyButtonElement().click();
//                task.findElement(By.xpath("//button[@class=\"destroy\"]")).click();
            }
        }
        catch (Exception e)
        {
            return this;
        }

        return this;
    }

    public TodoPage deleteCompletedTasksIndividually()
    {
        for (WebElement task : driver.findElements(allTasks))
        {
            if (task.findElement(By.xpath("./div/input")).isSelected())
            {
                act.moveToElement(task).perform();
                destroyButtonElement().click();
//                task.findElement(By.xpath("//button[@class=\"destroy\"]")).click();
            }
        }
        return this;
    }

    public TodoPage filterActive()
    {
        filterActiveElement().click();
//        driver.findElement(By.xpath("//a[text()='Active']")).click();
        for (WebElement task : driver.findElements(allTasks))
        {
            if (task.findElement(By.xpath("./div/input")).isSelected())
            {
                assertTrue(false);
            }
        }
        assertTrue(true);
        return this;
    }

    public TodoPage filterCompleted()
    {
        filterCompletedElement().click();
//        driver.findElement(By.xpath("//a[text()='Completed']")).click();

        for (WebElement task : driver.findElements(allTasks))
        {
            if (!task.findElement(By.xpath("./div/input")).isSelected())
            {
                assertTrue(false);
            }
        }
        assertTrue(true);
        return this;
    }

    public TodoPage filterAll()
    {
        filterAllElement().click();
//        driver.findElement(By.xpath("//a[text()='All']")).click();

        assertTrue(true);
        return this;
    }


    /***************************************** Business Logic *****************************************/

    public boolean isTaskExists(String taskName)
    {
        try
        {
            return driver.findElement(By.xpath("//label[text()='" + taskName + "']")).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public boolean isTaskComplete(String taskName)
    {
        try
        {
            return driver.findElement(By.xpath("//label[text()='" + taskName + "']/preceding-sibling::input")).isSelected();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isAllTasksDeleted()
    {
        return driver.findElements(allTasks).isEmpty();
    }

    public int getTaskCount()
    {
        return driver.findElements(allTasks).size();
    }

    public TodoPage clickEscape()
    {
        act.sendKeys(Keys.ESCAPE).perform();

        return this;
    }

    public boolean isEditingModeON()
    {
        try
        {
            return taskEditFieldElement().isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isAllTasksComplete()
    {

        try {
            for (WebElement task : driver.findElements(allTasks))
            {
                if (!task.findElement(By.xpath("./div/input")).isSelected())
                {
                    return false;
                }
            }
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    public boolean isTaskCountCorrect(int taskCount)
    {
        try
        {
            return driver.findElements(allTasks).size() == taskCount;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public int markedTasksCount()
    {
        int count = 0;
        for (WebElement task : driver.findElements(allTasks))
        {
            if (task.findElement(By.xpath("./div/input")).isSelected())
            {
                count++;
            }
        }
        return count;

    }

    public boolean isItemLeftCorrect(int taskCount)
    {
        try
        {
            String itemCountText = todoCountElement().getText().split(" ")[0];
            int itemCount = Integer.parseInt(itemCountText);
            return itemCount == taskCount;
        }
        catch (Exception e)
        {
            return false;
        }
    }



}
