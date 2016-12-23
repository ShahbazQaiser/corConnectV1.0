package corConnectV1;

/**
 * Created by shahbaz on 12/8/2016.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import Utility.propertyDataClass;

import static org.testng.AssertJUnit.assertTrue;

//import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class loginPage extends LoadableComponent<loginPage>
{
    private WebDriver driver;
    private propertyDataClass pdc;

    //public String title="corconnect-qa.corcentric.com | Login";
    public String title="corconnect-qa.corcentric.com | Login";

    @FindBy(id="txtuserID_I")
    public WebElement loginIdField;

    @FindBy(id="txtPassword_I")
    public WebElement loginPwdField;

    @FindBy (id="btnSubmit_CD")
    public WebElement loginButton;

    @FindBy(id="txtuserID_ETC")
    public WebElement userIdValidateMsg;

    @FindBy(id="txtPassword_ETC")
    public WebElement passwordValidateMsg;

    @FindBy(id="lblMessageLogin")
    public WebElement logoutValidateMsg;

    /*  @FindBy(xpath ="//label[@for='id']")
      public WebElement IdValidateMsg;

      @FindBy (xpath ="//div[contains(.,'You have successfully logged out')][@class='alert alert-success fade in']")
      public WebElement logoutValidation;

      @FindBy(id="user_remember_me")
      public WebElement loginRememberCheck;*/

    public static  String userIdValMsgText="User ID is required";

    public String getUserIdValMsg()

    {
        return userIdValidateMsg.getText();
    }
    public static  String pwdValMsgText="Password is required";

    public String getPwdValMsg()

    {
        return passwordValidateMsg.getText();
    }

    public static  String logoutValMsgText="Logged out successfully.";

    public String getlogoutValMsgText()

    {
        return logoutValidateMsg.getText();
    }



    // id ='lblMessageLogin'
    // text ='Logged out successfully.'

    public loginPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void loginActivity(String userId, String password)
    {
        loginIdField.clear();
        loginPwdField.clear();
        loginIdField.sendKeys(userId);
        loginPwdField.sendKeys(password);
        loginButton.click();
    }

    public void clickLoginBtn()
    {
        loginButton.click();
    }
    @Override
    protected void isLoaded() throws Error
    {
        assertTrue(driver.getTitle().equals(title));
    }

    @Override
    protected void load()
    {
        pdc = new propertyDataClass();
        driver.get(pdc.URL);
        System.out.println(pdc.URL);
    }
   /* protected void load(){}
      driver.get("https://corconnect-qa.corcentric.com/");
    }*/

}