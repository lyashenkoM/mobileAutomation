package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES =  "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED =  "id:Learn more about data collected",
    BUTTON_NEXT = "id:Next",
    BUTTON_GET_STARTED = "id:Get started";



    public WelcomePageObject (AppiumDriver driver){
        super (driver);

    }

    public void waitForLearnMoreLink (){
        this.waitForElementPresent((STEP_LEARN_MORE_LINK), "Cannot find Learn more about Wikipedia link", 20);

    }


    public void waitForNewWaysToExploreLink (){
        this.waitForElementPresent((STEP_NEW_WAYS_TO_EXPLORE), "Cannot find 'New ways to explore' link", 10);

    }

    public void waitForAddOrEditPreferredLanguagesLink (){
        this.waitForElementPresent((STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES), "Cannot find 'Add or edit preferred languages' link", 10);

    }

    public void waitForLearnMoreAboutDataCollectedLink (){
        this.waitForElementPresent((STEP_LEARN_MORE_ABOUT_DATA_COLLECTED), "Cannot find 'Learn more about data collected' link", 10);

    }

    public void clickNextButton(){
        this.waitForElemenAndClick((BUTTON_NEXT), "Cannot find and click 'Next' button", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElemenAndClick((BUTTON_GET_STARTED), "Cannot find and click 'Get started' button", 10);
    }


}
