package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CoreTestCase extends TestCase {   //class jUnit
//    private static final String PLATFORM_IOS = "ios";
//    private static final String PLATFORM_ANDROID = "android";


    protected RemoteWebDriver driver;
    // public static ScreenOrientation orientation;
    //private static String appiumURL = "http://127.0.0.1:4723/wd/hub";
    // protected Platform Platform;


    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        // DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

//        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        //this.rotateScreenPortrait();
       // this.skipWelcomePageForiOS();
        this.openWikiWebPageForMobileWeb();

    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);

        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }

    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }


    }

    protected void backgroundUp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }

    }


    private void skipWelcomePageForiOS() {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

    protected void openWikiWebPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
           System.setProperty("webdriver.chrome.driver", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/chromedriver");
            driver.manage().window().setSize(new Dimension(360,640));
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }

    }

//    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
//        String platform = System.getenv("PLATFORM");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        if (platform.equals(PLATFORM_ANDROID)) {
//            capabilities.setCapability("platformName", "Android");
//            capabilities.setCapability("deviceName", "AndroidTestDevice");
//            capabilities.setCapability("platformVersion", "8.0");
//            capabilities.setCapability("automationName", "Appium");
//            capabilities.setCapability("appPackage", "org.wikipedia");
//            capabilities.setCapability("appActivity", ".main.MainActivity");
//            capabilities.setCapability("app", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
//            capabilities.setCapability("orientation", "PORTRAIT");
//        } else if (platform.equals(PLATFORM_IOS)) {
//            capabilities.setCapability("platformName", "iOS");
//            capabilities.setCapability("deviceName", "iPhone SE");
//            capabilities.setCapability("platformVersion", "12.1");
//            capabilities.setCapability("app", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
//        } else {
//            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
//        }
//        return capabilities;
//    }

}
