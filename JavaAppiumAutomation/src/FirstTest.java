
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception

    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }



    @Test
    public void testex2CreateMethod() {
        MainPageObject.waitForElemenAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No web element search field",
                5);

        WebElement search_field = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "No 'Search field' appears",
                5);

        String searchField_text = search_field.getAttribute("text");
        assertEquals(
                "We don't see text 'Search...'",
                "Search…",
                searchField_text
        );


    }


    @Test
    public void testEx3SearchCancel() {

        MainPageObject.waitForElemenAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No web element search field",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Test",
                "No Search field to type a value to search",
                5);

        assertTrue("There is 1 or 0 articles regarding search value!", MainPageObject.waitForElementPresentAndSaveToList(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No articles corresponded to search!",
                10).size() > 1);

        MainPageObject.waitForElemenAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "No Close Button",
                5
        );

        WebElement searchField_element = MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "No 'Java programming language' article",
                15);

        String searchField_placeholder = searchField_element.getAttribute("text");
        assertEquals(
                "Search is not canceled",
                "Search…",
                searchField_placeholder);

    }


    @Test
    public void testEx4CheckWordInSearchResult() {

        MainPageObject.waitForElemenAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No web element search field",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Test",
                "No Search field to type a value to search",
                5);

        List list = MainPageObject.waitForElementPresentAndSaveToList(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No articles corresponded to search!",
                10);

      assertTrue("No all search result elements contain 'Text'", MainPageObject.checkWordInSearch(list, "Test"));


    }


    @Test
    public void testToSave2Articles() {
        MainPageObject.waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        String search_line = "software testing";
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);

        MainPageObject.waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not find 'Software testing', searching by " + search_line,
                15);

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Software testing' article",
                15);

        MainPageObject.waitForElemenAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),//by class //
                "Cannot find button to open article options",
                5);

        MainPageObject.waitForElemenAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                10);

        MainPageObject.waitForElemenAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find 'Got it' button",
                15);
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find 'Name for reading list' field ",
                5);
        String folder_name = "Folder 1";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_name,
                "Cannot sendkeys to 'Name for reading list'",
                5);

        MainPageObject.waitForElemenAndClick(
                By.xpath("//*[@text='OK']"),
                "Can not find OK button",
                5);

        MainPageObject.waitForElemenAndClick(
                By.id("org.wikipedia:id/menu_page_search"),
                "Cannot find Search icon to continue search!",
                5);
        MainPageObject.waitForElemenAndClick(
                By.xpath("//*[@text='software testing']"),
                "Cannot find recent search" + search_line,
                15);

        MainPageObject.waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing tactics']"),
                "Can not find 'Software testing tactics', searching by " + search_line,
                15);


        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Software testing tactics' article",
                15);

        MainPageObject.waitForElemenAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),//by class //
                "Cannot find button to open article options",
                5);

        MainPageObject.waitForElemenAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                10);
        MainPageObject.waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='" + folder_name + "']"),
                "Can not find '" + folder_name + " folder",
                15);
        MainPageObject.waitForElemenAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5);

        MainPageObject.waitForElemenAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find X button",
                5);

        MainPageObject.waitForElemenAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Can not find '" + folder_name + "' folder",
                15);
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not find 'Software testing' article",
                15);
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not remove 'Software testing' article");

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No articles on the page");

        MainPageObject.waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing tactics']"),
                "Can not open 'Software testing tactics' article)",
                15);
        String article_title = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No 'Software testing tactics' article",
                5).getAttribute("text");

        assertEquals("Article 'Software testing tactics' not found", "Software testing tactics", article_title);

//        driver.rotate(ScreenOrientation.LANDSCAPE);
//       orientation = driver.getOrientation();

    }

    @Test
    public void testOpenArticleAndCheckTitle() {

        //driver.rotate(orientation);
        MainPageObject.waitForElemenAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Search Wikipedia input",
                5);
        String search_line = "software testing";
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Can not find search input",
                5);

        MainPageObject.waitForElemenAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Software testing']"),
                "Can not find 'Software testing', searching by " + search_line,
                15);

        MainPageObject.assertElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_header_container']//*[@text='Software testing']"),
                "No title on the page!");
    }


}