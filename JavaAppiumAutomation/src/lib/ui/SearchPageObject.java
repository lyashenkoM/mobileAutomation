package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String //константы
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver); //driver from MainPageObject as parent class
    }

    /* Templates methods */

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /* Templates methods */

    public void initSearchInput() {
        this.waitForElemenAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find click Search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking search init element");

    }

    public void waitForCancelButtonAndAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Cancel button", 5);

    }

    public void waitForCancelButtonAndDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Cancel button is still present", 5);

    }

    public void clickCancelSearch() {
        this.waitForElemenAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Cancel button to click", 5);
    }


    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Can not find and type into search input", 5);

    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring" + substring, 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElemenAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15);

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));

    }

    public void waitForEmptyResultsLabel() {

        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result label",
                15);
    }

    public void assertThereIsNoResultOfSearch (){
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results!");
    }

    public void assertSomeArticlesFoundAsResultOfSearch (){
        this.assertSomeElementsPresent(By.xpath(SEARCH_RESULT_ELEMENT), "There are 1 or 0 articles found according to search result!");


    }

    public void assertSearchIsCanceled(){
        this.assertElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "The search is not canceled!");
    }






}
