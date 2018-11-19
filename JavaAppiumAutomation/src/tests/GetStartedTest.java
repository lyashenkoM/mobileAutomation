package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {


    @Test
    public void testPathTroughWelcome(){

        if (Platform.getInstance().isAndroid()){
            return;
        }

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
