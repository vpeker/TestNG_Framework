package testcases;

import org.testng.annotations.Test;
import pages.DashboardPageWebElements;
import pages.LoginPageWebElements;
import utils.CommonMethods;

public class AddMultipleEmployee extends CommonMethods {
    @Test
    public void addMultipleEmployee(){
        LoginPageWebElements.login();
        DashboardPageWebElements dashboard=new DashboardPageWebElements();
        jsClick(dashboard.PimBtn);
        jsClick(dashboard.addEmpBtn);

    }
}
