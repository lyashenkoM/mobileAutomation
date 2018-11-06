package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {//конструктор класса
        this.driver = driver;
    }



    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElemenAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public List<WebElement> waitForElementPresentAndSaveToList(By by, String error_message, long timeoutInSeconds) {
        waitForElementPresent(by, error_message, timeoutInSeconds);
        List<WebElement> list = driver.findElements(by);
        return list;
    }


    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean checkWordInSearch(List<WebElement> list, String text) {
        boolean checkResult = true;
        String inLowerCase = text.toLowerCase();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getAttribute("text").toLowerCase().contains(inLowerCase)) {
                checkResult = false;
                break;
            }
        }

//        for (WebElement element : list) {
//            if (!element.getAttribute("text").toLowerCase().contains(inLowerCase)) {
//                checkResult = false;
//
//                break;
//            }
//        }

        return checkResult;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);//to start at 80 % of the screen
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Can not find element by swipping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX(); //this function will record the most left point acording to X-axis
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    public void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + "" + error_message);
        }

    }

    public void assertElementPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements <1) {
            String default_message = "An element '" + by.toString() + "' is not present";
            throw new AssertionError(default_message + "  " + error_message);

        }
    }

    public void assertSomeElementsPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements >= 1) {
            String default_message = "An element '" + by.toString() + "' is not present!";
            throw new AssertionError(default_message + "  " + error_message);

        }
    }

//    public void assertElementIsPresent(By by, String error_message) {
//
//        List <WebElement>  list  = driver.findElements(by);
//
//        if (list.isEmpty()) {
//            String default_message = "An element '" + by.toString() + "' is not present";
//            throw new AssertionError(default_message + " " + error_message);
//
//        }
//    }


    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);

    }
}


