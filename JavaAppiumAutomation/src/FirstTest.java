
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





}