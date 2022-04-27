package tests;

import core.CapabilityFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    @Parameters(value={"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp (@Optional("chrome") String browser) throws MalformedURLException {
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getDriver().quit();
    }

    @AfterMethod(alwaysRun=true)
    public void catchExceptions(ITestResult result){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(!result.isSuccess()){
            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(("Screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png")));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
