package testExample;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class DataProviderRecap  {
    WebDriver driver;

    /*
    steps
  1. open browser and navigate ot orangeHRM
  2.Login on OrangeHRM
  3.Navigate to add  employee page
  4. enter employee name and lastname -click save
  5.verify that employee is added succecfully
  6.close the browser
     */
    @BeforeClass
    public void openBrowserNavigateOrangeHRM(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");




    }

    @BeforeMethod
    public void login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement userNm = driver.findElement(By.id("txtUsername"));
        wait.until(ExpectedConditions.elementToBeClickable(userNm));
        userNm.sendKeys("Admin");


        WebElement passWd = driver.findElement(By.id("txtPassword"));
        wait.until(ExpectedConditions.elementToBeClickable(passWd));
        passWd.sendKeys("admin123");

        WebElement loginBn = driver.findElement(By.id("btnLogin"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBn));
        loginBn.click();

    }

    @Test(dataProvider = "getData")
    public void addEmployee(String name, String lastName) throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver,10);

        WebElement PIMButton =driver.findElement(By.id("menu_pim_viewPimModule"));
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(PIMButton));
        PIMButton.click();


        WebElement addEmpButton=driver.findElement(By.id("menu_pim_addEmployee"));
         Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(addEmpButton));
        addEmpButton.click();


        driver.findElement(By.id("firstName")).sendKeys(name);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("btnSave")).click();

        String actEmpFullName=driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText();
        String expName=name + " " +lastName;
        Assert.assertEquals(actEmpFullName,expName,"Name MISMATCH");


    }
    @DataProvider
    public Object[][] getData(){
        Object[][] data={
                {"John", "Smith"},
                {"James","Brown"},
                {"David","White"},

        };
        return data;
    }


    @AfterMethod
    public void logut(){
        WebDriverWait wait=new WebDriverWait(driver,30);

        WebElement btn= driver.findElement(By.id("welcome"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();

       WebElement logout= driver.findElement(By.linkText("Logout"));
       wait.until(ExpectedConditions.elementToBeClickable(logout));
       logout.click();

    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();

    }
}
