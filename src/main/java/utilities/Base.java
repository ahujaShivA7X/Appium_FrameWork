package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Base {

    public static Properties config = null;
    public static FileInputStream fis = null;

    public static AndroidDriver<AndroidElement> setup(String appName) throws Exception {


        /*Initializing properties file*/
        System.out.println("***************Initializing Properties file*******************");
        config = new Properties();
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\Global.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*Invoking the driver*/
        AndroidDriver<AndroidElement> driver;

        File appDir = new File("src");
        File app = new File(appDir,config.getProperty(appName));

        DesiredCapabilities dc = new DesiredCapabilities();

        /*These are parsed as JSON for the Appium server*/
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getProperty("PlatformVersion"));
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, config.getProperty("DeviceName"));
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //dc.setCapability("appWaitPackage", "io.anyline.examples.store");
        //dc.setCapability("appWaitActivity", "io.anyline.examples.onboarding.OnBoardingActivity");
        dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());


        /*Localhost*/
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(url, dc);

        return driver;
    }
}
