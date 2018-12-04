package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);

    }

    public void clickAuthButton (){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find Login button", 5);
        this.waitForElemenAndClick(LOGIN_BUTTON, "Cannot click Login button", 5);
    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot find Login field", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find password input field", 5);

    }

    public void submitForm(){
        this.waitForElemenAndClick(SUBMIT_BUTTON, "Cannot find submit button", 5);
    }

}


