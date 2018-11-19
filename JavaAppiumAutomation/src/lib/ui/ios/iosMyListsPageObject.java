package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iosMyListsPageObject extends MyListsPageObject {

    static {

        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }

    public iosMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
