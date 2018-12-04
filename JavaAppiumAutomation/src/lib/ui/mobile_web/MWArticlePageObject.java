package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MWArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "css:#content h1";
        ARTICLE_BY_TITLE_TPL = "";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_BUTTON = "";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch[title='Watch this page']";
                //"css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch[title='Stop watching']";
                //"css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
        ADD_TO_MY_LIST_OVERLAY = "";
        MY_LIST_NAME_INPUT = "";
        MY_LIST_OK_BUTTON = "";
        CLOSE_ARTICLE_BUTTON = "";
        SAVED_FOLDER_TPL = "";
        CLOSE_BUTTON_SYNC_WINDOW = "";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

