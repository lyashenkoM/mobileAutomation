package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE = "New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES =  "Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED =  "Learn more about data collected",
    BUTTON_NEXT = "Next",
    BUTTON_GET_STARTED = "Get started";



    public WelcomePageObject (AppiumDriver driver){
        super (driver);

    }

    public void waitForLearnMoreLink (){
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_LINK), "Cannot find Learn more about Wikipedia link", 20);

    }


    public void waitForNewWaysToExploreLink (){
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE), "Cannot find 'New ways to explore' link", 10);

    }

    public void waitForAddOrEditPreferredLanguagesLink (){
        this.waitForElementPresent(By.id(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES), "Cannot find 'Add or edit preferred languages' link", 10);

    }

    public void waitForLearnMoreAboutDataCollectedLink (){
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED), "Cannot find 'Learn more about data collected' link", 10);

    }

    public void clickNextButton(){
        this.waitForElemenAndClick(By.id(BUTTON_NEXT), "Cannot find and click 'Next' button", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElemenAndClick(By.id(BUTTON_GET_STARTED), "Cannot find and click 'Get started' button", 10);
    }





}
