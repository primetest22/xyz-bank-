package resources.testdata;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;
import sun.security.util.Password;


public class TestData {

    @DataProvider(name = "credentials")
    public Object[][] getLogInCredentials(){
        String [][] data = new String[][]{
                {"jj1@yahoo.com","password"}

        };
        return data;
    }

}
