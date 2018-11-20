package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {

    private static final String NAME_OF_FOLDER = "Learning Programming";

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("iPhone XS");
        SearchPageObject.clickByArticleWithSubstring("Smartphone produced by Apple inc");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();


        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        }

        ArticlePageObject.addArticleToMySaved();
        ArticlePageObject.closeSyncWindow();

        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testEx5ToSave2Articles() {

        String search = "iPhone XS";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search);
        SearchPageObject.clickByArticleWithSubstring("Smartphone produced by Apple inc");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeSyncWindow();
        }

        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickRecentSearch(search);
        }
        SearchPageObject.clickByArticleWithSubstring("Line of smartphones designed and marketed by Apple Inc.");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
        } else {
            ArticlePageObject.waitForArticleToAppearByTitle("Line of smartphones designed and marketed by Apple Inc.");
        }
        if (Platform.getInstance().isAndroid()) {
            String second_article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToSavedList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUI.clickMyLists();
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(NAME_OF_FOLDER);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
        MyListPageObject.checkEmptySavedListLabel();
        MyListPageObject.clickSavedArticle("Line of smartphones designed and marketed by Apple Inc.");

        if (Platform.getInstance().isAndroid()) {
            String second_article_title = ArticlePageObject.getArticleTitle();
            MyListPageObject.clickSavedArticle(second_article_title);
            assertEquals(
                    "We see unexpected title",
                    "Software testing controversies",
                    second_article_title);
        } else {
        MyListPageObject.checkSavedIconOnSavedArticle();}

    }
}
