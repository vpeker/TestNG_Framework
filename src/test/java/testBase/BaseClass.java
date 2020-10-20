package testBase;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigsReader;
import utils.Constants;

import java.util.concurrent.TimeUnit;

public class BaseClass extends Constants{
    public static WebDriver driver;

    public static ExtentHtmlReporter html;
    public static ExtentReports report;
    public static ExtentTest test;

    @BeforeTest (alwaysRun = true)
    public void genereteReport() {
        html=new ExtentHtmlReporter(Constants.REPORT_FILEPATH);
        html.config().setTheme(Theme.DARK);
        html.config().setDocumentTitle("Orange HRM Testing Report");
        html.config().setReportName("HRM Tesing Execution Report");

        report=new ExtentReports();
        report.attachReporter(html);
        report.setSystemInfo("QA Engineer", Constants.USER_NAME);
        report.setSystemInfo("Environment", Constants.OS_NAME);
        report.setSystemInfo("Browser", ConfigsReader.getProperty("browser"));
    }

    @AfterTest(alwaysRun = true)
    public void writeReport() {
        report.flush();
    }


    @BeforeMethod(alwaysRun = true)
    public static void setUp(){

        String browser= ConfigsReader.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome")) {
//			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();

        }else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver=new InternetExplorerDriver();
        }
        else {
            System.err.println("Browser not supported");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT,  TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME,  TimeUnit.SECONDS);
        driver.get(ConfigsReader.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        driver.quit();
    }
}
