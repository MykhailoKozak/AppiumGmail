import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidGmailTest {
    public AppiumDriver<MobileElement> driver;
    MobileElement buttonUnderstand;
    MobileElement myMail;
    MobileElement buttonGoToGmail;
    MobileElement mailLetter;
    MobileElement onLater;
    MobileElement checkImportant;
    MobileElement buttonBackToAllMail;
    MobileElement mainPanel;
    MobileElement importantPackage;
    MobileElement textAttribute;
    MobileElement textAttributeNew;
    String textAtt;
    String textAttNew;

    private static final Logger logger = LogManager.getLogger(AndroidGmailTest.class);

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appPackage", "com.google.android.gm");
        capabilities.setCapability("appActivity", "com.google.android.gm.ConversationListActivityGmail");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "abaace27d340");

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test()
    public void test() {
        buttonUnderstand = driver.findElementById("com.google.android.gm:id/welcome_tour_got_it");
        buttonUnderstand.click();
        myMail = driver.findElementById("com.google.android.gm:id/account_address");
        myMail.click();
        buttonGoToGmail = driver.findElementById("com.google.android.gm:id/action_done");
        buttonGoToGmail.click();
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gm:id/mail_toolbar")));

        Assert.assertTrue(driver.findElementById("com.google.android.gm:id/search").isDisplayed());

        mailLetter = driver.findElementByClassName("android.view.View");
        //textAtt = mailLetter.getAttribute("text");
        mailLetter.click();
        textAttribute = driver.findElementById("com.google.android.gm:id/subject_and_folder_view");
        textAtt = textAttribute.getAttribute("text");
        logger.info("Get Attribute textAtt");
        Assert.assertEquals(textAtt, "BIG DATA. Новый факультет с трудоустройством Вхідні ");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageView")));

        onLater = driver.findElementByClassName("android.widget.ImageView");
        onLater.click();
        checkImportant = driver.findElementByXPath("//*[@class='android.widget.TextView' and @instance='3']");
        checkImportant.click();
        buttonBackToAllMail = driver.findElementByClassName("android.widget.ImageButton");
        buttonBackToAllMail.click();
        mainPanel = driver.findElementByClassName("android.widget.ImageButton");
        mainPanel.click();
        importantPackage = driver.findElementByXPath("//*[@class='android.widget.LinearLayout' and @instance='10']");
        importantPackage.click();
        mailLetter = driver.findElementByClassName("android.view.View");
        mailLetter.click();

        textAttributeNew = driver.findElementById("com.google.android.gm:id/subject_and_folder_view");
        textAttNew = textAttributeNew.getAttribute("text");
        logger.info("Get Attribute textAttributeNew");
        Assert.assertEquals(textAttNew, "BIG DATA. Новый факультет с трудоустройством . Вхідні ");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
