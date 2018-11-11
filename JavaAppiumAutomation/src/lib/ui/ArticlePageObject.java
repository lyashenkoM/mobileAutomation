package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
    ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "id:android:id/button1",
    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    SAVED_FOLDER_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{SUBSTRING}']";


    public ArticlePageObject (AppiumDriver driver){
        super (driver);
    }

    private static String getSavedFolderElememt(String substring) {
        return SAVED_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }


    public WebElement waitForTitleElement (){
        return this.waitForElementPresent((ARTICLE_TITLE), "Cannot find article title on page", 15);

    }

    public String getArticleTitle (){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(
                (FOOTER_ELEMENT),
                "Cannot find footer element",
                20);
    }

    public void addArticleToMyList (String name_of_folder){
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

    public void addArticleToSavedList(String substring){
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
                "Can not find saved folder " +substring,
                15);
    }


    public void closeArticle (){

        this.waitForElemenAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot find X button",
                5);

    }

    public void assertCheckArticleTitleOnPage (){
       this.assertElementPresent((ARTICLE_TITLE), "There is no title on the page");

    }

}
