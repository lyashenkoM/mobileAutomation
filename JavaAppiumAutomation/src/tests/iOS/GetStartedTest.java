package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {


    @Test
    public void testPathTroughWelcome(){

        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWaysToExploreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferredLanguagesLink();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDataCollectedLink();
        welcomePage.clickGetStartedButton();

    }

}
