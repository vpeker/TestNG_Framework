package testBase;
import pages.AddEmployeePageWebElements;
import pages.DashboardPageWebElements;
import pages.LoginPageWebElements;
import pages.PersonalDetailsPageWebElements;

public class PageInitializer extends BaseClass {
    public static LoginPageWebElements login;
    public static DashboardPageWebElements dBoard;
    public static AddEmployeePageWebElements addEmp;
    public static PersonalDetailsPageWebElements pDetails;

    public void initializeAllPages() {
        login=new LoginPageWebElements();
        dBoard=new DashboardPageWebElements();
        addEmp=new AddEmployeePageWebElements();
        pDetails=new PersonalDetailsPageWebElements();
    }

}
