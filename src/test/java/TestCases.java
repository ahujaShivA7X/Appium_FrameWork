import pageObjects.HomePage;
import utilities.Base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class TestCases extends Base {

        @Test
        public void start() throws Exception {
        AndroidDriver<AndroidElement> driver = setup("ApplicationName");
        HomePage hp = new HomePage(driver);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        TouchAction t = new TouchAction(driver);
        for(int i=1; i<=5; i++) {
            t.press(PointOption.point(1400, 500)).waitAction(waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(20, 500)).release().perform();
        }

        hp.clickHomeButton().click();

        //driver.findElement(By.id("io.anyline.examples.store:id/btn_ok")).click();

    }
}
