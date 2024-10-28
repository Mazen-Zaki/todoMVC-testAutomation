package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.assertTrue;

public class TodoPage
{

    private final WebDriver driver;
    Actions act;

    // Locators
    private final By taskInputField = By.className("new-todo");
    private final By allTasks = By.cssSelector(".todo-list li");


    public TodoPage(WebDriver driver)
    {
        this.driver = driver;
        act = new Actions(driver);
    }


    public TodoPage addTask(String taskName)
    {
        driver.findElement(taskInputField).sendKeys(taskName + "\n");
        return this;
    }

    public TodoPage clearAndEditTask(String taskName, String newTaskName)
    {
        WebElement editTaskElement= driver.findElement(By.xpath("//label[text()='" + taskName + "']"));
        act.doubleClick(editTaskElement).perform();
        driver.findElement(By.xpath("//input[@id='edit-todo-input']")).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        driver.findElement(By.xpath("//input[@id='edit-todo-input']")).sendKeys(newTaskName + Keys.ENTER);

        return this;
    }


    public TodoPage markTaskComplete(String taskName)
    {
        driver.findElement(By.xpath("//label[text()='" + taskName + "']/preceding-sibling::input")).click();
        return this;
    }


    public TodoPage deleteTask(String taskName)
    {
        WebElement task = driver.findElement(By.xpath("//label[text()='" + taskName + "']"));
        act.moveToElement(task).perform();
        driver.findElement(By.xpath("//label[text()='" + taskName + "']/following-sibling::button")).click();
        return this;
    }


    public boolean isTaskExists(String taskName)
    {
        try
        {
            return !driver.findElements(By.xpath("//label[text()='" + taskName + "']")).isEmpty();
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
            return driver.findElement(By.id("edit-todo-input")).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public TodoPage openEditField(String taskName)
    {
        WebElement editTaskElement= driver.findElement(By.xpath("//label[text()='" + taskName + "']"));
        act.doubleClick(editTaskElement).perform();

        return this;
    }

    public TodoPage refreshPage()
    {
        driver.navigate().refresh();
        return this;
    }

    public TodoPage clearCompletedTasks()
    {
        driver.findElement(By.className("clear-completed")).click();
        return this;
    }

    public TodoPage toggleTasksComplete()
    {
        driver.findElement(By.className("toggle-all")).click();
        return this;
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
            String itemCountText = driver.findElement(By.xpath("//span[@class=\"todo-count\"]//strong")).getText();
            int itemCount = Integer.parseInt(itemCountText);
            return itemCount == taskCount;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public TodoPage deleteAllTasksIndividually()
    {

        for (WebElement task : driver.findElements(allTasks))
        {
            act.moveToElement(task).perform();
            task.findElement(By.xpath("//button[@class=\"destroy\"]")).click();
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
                task.findElement(By.xpath("//button[@class=\"destroy\"]")).click();
            }
        }
        return this;
    }

    public TodoPage filterActive()
    {
        driver.findElement(By.xpath("//a[text()='Active']")).click();
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
        driver.findElement(By.xpath("//a[text()='Completed']")).click();

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
        driver.findElement(By.xpath("//a[text()='All']")).click();

        assertTrue(true);
        return this;
    }





}
