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
    private static final String LOGIN = "masha_lyasha";
    private static final String PASSWORD = "Qwerty@1";

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();


        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        }

        ArticlePageObject.addArticleToMySaved();

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(LOGIN, PASSWORD);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();

        }
        // ArticlePageObject.closeSyncWindow();
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();

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
        SearchPageObject.clickByArticleWithSubstring("martphone produced by Apple Inc and twelfth generation iPhone");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        }
        ArticlePageObject.addArticleToMySaved();

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncWindow();
        } else {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(LOGIN, PASSWORD);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        }

        SearchPageObject.initSearchInput();

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickRecentSearch(search);
        }

        if (Platform.getInstance().isMW()) {
            SearchPageObject.typeSearchLine(search);
        }

        SearchPageObject.clickByArticleWithSubstring("ine of smartphones designed and marketed by Apple Inc.");

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            ArticlePageObject.waitForTitleElement();
        } else {
            ArticlePageObject.waitForArticleToAppearByTitle("ine of smartphones designed and marketed by Apple Inc.");
        }
        if (Platform.getInstance().isAndroid()) {
            String second_article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToSavedList(NAME_OF_FOLDER);
        }

        ArticlePageObject.addArticleToMySaved();


        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();


        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(NAME_OF_FOLDER);
        }
        MyListPageObject.swipeByArticleToDelete("Phone XS");
        MyListPageObject.clickSavedArticle("Phone");

        if (Platform.getInstance().isAndroid()) {
            String second_article_title = ArticlePageObject.getArticleTitle();
            MyListPageObject.clickSavedArticle(second_article_title);
            assertEquals(
                    "We see unexpected title",
                    "Software testing controversies",
                    second_article_title);
        } else {
            MyListPageObject.checkSavedIconOnSavedArticle();

    }}
}
