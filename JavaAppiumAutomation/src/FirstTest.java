import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown (){
        driver.quit();
    }

    @Test
    public void  firstTest(){
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
       waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not find 'Java programming language', searching by Java",
                15);


    }

    @Test
    public void testCancelSearch(){
        waitForElemenAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No web element search field",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "No element to clear",
                5
        );

        waitForElemenAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "No web element 'Close button'",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Close button still presents",
                5
        );

    }

    @Test
    public void compareArticleTitle() {
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can not find search input",
                5);

        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not find 'Java programming language', searching by Java",
                15);

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Java programming language' article",
                15);

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

        @Test
        public void ex2CreateMethod() {
            waitForElemenAndClick(
                    By.id("org.wikipedia:id/search_container"),
                    "No web element search field",
                    5);

            WebElement search_field = waitForElementPresent(
                    By.id("org.wikipedia:id/search_src_text"),
                    "No 'Search field' appears",
                    5);

            String searchField_text = search_field.getAttribute("text");
            Assert.assertEquals(
                    "We don't see text 'Search...'",
                    "Search…",
                    searchField_text
            );



        }


        @Test
        public void ex3SearchCancel(){

            waitForElemenAndClick(
                    By.id("org.wikipedia:id/search_container"),
                    "No web element search field",
                    5);

            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/search_src_text"),
                    "Test",
                    "No Search field to type a value to search",
                    5);

            Assert.assertTrue("There is 1 or 0 articles regarding search value!", waitForElementPresentAndSaveToList(
                   By.id("org.wikipedia:id/page_list_item_title"),
                           "No articles corresponded to search!",
                    10).size()>1);

            waitForElemenAndClick(
                    By.id("org.wikipedia:id/search_close_btn"),
                    "No Close Button",
                    5
            );

            WebElement searchField_element = waitForElementPresent(
                    By.xpath("//*[contains(@text, 'Search…')]"),
                    "No 'Java programming language' article",
                    15);

            String searchField_placeholder = searchField_element.getAttribute("text");
            Assert.assertEquals(
                    "Search is not canceled",
                    "Search…",
                    searchField_placeholder);

        }




    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElemenAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return  element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return  element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){

        WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private List<WebElement> waitForElementPresentAndSaveToList(By by, String error_message, long timeoutInSeconds){
        waitForElementPresent(by, error_message, timeoutInSeconds);
        List list = driver.findElements(by);
        return list;
    }



}
