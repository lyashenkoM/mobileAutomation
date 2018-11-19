package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;


abstract  public class MyListsPageObject extends  MainPageObject {

   protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL;

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver) {
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
        String article_xpath = getFolderXpathByName(article_title);

        this.waitForElementNotPresent(
                (article_xpath),
                "Can not deleted saved article" + article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title) {
      this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        System.out.println(article_xpath);
        this.swipeElementToLeft(
                (article_xpath),
                "Cannot find saved article " + article_title);

        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article in swipe method");
        }
        this.waitForArticleToDisappearByTitle(article_title);


    }

    public void assertFolderContainsArticle(String article_title) {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.assertElementPresent((article_title_xpath), "There are no saved articles in the folder");

    }

    public void clickSavedArticle (String article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElemenAndClick((article_title_xpath), "It is impossible to find saved in folder article to click it", 10);

    }

}
