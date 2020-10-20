package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPageWebElements;
import utils.CommonMethods;
import utils.ConfigsReader;

public class LoginPageTests extends CommonMethods{
    LoginPageWebElements login;


    @Test (groups="regression")
    public void titleValidation() {
        login=new LoginPageWebElements();
        String expTitle="OrangeHRM";
        String actTitle=driver.getTitle();

        Assert.assertEquals(actTitle, expTitle, "Title MISMATCH");
    }

    @Test (groups = "smoke")
    public void login() {
        login=new LoginPageWebElements();
        sendText(login.userName, ConfigsReader.getProperty("username"));
        sendText(login.password, ConfigsReader.getProperty("password"));
        click(login.loginBtn);

        Assert.assertTrue(dBoard.orangeHrmLogo.isDisplayed(), "Login FAIL");
    }
}
