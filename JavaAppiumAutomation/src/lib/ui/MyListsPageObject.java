package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import static lib.ui.ArticlePageObject.OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;


abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            EMPTY_SAVED_LIST,
            SAVED_ICON,
            REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }


    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }


    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElemenAndClick(
                (folder_name_xpath),
                "Can not find folder by name " + name_of_folder,
                10);

    }


    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                (article_xpath),
                "Can not find saved article by title " + article_title,
                15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(
                (article_xpath),
                "Can not deleted saved article" + article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        System.out.println(article_xpath);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article " + article_title);
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElemenAndClick(remove_locator, "Cannot click button to remove article from saved.", 10);
        }


        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article in swipe method");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();

        }

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForArticleToDisappearByTitle(article_title);


        }
    }

    public void assertFolderContainsArticle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.assertElementPresent((article_title_xpath), "There are no saved articles in the folder");

    }

    public void clickSavedArticle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElemenAndClick((article_title_xpath), "It is impossible to find saved in folder article to click it", 10);

    }

    public void checkEmptySavedListLabel() {
        this.assertElementNotPresent(EMPTY_SAVED_LIST, "Empty saved list label is present");
    }

    public void checkSavedIconOnSavedArticle() {
        if(Platform.getInstance().isIOS()){
        this.isElementLocatedOnTheScreen(SAVED_ICON);
        this.assertElementPresent(SAVED_ICON, "Open article is not saved to the list!");}

        if (Platform.getInstance().isMW()){
            this.isElementLocatedOnTheScreen(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON);
            this.assertElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Open article is not saved to the list!");

        }

    }
}
