package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iosMyListsPageObject extends MyListsPageObject {

    static {

        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        EMPTY_SAVED_LIST = "id:saved-blank";
        SAVED_ICON = "id:Saved. Activate to unsave.";
    }

    public iosMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
