package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iosSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RECENT_VALUE_TPL = "xpath://*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING1}') and contains(@name, '{SUBSTRING2}')]";
    }

    public iosSearchPageObject (AppiumDriver driver){
        super(driver);
    }
}
