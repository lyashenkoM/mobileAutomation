package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE,
            ARTICLE_BY_TITLE_TPL,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            SAVED_FOLDER_TPL,
            CLOSE_BUTTON_SYNC_WINDOW;


    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getSavedFolderElememt(String substring) {
        return SAVED_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                (article_xpath),
                "Can not find saved article by title " + article_title,
                15);
    }


    public WebElement waitForTitleElement() {
        return this.waitForElementPresent((ARTICLE_TITLE), "Cannot find article title on page", 15);

    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {

            this.swipeUpToFindElement(
                    (FOOTER_ELEMENT),
                    "Cannot find footer element",
                    40);
        } else if (Platform.getInstance().isIOS()) {

            this.swipeUpToEllementAppear(FOOTER_ELEMENT, "Cannot find the end of  article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot fins the end of article", 40);
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
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){

        this.waitForElemenAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot find X button",
                5);
        } else{
            System.out.println("Method closeArticle() does nothing for platform");
        }


    }

    public void assertCheckArticleTitleOnPage() {
        this.assertElementPresent((ARTICLE_TITLE), "There is no title on the page");

    }

    public void addArticleToMySaved() {
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
    //    this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to my list", 5);

      this.waitForElemenAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to my list", 5);

    }

    public void closeSyncWindow() {
        this.waitForElemenAndClick(CLOSE_BUTTON_SYNC_WINDOW, "Cannot find Close button on Sync window", 10);
    }

    public void removeArticleFromSavedIfItAdded() {
     //  WebDriverWait wait = new WebDriverWait(driver, 10);
     //  wait.until(ExpectedConditions.stalenessOf(driver.findElementByCssSelector("#page-actions li#ca-watch[title='Stop watching']")));
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElemenAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove article",
                    10);

           this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                   "Cannot find button to add an article to saved list after removing it from this list before", 10);
            System.out.println("I am here");
        }

    }

    public void checkArticleIsAddedToMyListMW() {
        this.isElementLocatedOnTheScreen(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON);
        this.assertElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Open article is not saved to the list!");

    }


}