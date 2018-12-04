package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");


    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonAndAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonAndDisappear();

    }


    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue("We found too few results!", amount_of_search_results > 0);

    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "zxcvasdfqwer";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testEx3SearchCanceled() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Test";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertSomeArticlesFoundAsResultOfSearch();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertSearchIsCanceled();

    }

    @Test
    public void testFindElementByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "software";
        SearchPageObject.typeSearchLine(search_line);
            SearchPageObject.assertElementByTitleAndDescriptionIsDisplayed("Software", "Non-tangible executable component of a computer");
            SearchPageObject.assertElementByTitleAndDescriptionIsDisplayed("Software testing", "Quality assurance");
            SearchPageObject.assertElementByTitleAndDescriptionIsDisplayed("Software bug", "Error, flaw, failure, or fault in a computer program or system that produces an incorrect or unexpected result, or causes it to behave in unintended ways");

        }

    }




