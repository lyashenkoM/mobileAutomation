package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    public static Platform instance;

    private Platform(){}//приватный конструкто

    public  static Platform getInstance(){
        if (instance==null){
            instance = new Platform();
        }

        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()){
            return  new IOSDriver(URL, this.getIOSDesiredCapabilities());
        }  else if (this.isMW()){
            return new ChromeDriver(this.getMWChromeOptions());
        } else  throw new Exception("Cannot detect type of the driver. Platform value: " + this.getPlatformVar());
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }




    private DesiredCapabilities getAndroidDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("orientation", "PORTRAIT");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "12.1");
        capabilities.setCapability("app", "/Users/masha/Desktop/GitHub/mobileAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;

    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();

        return my_platform.equals(platform);
    }

   public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions.addArguments("--window-size=360, 640");
        return  ChromeOptions;
    }


}
