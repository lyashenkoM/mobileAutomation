package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {   //class jUnit
    protected AppiumDriver driver;
    public static ScreenOrientation orientation;
    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";


    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "12.1");
        capabilities.setCapability("app", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
       // capabilities.setCapability("networkConnectionEnabled", true);

        driver = new IOSDriver(new URL(appiumURL), capabilities);
        this.rotateScreenPortrait();

    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait (){
        driver.rotate(ScreenOrientation.PORTRAIT);

    }

    protected void rotateScreenLandscape (){
        driver.rotate(ScreenOrientation.LANDSCAPE);

    }

    protected void backgroundUp(int seconds){
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
}
