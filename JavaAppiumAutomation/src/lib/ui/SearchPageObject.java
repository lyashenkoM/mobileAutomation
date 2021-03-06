package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject {

   protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RECENT_VALUE_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION;


    public SearchPageObject(AppiumDriver driver) {
        super(driver); //driver from MainPageObject as parent class
    }

    /* Templates methods */

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getRecentSearch(String substring) {
        return SEARCH_RECENT_VALUE_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndSubtitle(String substring1, String substring2) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION.replace("{SUBSTRING1}", substring1).replace("{SUBSTRING2}", substring2);

    }


    /* Templates methods */

    public void initSearchInput() {
        this.waitForElemenAndClick((SEARCH_INIT_ELEMENT), "Cannot find click Search init element", 5);
        this.waitForElementPresent((SEARCH_INPUT), "Cannot find search input after clicking search init element");

    }

    public void waitForCancelButtonAndAppear() {
        this.waitForElementPresent((SEARCH_CANCEL_BUTTON), "Cannot find Cancel button", 5);

    }

    public void waitForCancelButtonAndDisappear() {
        this.waitForElementNotPresent((SEARCH_CANCEL_BUTTON), "Cancel button is still present", 5);

    }

    public void clickCancelSearch() {
        this.waitForElemenAndClick((SEARCH_CANCEL_BUTTON), "Cannot find Cancel button to click", 5);
    }


    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys((SEARCH_INPUT), search_line, "Can not find and type into search input", 5);

    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent((search_result_xpath), "Cannot find search result with substring" + substring, 5);
    }


    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElemenAndClick((search_result_xpath), "Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                (SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15);

        return this.getAmountOfElements((SEARCH_RESULT_ELEMENT));

    }

    public void waitForEmptyResultsLabel() {

        this.waitForElementPresent(
                (SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result label",
                15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent((SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results!");
    }

    public void assertSomeArticlesFoundAsResultOfSearch() {
        this.assertSomeElementsPresent((SEARCH_RESULT_ELEMENT), "There are 1 or 0 articles found according to search result!");


    }

    public void assertSearchIsCanceled() {
        this.assertElementPresent((SEARCH_INIT_ELEMENT), "The search is not canceled!");
    }

    public void clickRecentSearch(String substring) {
        String recent_search_xpath = getRecentSearch(substring);
        this.waitForElemenAndClick(
                (recent_search_xpath),
                "Cannot find recent search",
                15);
    }

public void assertElementByTitleAndDescriptionIsDisplayed (String title, String description){
    String search_result_xpath = getResultSearchElementByTitleAndSubtitle(title, description);
    this.waitForElementPresent((search_result_xpath), "Element by title " + title + " and description " +description +" is not displayed!", 10);


}




}
