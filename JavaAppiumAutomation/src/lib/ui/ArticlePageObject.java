package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            SAVED_FOLDER_TPL,
            CLOSE_BUTTON_SYNC_WINDOW;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getSavedFolderElememt(String substring) {
        return SAVED_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }


    public WebElement waitForTitleElement() {
        return this.waitForElementPresent((ARTICLE_TITLE), "Cannot find article title on page", 15);

    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {

            this.swipeUpToFindElement(
                    (FOOTER_ELEMENT),
                    "Cannot find footer element",
                    40);
        } else {

            this.swipeUpToEllementAppear(FOOTER_ELEMENT, "Cannot find the end of  article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElemenAndClick(
                (OPTIONS_BUTTON),//by class //
                "Cannot find button to open article options",
                5);
        this.waitForElemenAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                10);
        this.waitForElemenAndClick(
                (ADD_TO_MY_LIST_OVERLAY),
                "Cannot fint 'Cot it' overlay",
                5);

        this.waitForElementAndClear(
                (MY_LIST_NAME_INPUT),
                "Cannot find input field for reading list title ",
                5);

        this.waitForElementAndSendKeys(
                (MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5);
        this.waitForElemenAndClick(
                (MY_LIST_OK_BUTTON),
                "Cannot press Ok button",
                10);
    }

    public void addArticleToSavedList(String substring) {
        this.waitForElemenAndClick(
                (OPTIONS_BUTTON),//by class //
                "Cannot find button to open article options",
                5);
        this.waitForElemenAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                10);
        String saved_folder_xpath = getSavedFolderElememt(substring);

        this.waitForElemenAndClick(
                (saved_folder_xpath),
                "Can not find saved folder " + substring,
                15);
    }


    public void closeArticle() {

        this.waitForElemenAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot find X button",
                5);

    }

    public void assertCheckArticleTitleOnPage() {
        this.assertElementPresent((ARTICLE_TITLE), "There is no title on the page");

    }

    public void addArticleToMySaved() {
        this.waitForElemenAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to my list", 5);


    }

    public void closeSyncWindow (){
        this.waitForElemenAndClick(CLOSE_BUTTON_SYNC_WINDOW, "Cannot find Close button on Sync window", 10);
    }
}
