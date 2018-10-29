import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.Transient;
import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
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
    public void testCancelSearch() {
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
    public void ex3SearchCancel() {

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
                10).size() > 1);

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


    @Test
    public void ex4CheckWordInSearchResult() {

        waitForElemenAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No web element search field",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Test",
                "No Search field to type a value to search",
                5);

        List list = waitForElementPresentAndSaveToList(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No articles corresponded to search!",
                10);

        Assert.assertTrue("No all search result elements contain 'Text'", checkWordInSearch(list, "Test"));


    }


    @Test
    public void testSwipeArticle() {
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Can not find search input",
                5);

        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Can not find 'Appium', searching by Appium",
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Java programming language' article",
                15);

        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"), "Can not find the end of the article", 20);


    }

    @Test
    public void saveFirstArticleToMyList() {
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

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Java programming language' article",
                15);

        waitForElemenAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),//by class //
                "Cannot find button to open article options",
                5);
        waitForElemenAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                10);
        waitForElemenAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot fint 'Cot it' overlay",
                5);
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field for reading list title ",
                5);

        String name_of_Folder = "Learning Programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_Folder,
                "Cannot put text into articles folder input",
                5);
        waitForElemenAndClick(
                By.id("android:id/button1"),
                "Cannot press Ok button",
                1);
        waitForElemenAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5);

        waitForElemenAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                5);

        waitForElemenAndClick(
                By.xpath("//*[@text='Learning Programming']"),
                "Can not find saved folder'Learning programming'",
                10);

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article 'Java (programming language)'"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not deleted saved article",
                5
        );

    }

    @Test
    public void testAmountOfNotEmptySearch() {

        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);

        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15);

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        System.out.println(amount_of_search_results);

        Assert.assertTrue("We found too few results!",

                amount_of_search_results > 0);


    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);

        String search_line = "zxcvasdfqwer";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15);
        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line);


    }


    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        String search_line = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);

        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not find 'Object-oriented programming language' text searing by " + search_line,
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title of article",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title of article",
                15);

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title of article",
                15);

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_after_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        String search_line = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not find 'Object-oriented programming language' text searing by " + search_line,
                15
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not find article after returning from background!",
                15
        );

    }

    @Test
    public void testToSave2Articles() {
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        String search_line = "software testing";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);

        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not find 'Software testing', searching by " + search_line,
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Software testing' article",
                15);

        waitForElemenAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),//by class //
                "Cannot find button to open article options",
                5);

        waitForElemenAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                10);

        waitForElemenAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find 'Got it' button",
                15);
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find 'Name for reading list' field ",
                5);
        String folder_name = "Folder 1";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_name,
                "Cannot sendkeys to 'Name for reading list'",
                5);

        waitForElemenAndClick(
                By.xpath("//*[@text='OK']"),
                "Can not find OK button",
                5);

        waitForElemenAndClick(
                By.id("org.wikipedia:id/menu_page_search"),
                "Cannot find Search icon to continue search!",
                5);
        waitForElemenAndClick(
                By.xpath("//*[@text='software testing']"),
                "Cannot find recent search" + search_line,
                15);

        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing tactics']"),
                "Can not find 'Software testing tactics', searching by " + search_line,
                15);


        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Software testing tactics' article",
                15);

        waitForElemenAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),//by class //
                "Cannot find button to open article options",
                5);

        waitForElemenAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                10);
        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='" + folder_name + "']"),
                "Can not find '" + folder_name + " folder",
                15);
        waitForElemenAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5);

        waitForElemenAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find X button",
                5);

        waitForElemenAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Can not find '" + folder_name + "' folder",
                15);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not find 'Software testing' article",
                15);
        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not remove 'Software testing' article");

        assertElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No articles on the page");

        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing tactics']"),
                "Can not open 'Software testing tactics' article)",
                15);
        String article_title = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Software testing tactics' article",
                5).getAttribute("text");

        Assert.assertEquals("Article 'Software testing tactics' not found", "Software testing tactics", article_title);
    }

    @Test
    public void testOpenArticleAndCheckTitle() {
        waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        String search_line = "software testing";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);

        waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not find 'Software testing', searching by " + search_line,
                15);

        assertElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_header_container']//*[@text='Software testing']"),
                "No title on the page!");
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElemenAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private List<WebElement> waitForElementPresentAndSaveToList(By by, String error_message, long timeoutInSeconds) {
        waitForElementPresent(by, error_message, timeoutInSeconds);
        List<WebElement> list = driver.findElements(by);
        return list;
    }


    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private boolean checkWordInSearch(List<WebElement> list, String text) {
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

    protected void swipeUp(int timeOfSwipe) {
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

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
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

    protected void swipeElementToLeft(By by, String error_message) {
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

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + "" + error_message);
        }

    }

    private void assertElementPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements <1) {
            String default_message = "An element '" + by.toString() + "' is not present";
            throw new AssertionError(default_message + "  " + error_message);

        }
    }

//    private void assertElementIsPresent(By by, String error_message) {
//
//        List <WebElement>  list  = driver.findElements(by);
//
//        if (list.isEmpty()) {
//            String default_message = "An element '" + by.toString() + "' is not present";
//            throw new AssertionError(default_message + " " + error_message);
//
//        }
//    }


    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);

    }
}
