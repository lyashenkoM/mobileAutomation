package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iosNavigationUI extends NavigationUI {

    static{
        MY_LISTS_LINK ="id:Saved";
    }

    public  iosNavigationUI (AppiumDriver driver){
        super(driver);
    }
}
