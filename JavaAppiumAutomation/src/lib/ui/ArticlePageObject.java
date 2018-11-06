package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
    ARTICLE_TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text='View page in browser']",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "android:id/button1",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    SAVED_FOLDER_TPL = "//*[@resource-id='org.wikipedia:id/item_container']//*[@text='{SUBSTRING}']";


    public ArticlePageObject (AppiumDriver driver){
        super (driver);
    }

    private static String getSavedFolderElememt(String substring) {
        return SAVED_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }


    public WebElement waitForTitleElement (){
        return this.waitForElementPresent(By.id(ARTICLE_TITLE), "Cannot find article title on page", 15);

    }

    public String getArticleTitle (){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find footer element",
                20);
    }

    public void addArticleToMyList (String name_of_folder){
        this.waitForElemenAndClick(
                By.xpath(OPTIONS_BUTTON),//by class //
                "Cannot find button to open article options",
                5);
        this.waitForElemenAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                10);
        this.waitForElemenAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot fint 'Cot it' overlay",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input field for reading list title ",
                5);

       this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5);
        this.waitForElemenAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Cannot press Ok button",
                10);
    }

    public void addArticleToSavedList(String substring){
        this.waitForElemenAndClick(
                By.xpath(OPTIONS_BUTTON),//by class //
                "Cannot find button to open article options",
                5);
        this.waitForElemenAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                10);
        String saved_folder_xpath = getSavedFolderElememt(substring);

        this.waitForElemenAndClick(
                By.xpath(saved_folder_xpath),
                "Can not find saved folder " +substring,
                15);
    }


    public void closeArticle (){

        this.waitForElemenAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find X button",
                5);

    }

    public void assertCheckArticleTitleOnPage (){
       this.assertElementPresent(By.xpath(ARTICLE_TITLE), "There is no title on the page");

    }

}
