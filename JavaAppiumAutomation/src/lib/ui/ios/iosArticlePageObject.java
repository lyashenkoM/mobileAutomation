package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iosArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "id:Smartphone produced by Apple inc";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        SAVED_FOLDER_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{SUBSTRING}']";
        CLOSE_BUTTON_SYNC_WINDOW = "xpath://XCUIElementTypeButton[@name='places auth close']";
    }

    public iosArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
